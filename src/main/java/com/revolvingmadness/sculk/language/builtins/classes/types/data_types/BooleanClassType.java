package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class BooleanClassType extends BuiltinClassType {
    public static final BooleanClassType TYPE = new BooleanClassType();

    private BooleanClassType() {
        super("Boolean");
    }
}
