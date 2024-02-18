package com.revolvingmadness.sculk.language.builtins.classes.types.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class NBTStringType extends BuiltinType {
    public static final NBTStringType TYPE = new NBTStringType();

    private NBTStringType() {
        super("NBTString", NBTElementType.TYPE);
    }
}
