package com.revolvingmadness.sculk.language.builtins.classes.instances.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.nbt.NBTFloatType;
import net.minecraft.nbt.NbtDouble;
import net.minecraft.nbt.NbtElement;

public class NBTFloatInstance extends NBTElementInstance {
    public final double value;

    public NBTFloatInstance(double value) {
        this.value = value;
    }

    @Override
    public BuiltinType getType() {
        return NBTFloatType.TYPE;
    }

    @Override
    public NbtElement toNBT() {
        return NbtDouble.of(this.value);
    }

    @Override
    public double toNBTFloat() {
        return this.value;
    }
}
