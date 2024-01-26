package com.revolvingmadness.sculk.accessors;

import net.minecraft.nbt.NbtElement;

public interface EntityAccessor {
    void sculk$deleteCustomData(String key);

    NbtElement sculk$readCustomData(String key);

    void sculk$writeCustomData(String key, NbtElement value);
}
