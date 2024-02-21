package com.revolvingmadness.sculk.language.builtins.classes.instances.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.types.nbt.NBTStringType;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtString;

public class NBTStringInstance extends NBTElementInstance {
    public final String value;

    public NBTStringInstance(String value) {
        super(NBTStringType.TYPE);
        this.value = value;
    }

    @Override
    public NbtElement toNBT() {
        return NbtString.of(this.value);
    }

    @Override
    public String toNBTString() {
        return this.value;
    }
}
