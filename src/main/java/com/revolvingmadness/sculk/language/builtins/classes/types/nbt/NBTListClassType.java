package com.revolvingmadness.sculk.language.builtins.classes.types.nbt;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class NBTListClassType extends BuiltinClassType {
    public static final NBTListClassType TYPE = new NBTListClassType();

    private NBTListClassType() {
        super("NBTList", NBTElementClassType.TYPE);
    }
}
