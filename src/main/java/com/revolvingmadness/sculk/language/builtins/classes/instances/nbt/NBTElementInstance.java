package com.revolvingmadness.sculk.language.builtins.classes.instances.nbt;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.nbt.*;
import net.minecraft.nbt.NbtElement;

import java.util.List;

public abstract class NBTElementInstance extends BuiltinClass {
    public NBTElementInstance(BuiltinType elementType) {
        super(elementType);
    }

    public abstract NbtElement toNBT();

    public boolean toNBTBoolean() {
        throw ErrorHolder.cannotConvertType(this.type, NBTBooleanType.TYPE);
    }

    public NBTCompoundInstance toNBTCompound() {
        throw ErrorHolder.cannotConvertType(this.type, NBTCompoundType.TYPE);
    }

    public double toNBTFloat() {
        throw ErrorHolder.cannotConvertType(this.type, NBTListType.TYPE);
    }

    public long toNBTInteger() {
        throw ErrorHolder.cannotConvertType(this.type, NBTIntegerType.TYPE);
    }

    public List<NBTElementInstance> toNBTList() {
        throw ErrorHolder.cannotConvertType(this.type, NBTListType.TYPE);
    }

    public String toNBTString() {
        throw ErrorHolder.cannotConvertType(this.type, NBTStringType.TYPE);
    }
}
