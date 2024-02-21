package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class NumberType extends BuiltinType {
    public static final NumberType TYPE = new NumberType();

    private NumberType() {
        super("Number");
    }
}
