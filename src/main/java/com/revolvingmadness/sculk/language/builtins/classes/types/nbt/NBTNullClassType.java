package com.revolvingmadness.sculk.language.builtins.classes.types.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class NBTNullClassType extends BuiltinClassType {
    public static final NBTNullClassType TYPE = new NBTNullClassType();

    private NBTNullClassType() {
        super("NBTNull");
    }
}
