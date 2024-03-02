package com.revolvingmadness.sculk.mixin;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.DataFixer;
import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.accessors.DatapackContentsAccessor;
import com.revolvingmadness.sculk.backend.SculkScript;
import com.revolvingmadness.sculk.backend.SculkScriptManager;
import net.minecraft.network.QueryableServer;
import net.minecraft.registry.CombinedDynamicRegistries;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.ServerDynamicRegistryType;
import net.minecraft.resource.*;
import net.minecraft.server.*;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.function.CommandFunctionManager;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.ApiServices;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.thread.ReentrantThreadExecutor;
import net.minecraft.world.SaveProperties;
import net.minecraft.world.level.storage.LevelStorage;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.net.Proxy;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.stream.Stream;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin extends ReentrantThreadExecutor<ServerTask> implements QueryableServer, CommandOutput, AutoCloseable {
    @Shadow
    @Final
    private static Logger LOGGER;
    @Shadow
    public MinecraftServer.ResourceManagerHolder resourceManagerHolder;
    @Shadow
    @Final
    protected SaveProperties saveProperties;
    @Shadow
    @Final
    private CombinedDynamicRegistries<ServerDynamicRegistryType> combinedDynamicRegistries;
    @Shadow
    @Final
    private CommandFunctionManager commandFunctionManager;
    @Shadow
    @Final
    private ResourcePackManager dataPackManager;
    @Shadow
    private Profiler profiler;
    @Shadow
    @Final
    private StructureTemplateManager structureTemplateManager;
    @Shadow
    @Final
    private Executor workerExecutor;

    public MinecraftServerMixin(String string) {
        super(string);
    }

    @Shadow
    private static DataPackSettings createDataPackSettings(ResourcePackManager dataPackManager) {
        return null;
    }

    @Inject(at = @At("HEAD"), method = "startServer", cancellable = true)
    private static <S extends MinecraftServer> void injectStartServer(Function<Thread, S> serverFactory, CallbackInfoReturnable<S> cir) {
        AtomicReference<MinecraftServer> atomicReference = new AtomicReference<>();
        Thread thread2 = new Thread(() -> {
            MinecraftServer server = atomicReference.get();

            SculkScriptManager.setLoader(((DatapackContentsAccessor) server.resourceManagerHolder.dataPackContents()).sculk$getSculkScriptLoader());

            SculkScriptManager.initialize();

            Collection<SculkScript> scripts = SculkScriptManager.loader.getScriptsFromTag(SculkScriptManager.START_TAG_ID);

            SculkScriptManager.executeAll(scripts, SculkScriptManager.START_TAG_ID);

            server.runServer();
        }, "Server thread");
        thread2.setUncaughtExceptionHandler((thread, throwable) -> LOGGER.error("Uncaught exception in server thread", throwable));

        if (Runtime.getRuntime().availableProcessors() > 4) {
            thread2.setPriority(8);
        }

        S minecraftServer = serverFactory.apply(thread2);
        atomicReference.set(minecraftServer);
        thread2.start();

        cir.setReturnValue(minecraftServer);
    }

    @Shadow
    public abstract int getFunctionPermissionLevel();

    @Shadow
    public abstract PlayerManager getPlayerManager();

    @Shadow
    public abstract DynamicRegistryManager.Immutable getRegistryManager();

    @Inject(at = @At("TAIL"), method = "<init>")
    public void injectInit(Thread serverThread, LevelStorage.Session session, ResourcePackManager dataPackManager, SaveLoader saveLoader, Proxy proxy, DataFixer dataFixer, ApiServices apiServices, WorldGenerationProgressListenerFactory worldGenerationProgressListenerFactory, CallbackInfo ci) {
        Sculk.server = (MinecraftServer) (Object) this;
        SculkScriptManager.setLoader(((DatapackContentsAccessor) this.resourceManagerHolder.dataPackContents()).sculk$getSculkScriptLoader());
    }

    @Inject(at = @At("HEAD"), method = "reloadResources", cancellable = true)
    public void injectReloadResources(Collection<String> dataPacks, CallbackInfoReturnable<CompletableFuture<Void>> cir) {
        MinecraftServer thisInstance = (MinecraftServer) (Object) this;

        DynamicRegistryManager.Immutable immutable = this.combinedDynamicRegistries.getPrecedingRegistryManagers(ServerDynamicRegistryType.RELOADABLE);

        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            Stream<String> var10000 = dataPacks.stream();
            ResourcePackManager datapackManager = this.dataPackManager;
            Objects.requireNonNull(datapackManager);
            return var10000.map(datapackManager::getProfile).filter(Objects::nonNull).map(ResourcePackProfile::createResourcePack).collect(ImmutableList.toImmutableList());
        }, thisInstance).thenCompose((resourcePacks) -> {
            LifecycledResourceManager lifecycledResourceManager = new LifecycledResourceManagerImpl(ResourceType.SERVER_DATA, resourcePacks);
            return DataPackContents.reload(lifecycledResourceManager, immutable, this.saveProperties.getEnabledFeatures(), this.isDedicated() ? CommandManager.RegistrationEnvironment.DEDICATED : CommandManager.RegistrationEnvironment.INTEGRATED, this.getFunctionPermissionLevel(), this.workerExecutor, thisInstance).whenComplete((dataPackContents, throwable) -> {
                if (throwable != null) {
                    lifecycledResourceManager.close();
                }

            }).thenApply((dataPackContents) -> new MinecraftServer.ResourceManagerHolder(lifecycledResourceManager, dataPackContents));
        }).thenAcceptAsync((resourceManagerHolder) -> {
            this.resourceManagerHolder.close();
            this.resourceManagerHolder = resourceManagerHolder;
            this.dataPackManager.setEnabledProfiles(dataPacks);
            DataConfiguration dataConfiguration = new DataConfiguration(createDataPackSettings(this.dataPackManager), this.saveProperties.getEnabledFeatures());
            this.saveProperties.updateLevelInfo(dataConfiguration);
            this.resourceManagerHolder.dataPackContents().refresh(this.getRegistryManager());
            this.getPlayerManager().saveAllPlayerData();
            this.getPlayerManager().onDataPacksReloaded();
            this.commandFunctionManager.setFunctions(this.resourceManagerHolder.dataPackContents().getFunctionLoader());
            SculkScriptManager.setLoader(((DatapackContentsAccessor) this.resourceManagerHolder.dataPackContents()).sculk$getSculkScriptLoader());
            this.structureTemplateManager.setResourceManager(this.resourceManagerHolder.resourceManager());
        }, this);
        if (this.isOnThread()) {
            Objects.requireNonNull(completableFuture);
            this.runTasks(completableFuture::isDone);
        }

        cir.setReturnValue(completableFuture);
    }

    @Inject(at = @At("HEAD"), method = "tickWorlds")
    public void injectTickWorlds(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        this.profiler.push("sculkScripts");
        SculkScriptManager.tick();
        this.profiler.pop();
    }

    @Shadow
    public abstract boolean isDedicated();
}
