package com.revolvingmadness.testing.mixin;

import com.revolvingmadness.testing.accessor.DatapackContentsAccessor;
import com.revolvingmadness.testing.loader.LangScriptLoader;
import net.minecraft.loot.LootManager;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.tag.TagManagerLoader;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.server.DataPackContents;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.function.FunctionLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(DataPackContents.class)
public class DatapackContentsMixin implements DatapackContentsAccessor {
    @Shadow
    @Final
    private TagManagerLoader registryTagManager;

    @Shadow
    @Final
    private LootManager lootManager;

    @Shadow
    @Final
    private RecipeManager recipeManager;

    @Shadow
    @Final
    private FunctionLoader functionLoader;

    @Shadow
    @Final
    private ServerAdvancementLoader serverAdvancementLoader;

    @Unique
    private LangScriptLoader langScriptLoader;

    @Override
    public LangScriptLoader testing$getLangScriptLoader() {
        return this.langScriptLoader;
    }

    @Inject(at=@At("TAIL"), method="<init>")
    public void injectInit(DynamicRegistryManager.Immutable dynamicRegistryManager, FeatureSet enabledFeatures, CommandManager.RegistrationEnvironment environment, int functionPermissionLevel, CallbackInfo ci) {
        this.langScriptLoader = new LangScriptLoader();
    }

    @Inject(at=@At("HEAD"), method="getContents", cancellable = true)
    public void injectGetContents(CallbackInfoReturnable<List<ResourceReloader>> cir) {
        cir.setReturnValue(List.of(this.registryTagManager, this.lootManager, this.recipeManager, this.functionLoader, this.langScriptLoader, this.serverAdvancementLoader));
    }
}
