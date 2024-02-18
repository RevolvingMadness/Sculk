package com.revolvingmadness.sculk.language.builtins.classes.types.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class NBTFloatType extends BuiltinType {
    public static final NBTFloatType TYPE = new NBTFloatType();

    private NBTFloatType() {
        super("NBTFloat", NBTElementType.TYPE);
    }
}
