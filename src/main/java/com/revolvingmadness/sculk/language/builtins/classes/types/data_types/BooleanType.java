package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class BooleanType extends BuiltinType {
    public static final BooleanType TYPE = new BooleanType();

    private BooleanType() {
        super("Boolean");
    }
}
