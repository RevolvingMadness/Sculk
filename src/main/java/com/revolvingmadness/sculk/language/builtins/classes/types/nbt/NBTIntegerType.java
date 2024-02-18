package com.revolvingmadness.sculk.language.builtins.classes.types.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class NBTIntegerType extends BuiltinType {
    public static final NBTIntegerType TYPE = new NBTIntegerType();

    private NBTIntegerType() {
        super("NBTInteger", NBTElementType.TYPE);
    }
}
