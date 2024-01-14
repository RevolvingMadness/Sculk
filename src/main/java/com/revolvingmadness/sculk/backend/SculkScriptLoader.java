package com.revolvingmadness.sculk.backend;

import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.tag.TagGroupLoader;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;

public class SculkScriptLoader implements ResourceReloader {
    private static final ResourceFinder FINDER = new ResourceFinder("scripts", ".sk");
    public Map<Identifier, SculkScript> scripts = new HashMap<>();
    public final TagGroupLoader<SculkScript> TAG_LOADER = new TagGroupLoader<>(this::get, "tags/scripts");
    public Map<Identifier, Collection<SculkScript>> taggedScripts = Map.of();

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

    public Optional<SculkScript> get(Identifier id) {
        return Optional.ofNullable(this.scripts.get(id));
    }

    public Collection<SculkScript> getScriptsFromTag(Identifier tag) {
        return this.taggedScripts.getOrDefault(tag, List.of());
    }

    @Override
    public CompletableFuture<Void> reload(Synchronizer synchronizer, ResourceManager resourceManager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor) {
        CompletableFuture<Map<Identifier, List<TagGroupLoader.TrackedEntry>>> completableScriptTags = CompletableFuture.supplyAsync(() -> this.TAG_LOADER.loadTags(resourceManager), prepareExecutor);

        CompletableFuture<Map<Identifier, CompletableFuture<SculkScript>>> completableIdentifiedScripts = CompletableFuture.supplyAsync(() -> FINDER.findResources(resourceManager), prepareExecutor).thenCompose((identifiedScriptResources) -> {
            Map<Identifier, CompletableFuture<SculkScript>> identifiedCompletableScripts = new HashMap<>();

            identifiedScriptResources.forEach((scriptIdentifier, scriptResource) -> {
                Identifier scriptResourceIdentifier = FINDER.toResourceId(scriptIdentifier);

                identifiedCompletableScripts.put(scriptResourceIdentifier, CompletableFuture.supplyAsync(() -> {
                    List<String> scriptContentsList = readResource(scriptResource);

                    return new SculkScript(scriptIdentifier, scriptContentsList);
                }, prepareExecutor));
            });

            CompletableFuture<?>[] completableFutures = identifiedCompletableScripts.values().toArray(new CompletableFuture[0]);
            return CompletableFuture.allOf(completableFutures).handle((unused, ex) -> identifiedCompletableScripts);
        });

        CompletableFuture<Pair<Map<Identifier, List<TagGroupLoader.TrackedEntry>>, Map<Identifier, CompletableFuture<SculkScript>>>> completableScriptTagPair = completableScriptTags.thenCombine(completableIdentifiedScripts, Pair::of);

        if (synchronizer == null) {
            throw new RuntimeException("Synchronizer is null???");
        }

        return completableScriptTagPair.thenCompose(synchronizer::whenPrepared).thenAcceptAsync((scriptTagPair) -> {
            Map<Identifier, CompletableFuture<SculkScript>> identifiedCompletableScripts = scriptTagPair.getSecond();
            Map<Identifier, SculkScript> identifiedScripts = new HashMap<>();

            identifiedCompletableScripts.forEach((scriptIdentifier, scriptFuture) -> scriptFuture.handle((script, exception) -> {
                if (exception != null) {
                    Logger.error("Failed to load script '" + scriptIdentifier + "'");
                    Logger.error(exception.getMessage());
                } else {
                    identifiedScripts.put(scriptIdentifier, script);
                }

                return null;
            }).join());

            this.scripts = identifiedScripts;
            this.taggedScripts = this.TAG_LOADER.buildGroup(scriptTagPair.getFirst());
        }, applyExecutor);
    }
}
