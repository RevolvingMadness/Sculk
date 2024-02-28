package com.revolvingmadness.sculk.language.builtins.classes.types.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class NBTElementClassType extends BuiltinClassType {
    public static final NBTElementClassType TYPE = new NBTElementClassType();

    private NBTElementClassType() {
        super("NBTElement");
    }
}
