package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class NumberClassType extends BuiltinClassType {
    public static final NumberClassType TYPE = new NumberClassType();

    private NumberClassType() {
        super("Number");
    }
}
