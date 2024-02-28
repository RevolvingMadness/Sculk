package com.revolvingmadness.sculk.language.builtins.classes.instances.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.types.nbt.NBTBooleanClassType;
import net.minecraft.nbt.NbtByte;
import net.minecraft.nbt.NbtElement;

@SuppressWarnings("unused")
public class NBTBooleanInstance extends NBTElementInstance {
    public final boolean value;

    public NBTBooleanInstance(boolean value) {
        super(NBTBooleanClassType.TYPE);
        this.value = value;
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
