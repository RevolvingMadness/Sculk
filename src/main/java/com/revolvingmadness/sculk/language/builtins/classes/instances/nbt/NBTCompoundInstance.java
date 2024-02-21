package com.revolvingmadness.sculk.language.builtins.classes.instances.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.types.nbt.NBTCompoundType;
import com.revolvingmadness.sculk.language.errors.NameError;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.Map;

public class NBTCompoundInstance extends NBTElementInstance {
    public final Map<String, NBTElementInstance> value;

    public NBTCompoundInstance(Map<String, NBTElementInstance> value) {
        super(NBTCompoundType.TYPE);
        this.value = value;
    }

    public NBTElementInstance get(String key) {
        NBTElementInstance value = this.value.get(key);

        if (value == null) {
            return new NBTNullInstance();
        }

        return value;
    }

    public NBTElementInstance getOrThrow(String key) {
        NBTElementInstance value = this.value.get(key);

        if (value == null) {
            throw new NameError("NBT Compound does not have the expected key '" + key + "'");
        }

        return value;
    }

    @Override
    public NbtElement toNBT() {
        NbtCompound compound = new NbtCompound();

        this.value.forEach((key, value) -> compound.put(key, value.toNBT()));

        return compound;
    }

    @Override
    public NBTCompoundInstance toNBTCompound() {
        return this;
    }
}
