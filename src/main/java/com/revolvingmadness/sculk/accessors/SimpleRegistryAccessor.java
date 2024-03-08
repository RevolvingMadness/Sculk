package com.revolvingmadness.sculk.accessors;

import net.minecraft.util.Identifier;

public interface SimpleRegistryAccessor<T> {
    void sculk$set(Identifier id, T value);
}
