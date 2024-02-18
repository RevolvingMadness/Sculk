package com.revolvingmadness.sculk.language.builtins.classes.types.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class NBTCompoundType extends BuiltinType {
    public static final NBTCompoundType TYPE = new NBTCompoundType();

    private NBTCompoundType() {
        super("NBTCompound", NBTElementType.TYPE);
    }
}
