package com.revolvingmadness.testing.loader;

import com.mojang.datafixers.util.Pair;
import com.revolvingmadness.testing.Testing;
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

public class LangScriptLoader implements ResourceReloader {
    private static final ResourceFinder FINDER = new ResourceFinder("scripts", ".script");
    private final TagGroupLoader<LangScript> TAG_LOADER = new TagGroupLoader<>(this::get, "tags/scripts");

    private Map<Identifier, LangScript> scripts = new HashMap<>();
    private Map<Identifier, Collection<LangScript>> identifiedScripts = Map.of();

    public Optional<LangScript> get(Identifier id) {
        return Optional.ofNullable(this.scripts.get(id));
    }

    public Collection<LangScript> getScriptsFromTag(Identifier tag) {
        return this.identifiedScripts.getOrDefault(tag, List.of());
    }

    @Override
    public CompletableFuture<Void> reload(Synchronizer synchronizer, ResourceManager resourceManager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor) {
        Testing.LOGGER.info("Starting reload for mod '" + Testing.ID + "'");
        CompletableFuture<Map<Identifier, List<TagGroupLoader.TrackedEntry>>> completableScriptTags = CompletableFuture.supplyAsync(() -> this.TAG_LOADER.loadTags(resourceManager), prepareExecutor);

        CompletableFuture<Map<Identifier, CompletableFuture<LangScript>>> completableIdentifiedScripts = CompletableFuture.supplyAsync(() -> FINDER.findResources(resourceManager), prepareExecutor).thenCompose((identifiedScriptResources) -> {
            Map<Identifier, CompletableFuture<LangScript>> identifiedCompletableScripts = new HashMap<>();

            identifiedScriptResources.forEach((scriptIdentifier, scriptResource) -> {
                Identifier scriptResourceIdentifier = FINDER.toResourceId(scriptIdentifier);

                identifiedCompletableScripts.put(scriptResourceIdentifier, CompletableFuture.supplyAsync(() -> {
                    List<String> scriptContents = readResource(scriptResource);

                    return new LangScript(scriptIdentifier, scriptContents);
                }, prepareExecutor));
            });

            CompletableFuture<?>[] completableFutures = identifiedCompletableScripts.values().toArray(new CompletableFuture[0]);
            return CompletableFuture.allOf(completableFutures).handle((unused, ex) -> identifiedCompletableScripts);
        });

        CompletableFuture<Pair<Map<Identifier, List<TagGroupLoader.TrackedEntry>>, Map<Identifier, CompletableFuture<LangScript>>>> completableScriptTagPair = completableScriptTags.thenCombine(completableIdentifiedScripts, Pair::of);

        if (synchronizer == null) {
            throw new RuntimeException("Synchronizer is null???");
        }

        return completableScriptTagPair.thenCompose(synchronizer::whenPrepared).thenAcceptAsync((scriptTagPair) -> {
            Map<Identifier, CompletableFuture<LangScript>> identifiedCompletableScripts = scriptTagPair.getSecond();
            Map<Identifier, LangScript> identifiedScripts = new HashMap<>();

            identifiedCompletableScripts.forEach((scriptIdentifier, scriptFuture) -> scriptFuture.handle((script, exception) -> {
                if (exception != null) {
                    Testing.LOGGER.error("Failed to load script '" + scriptIdentifier + "'");
                    Testing.LOGGER.error(exception.getMessage());
                } else {
                    identifiedScripts.put(scriptIdentifier, script);
                }

                return null;
            }).join());

            this.scripts = identifiedScripts;
            this.identifiedScripts = this.TAG_LOADER.buildGroup(scriptTagPair.getFirst());
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
