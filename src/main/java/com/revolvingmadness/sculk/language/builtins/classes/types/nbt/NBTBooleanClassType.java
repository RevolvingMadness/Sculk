package com.revolvingmadness.sculk.language.builtins.classes.types.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class NBTBooleanClassType extends BuiltinClassType {
    public static final NBTBooleanClassType TYPE = new NBTBooleanClassType();

    private NBTBooleanClassType() {
        super("NBTBoolean", NBTElementClassType.TYPE);
    }
}
