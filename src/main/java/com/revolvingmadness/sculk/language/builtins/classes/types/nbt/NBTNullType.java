package com.revolvingmadness.sculk.language.builtins.classes.types.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class NBTNullType extends BuiltinType {
    public static final NBTNullType TYPE = new NBTNullType();

    private NBTNullType() {
        super("NBTNull");
    }
}
