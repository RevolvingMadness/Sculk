package com.revolvingmadness.sculk.dynamicreg;

import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public abstract class DynamicRegistry<T> {
    public final Map<Identifier, T> registeredComponents;
    public final DefaultedRegistry<T> registry;

    public DynamicRegistry(DefaultedRegistry<T> registry) {
        this.registry = registry;
        this.registeredComponents = new HashMap<>();
    }

    public abstract void register(Identifier id, T value);
}
