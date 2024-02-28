package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class TypeClassType extends BuiltinClassType {
    public static final TypeClassType TYPE;

    private TypeClassType() {
        super(TypeClassType.TYPE, "Type");
    }

    static {
        TYPE = new TypeClassType();

        TYPE.type = new TypeClassType();
    }
}
