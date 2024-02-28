package com.revolvingmadness.sculk.language.builtins.classes.instances.nbt;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.nbt.*;
import net.minecraft.nbt.NbtElement;

import java.util.List;

public abstract class NBTElementInstance extends BuiltinClass {
    public NBTElementInstance(BuiltinClassType elementType) {
        super(elementType);
    }

    public abstract NbtElement toNBT();

    public boolean toNBTBoolean() {
        throw ErrorHolder.cannotConvertType(this.type, NBTBooleanClassType.TYPE);
    }

    public NBTCompoundInstance toNBTCompound() {
        throw ErrorHolder.cannotConvertType(this.type, NBTCompoundClassType.TYPE);
    }

    public double toNBTFloat() {
        throw ErrorHolder.cannotConvertType(this.type, NBTListClassType.TYPE);
    }

    public long toNBTInteger() {
        throw ErrorHolder.cannotConvertType(this.type, NBTIntegerClassType.TYPE);
    }

    public List<NBTElementInstance> toNBTList() {
        throw ErrorHolder.cannotConvertType(this.type, NBTListClassType.TYPE);
    }

    public String toNBTString() {
        throw ErrorHolder.cannotConvertType(this.type, NBTStringClassType.TYPE);
    }
}
