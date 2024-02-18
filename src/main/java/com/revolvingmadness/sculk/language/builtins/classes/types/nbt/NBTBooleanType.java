package com.revolvingmadness.sculk.language.builtins.classes.types.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class NBTBooleanType extends BuiltinType {
    public static final NBTBooleanType TYPE = new NBTBooleanType();

    private NBTBooleanType() {
        super("NBTBoolean", NBTElementType.TYPE);
    }
}
