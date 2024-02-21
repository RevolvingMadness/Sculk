package com.revolvingmadness.sculk.language.builtins.classes.instances.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.types.nbt.NBTNullType;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtEnd;

public class NBTNullInstance extends NBTElementInstance {
    public NBTNullInstance() {
        super(NBTNullType.TYPE);
    }

    @Override
    public NbtElement toNBT() {
        return NbtEnd.INSTANCE;
    }
}
