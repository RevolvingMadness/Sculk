package com.revolvingmadness.sculk.language.builtins.classes.instances.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.nbt.NBTListType;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.List;

public class NBTListInstance extends NBTElementInstance {
    public final List<NBTElementInstance> value;

    public NBTListInstance(List<NBTElementInstance> value) {
        this.value = value;
    }

    @Override
    public BuiltinType getType() {
        return NBTListType.TYPE;
    }

    @Override
    public NbtElement toNBT() {
        NbtList list = new NbtList();

        this.value.forEach(value -> list.add(value.toNBT()));

        return list;
    }

    @Override
    public List<NBTElementInstance> toNBTList() {
        return this.value;
    }
}
