package com.revolvingmadness.sculk.language.builtins.classes.types.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class NBTFloatClassType extends BuiltinClassType {
    public static final NBTFloatClassType TYPE = new NBTFloatClassType();

    private NBTFloatClassType() {
        super("NBTFloat", NBTElementClassType.TYPE);
    }
}
