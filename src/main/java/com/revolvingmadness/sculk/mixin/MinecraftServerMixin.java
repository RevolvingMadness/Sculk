package com.revolvingmadness.sculk.mixin;

import com.google.common.collect.ImmutableList;
import com.revolvingmadness.sculk.accessors.DatapackContentsAccessor;
import com.revolvingmadness.sculk.backend.SculkScriptManager;
import net.minecraft.network.QueryableServer;
import net.minecraft.registry.CombinedDynamicRegistries;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.ServerDynamicRegistryType;
import net.minecraft.resource.*;
import net.minecraft.server.DataPackContents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.ServerTask;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.function.CommandFunctionManager;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.thread.ReentrantThreadExecutor;
import net.minecraft.world.SaveProperties;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.BooleanSupplier;
import java.util.stream.Stream;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin extends ReentrantThreadExecutor<ServerTask> implements QueryableServer, CommandOutput, AutoCloseable {
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

    @Shadow
    public abstract int getFunctionPermissionLevel();

    @Shadow
    public abstract PlayerManager getPlayerManager();

    @Shadow
    public abstract DynamicRegistryManager.Immutable getRegistryManager();

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
