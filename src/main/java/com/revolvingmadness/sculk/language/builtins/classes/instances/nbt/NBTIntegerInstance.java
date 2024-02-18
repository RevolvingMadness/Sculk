package com.revolvingmadness.sculk.language.builtins.classes.instances.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.nbt.NBTIntegerType;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtLong;

public class NBTIntegerInstance extends NBTElementInstance {
    public final long value;

    public NBTIntegerInstance(long value) {
        this.value = value;
    }

    @Override
    public BuiltinType getType() {
        return NBTIntegerType.TYPE;
    }

    @Override
    public NbtElement toNBT() {
        return NbtLong.of(this.value);
    }

    @Override
    public long toNBTInteger() {
        return this.value;
    }
}
