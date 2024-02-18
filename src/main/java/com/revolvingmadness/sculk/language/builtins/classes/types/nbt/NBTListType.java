package com.revolvingmadness.sculk.language.builtins.classes.types.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class NBTListType extends BuiltinType {
    public static final NBTListType TYPE = new NBTListType();

    private NBTListType() {
        super("NBTList", NBTElementType.TYPE);
    }
}
