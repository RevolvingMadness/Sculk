package com.revolvingmadness.testing.loader;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.lang.LangScript;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.registry.tag.TagGroupLoader;
import net.minecraft.resource.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;

public class ResourceLoader implements IdentifiableResourceReloadListener {
    private static final ResourceFinder FINDER = new ResourceFinder("scripts", ".script");
    private volatile Map<Identifier, LangScript> scripts = ImmutableMap.of();
    private final TagGroupLoader<LangScript> tagLoader = new TagGroupLoader<>(this::get, "tags/scripts");
    private Map<Identifier, Collection<LangScript>> tagsWithScripts = Map.of();

    public Optional<LangScript> get(Identifier id) {
        return Optional.ofNullable(this.scripts.get(id));
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier(Testing.ID, "resource_loader");
    }

    @Override
    public CompletableFuture<Void> reload(Synchronizer synchronizer, ResourceManager resourceManager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor) {
        Testing.LOGGER.info("Starting reload for mod '" + Testing.ID + "'");
        CompletableFuture<Map<Identifier, List<TagGroupLoader.TrackedEntry>>> completableScriptTags = CompletableFuture.supplyAsync(() -> this.tagLoader.loadTags(resourceManager), prepareExecutor);
        CompletableFuture<Map<Identifier, CompletableFuture<LangScript>>> completableIdentifiedScripts = CompletableFuture.supplyAsync(() -> {
            return FINDER.findResources(resourceManager);
        }, prepareExecutor).thenCompose((identifiedScriptResources) -> {
            Map<Identifier, CompletableFuture<LangScript>> identifiedScripts = Maps.newHashMap();

            for (Map.Entry<Identifier, Resource> entry : identifiedScriptResources.entrySet()) {
                Identifier scriptIdentifier = entry.getKey();
                Identifier scriptResourceIdentifier = FINDER.toResourceId(scriptIdentifier);
                identifiedScripts.put(scriptResourceIdentifier, CompletableFuture.supplyAsync(() -> {
                    Resource scriptResource = entry.getValue();
                    List<String> scriptContents = readResource(scriptResource);

                    return new LangScript(scriptIdentifier, scriptContents);
                }, prepareExecutor));
            }

            CompletableFuture<?>[] completableFutures = identifiedScripts.values().toArray(new CompletableFuture[0]);
            return CompletableFuture.allOf(completableFutures).handle((unused, ex) -> identifiedScripts);
        });
        CompletableFuture<Pair<Map<Identifier, List<TagGroupLoader.TrackedEntry>>, Map<Identifier, CompletableFuture<LangScript>>>> completableScriptTagPair = completableScriptTags.thenCombine(completableIdentifiedScripts, Pair::of);
        if (synchronizer == null) {
            throw new RuntimeException("Synchronizer is null???");
        }
        return completableScriptTagPair.thenCompose(synchronizer::whenPrepared).thenAcceptAsync((intermediate) -> {
            Map<Identifier, CompletableFuture<LangScript>> completableScriptMap = intermediate.getSecond();
            ImmutableMap.Builder<Identifier, LangScript> identifiedScriptBuilder = ImmutableMap.builder();

            completableScriptMap.forEach((scriptID, scriptFuture) -> scriptFuture.handle((script, exception) -> {
                if (exception != null) {
                    Testing.LOGGER.error("Failed to load script '" + scriptID + "'");
                    Testing.LOGGER.error(exception.getMessage());
                } else {
                    identifiedScriptBuilder.put(scriptID, script);
                }

                return null;
            }).join());
            this.scripts = identifiedScriptBuilder.build();
            this.tagsWithScripts = this.tagLoader.buildGroup(intermediate.getFirst());
        }, applyExecutor);
    }

    private static List<String> readResource(Resource resource) {
        try {
            BufferedReader bufferedReader = resource.getReader();

            List<String> contents;
            try {
                contents = bufferedReader.lines().toList();
            } catch (Throwable throwable) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable throwable1) {
                        throwable.addSuppressed(throwable1);
                    }
                }

                throw throwable;
            }

            bufferedReader.close();

            return contents;
        } catch (IOException ioException) {
            throw new CompletionException(ioException);
        }
    }
}
