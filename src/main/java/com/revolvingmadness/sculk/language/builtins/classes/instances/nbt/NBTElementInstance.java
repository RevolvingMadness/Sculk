package com.revolvingmadness.sculk.language.builtins.classes.instances.nbt;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.nbt.*;
import net.minecraft.nbt.NbtElement;

import java.util.List;

public abstract class NBTElementInstance extends BuiltinClass {
    @Override
    public BuiltinType getType() {
        return NBTElementType.TYPE;
    }

    public abstract NbtElement toNBT();

    public boolean toNBTBoolean() {
        throw ErrorHolder.cannotConvertType(this.getType(), NBTBooleanType.TYPE);
    }

    public NBTCompoundInstance toNBTCompound() {
        throw ErrorHolder.cannotConvertType(this.getType(), NBTCompoundType.TYPE);
    }

    public double toNBTFloat() {
        throw ErrorHolder.cannotConvertType(this.getType(), NBTListType.TYPE);
    }

    public long toNBTInteger() {
        throw ErrorHolder.cannotConvertType(this.getType(), NBTIntegerType.TYPE);
    }

    public List<NBTElementInstance> toNBTList() {
        throw ErrorHolder.cannotConvertType(this.getType(), NBTListType.TYPE);
    }

    public String toNBTString() {
        throw ErrorHolder.cannotConvertType(this.getType(), NBTStringType.TYPE);
    }
}
