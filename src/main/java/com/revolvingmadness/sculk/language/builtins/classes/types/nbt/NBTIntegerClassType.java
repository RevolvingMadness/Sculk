package com.revolvingmadness.sculk.language.builtins.classes.types.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class NBTIntegerClassType extends BuiltinClassType {
    public static final NBTIntegerClassType TYPE = new NBTIntegerClassType();

    private NBTIntegerClassType() {
        super("NBTInteger", NBTElementClassType.TYPE);
    }
}
