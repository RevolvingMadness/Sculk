package com.revolvingmadness.sculk.language.builtins.classes.types.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class NBTCompoundClassType extends BuiltinClassType {
    public static final NBTCompoundClassType TYPE = new NBTCompoundClassType();

    private NBTCompoundClassType() {
        super("NBTCompound", NBTElementClassType.TYPE);
    }
}
