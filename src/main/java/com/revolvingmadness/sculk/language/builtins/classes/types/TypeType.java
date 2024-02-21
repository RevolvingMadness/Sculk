package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class TypeType extends BuiltinType {
    public static final TypeType TYPE;

    private TypeType() {
        super((BuiltinType) null, "Type");
    }

    static {
        TYPE = new TypeType();

        TYPE.type = new TypeType();
    }
}
