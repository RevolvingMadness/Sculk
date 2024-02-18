package com.revolvingmadness.sculk.language.builtins.classes.types.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class NBTElementType extends BuiltinType {
    public static final NBTElementType TYPE = new NBTElementType();

    private NBTElementType() {
        super("NBTElement");
    }
}
