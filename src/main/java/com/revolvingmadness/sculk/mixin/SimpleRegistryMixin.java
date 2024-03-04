package com.revolvingmadness.sculk.mixin;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

@Mixin(SimpleRegistry.class)
public abstract class SimpleRegistryMixin<T> {
    @Shadow
    @Final
    private Map<RegistryKey<T>, RegistryEntry.Reference<T>> keyToEntry;
    @Shadow
    @Final
    private Map<T, RegistryEntry.Reference<T>> valueToEntry;

    @Shadow
    public abstract RegistryKey<? extends Registry<T>> getKey();

    @SuppressWarnings("unchecked")
    @Inject(at = @At("HEAD"), method = "freeze", cancellable = true)
    public void injectFreeze(CallbackInfoReturnable<Registry<T>> cir) {
        this.valueToEntry.forEach((value, entry) -> entry.setValue(value));
        List<Identifier> list = this.keyToEntry.entrySet().stream().filter(entry -> !entry.getValue().hasKeyAndValue()).map(entry -> entry.getKey().getValue()).sorted().toList();
        if (!list.isEmpty()) {
            throw new IllegalStateException("Unbound values in registry " + this.getKey() + ": " + list);
        }

        cir.setReturnValue((SimpleRegistry<T>) (Object) this);
    }
}
