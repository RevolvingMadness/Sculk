package com.revolvingmadness.testing.resourcereloader;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.revolvingmadness.testing.Testing;
import net.minecraft.registry.tag.TagGroupLoader;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class ResourceLoader implements ResourceReloader {
    private static final ResourceFinder FINDER = new ResourceFinder("functions", ".mcfunction");
    private volatile Map<Identifier, CommandFunction> functions = ImmutableMap.of();
    private final TagGroupLoader<CommandFunction> tagLoader = new TagGroupLoader<>(this::get, "tags/functions");
    private volatile Map<Identifier, Collection<CommandFunction>> tagsWithScripts = Map.of();

    public Optional<CommandFunction> get(Identifier id) {
        return Optional.ofNullable(this.functions.get(id));
    }

    @Override
    public CompletableFuture<Void> reload(Synchronizer synchronizer, ResourceManager resourceManager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor) {
        CompletableFuture<Map<Identifier, List<TagGroupLoader.TrackedEntry>>> completableCommandFunctionTags = CompletableFuture.supplyAsync(() -> {
            return this.tagLoader.loadTags(resourceManager);
        }, prepareExecutor);
        CompletableFuture<Map<Identifier, CompletableFuture<CommandFunction>>> completableIdentifiedFunctions = CompletableFuture.supplyAsync(() -> {
            return FINDER.findResources(resourceManager);
        }, prepareExecutor).thenCompose((identifiedCommandFunctionResources) -> {
            Map<Identifier, CompletableFuture<CommandFunction>> identifiedFunctions = Maps.newHashMap();

            for (Map.Entry<Identifier, Resource> entry : identifiedCommandFunctionResources.entrySet()) {
                Identifier commandFunctionIdentifier = entry.getKey();
                Identifier commandFunctionResourceIdentifier = FINDER.toResourceId(commandFunctionIdentifier);
                identifiedFunctions.put(commandFunctionResourceIdentifier, CompletableFuture.supplyAsync(() -> {
                    Resource functionResource = entry.getValue();
                    // List<String> list = readLines(resource);
                    // return CommandFunction.create(identifier2, this.commandDispatcher, serverCommandSource, list);

                    // 1. Read the contents of the resource into a List<String>
                    // 2. Return a function class that contains the identifier and the contents of the function

                    return null;
                }, prepareExecutor));
            }

            CompletableFuture<?>[] completableFutures = identifiedFunctions.values().toArray(new CompletableFuture[0]);
            return CompletableFuture.allOf(completableFutures).handle((unused, ex) -> identifiedFunctions);
        });
        CompletableFuture<Pair<Map<Identifier, List<TagGroupLoader.TrackedEntry>>, Map<Identifier, CompletableFuture<CommandFunction>>>> completableIdentifiedCommandFunctionsPair = completableCommandFunctionTags.thenCombine(completableIdentifiedFunctions, Pair::of);
        if (synchronizer == null) {
            throw new RuntimeException("Synchronizer is null???");
        }
        return completableIdentifiedCommandFunctionsPair.thenCompose(synchronizer::whenPrepared).thenAcceptAsync((intermediate) -> {
            Map<Identifier, CompletableFuture<CommandFunction>> completableCommandFunctionMap = intermediate.getSecond();
            ImmutableMap.Builder<Identifier, CommandFunction> identifiedCommandFunctionBuilder = ImmutableMap.builder();

            completableCommandFunctionMap.forEach((commandFunctionID, commandFunctionFuture) -> commandFunctionFuture.handle((commandFunction, exception) -> {
                if (exception != null) {
                    Testing.LOGGER.error("Failed to load function '" + commandFunctionID + "'");
                    Testing.LOGGER.error(exception.getMessage());
                } else {
                    identifiedCommandFunctionBuilder.put(commandFunctionID, commandFunction);
                }

                return null;
            }).join());
            this.functions = identifiedCommandFunctionBuilder.build();
            this.tagsWithScripts = this.tagLoader.buildGroup(intermediate.getFirst());
        }, applyExecutor);
    }
}
