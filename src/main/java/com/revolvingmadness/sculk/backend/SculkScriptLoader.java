package com.revolvingmadness.sculk.backend;

import com.mojang.datafixers.util.Pair;
import com.revolvingmadness.sculk.language.ScriptTag;
import net.minecraft.registry.tag.TagGroupLoader;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.jetbrains.annotations.NotNull;

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

    @NotNull
    private static Map<Identifier, SculkScript> loadScripts(Synchronizer synchronizer, Pair<Map<Identifier, List<TagGroupLoader.TrackedEntry>>, Map<Identifier, CompletableFuture<SculkScript>>> scriptTagPair) {
        if (synchronizer == null) {
            throw new RuntimeException("Synchronizer is null???");
        }

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
        return identifiedScripts;
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

    public Optional<SculkScript> get(Identifier id) {
        return Optional.ofNullable(this.scripts.get(id));
    }

    public Collection<SculkScript> getScriptsFromTag(Identifier tag) {
        return this.taggedScripts.getOrDefault(tag, List.of());
    }

    @Override
    public CompletableFuture<Void> reload(Synchronizer synchronizer, ResourceManager resourceManager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor) {
        Map<Identifier, List<TagGroupLoader.TrackedEntry>> completableScriptTags = CompletableFuture.supplyAsync(() -> this.TAG_LOADER.loadTags(resourceManager), prepareExecutor).join();

        Map<Identifier, CompletableFuture<SculkScript>> completableIdentifiedScripts = CompletableFuture.supplyAsync(() -> FINDER.findResources(resourceManager), prepareExecutor).thenCompose((identifiedScriptResources) -> {
            Map<Identifier, CompletableFuture<SculkScript>> taggedCompletableScripts = new HashMap<>();

            identifiedScriptResources.forEach((scriptIdentifier, scriptResource) -> {
                Identifier scriptTag = FINDER.toResourceId(scriptIdentifier);

                taggedCompletableScripts.put(scriptTag, CompletableFuture.supplyAsync(() -> {
                    List<String> scriptContentsList = readResource(scriptResource);

                    return new SculkScript(scriptIdentifier, scriptContentsList, this, ScriptTag.of(scriptTag));
                }, prepareExecutor));
            });

            CompletableFuture<?>[] completableFutures = taggedCompletableScripts.values().toArray(new CompletableFuture[0]);
            return CompletableFuture.allOf(completableFutures).handle((unused, ex) -> taggedCompletableScripts);
        }).join();

        Pair<Map<Identifier, List<TagGroupLoader.TrackedEntry>>, Map<Identifier, CompletableFuture<SculkScript>>> scriptTagPair = Pair.of(completableScriptTags, completableIdentifiedScripts);

        this.scripts = SculkScriptLoader.loadScripts(synchronizer, scriptTagPair);
        this.taggedScripts = this.TAG_LOADER.buildGroup(scriptTagPair.getFirst());

        SculkScriptManager.setLoader(this);

        SculkScriptManager.initialize();

        Collection<SculkScript> scripts = this.getScriptsFromTag(SculkScriptManager.START_TAG_ID);

        SculkScriptManager.executeAll(scripts, SculkScriptManager.START_TAG_ID);

        return CompletableFuture.completedFuture(null).thenCompose(synchronizer::whenPrepared).thenAcceptAsync(void_ -> {

        }, applyExecutor);
    }
}
