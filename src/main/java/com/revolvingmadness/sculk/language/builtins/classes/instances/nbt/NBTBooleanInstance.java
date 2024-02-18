package com.revolvingmadness.sculk.language.builtins.classes.instances.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.nbt.NBTBooleanType;
import net.minecraft.nbt.NbtByte;
import net.minecraft.nbt.NbtElement;

public class NBTBooleanInstance extends NBTElementInstance {
    public final boolean value;

    public NBTBooleanInstance(boolean value) {
        this.value = value;
    }

    @Override
    public BuiltinType getType() {
        return NBTBooleanType.TYPE;
    }

    @Override
    public NbtElement toNBT() {
        return NbtByte.of(this.value);
    }

    @Override
    public boolean toNBTBoolean() {
        return this.value;
    }
}
