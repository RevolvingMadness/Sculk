package com.revolvingmadness.sculk.language.builtins.classes.types.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class NBTStringClassType extends BuiltinClassType {
    public static final NBTStringClassType TYPE = new NBTStringClassType();

    private NBTStringClassType() {
        super("NBTString", NBTElementClassType.TYPE);
    }
}
