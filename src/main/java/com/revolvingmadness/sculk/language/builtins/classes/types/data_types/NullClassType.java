package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class NullClassType extends BuiltinClassType {
    public static final NullClassType TYPE = new NullClassType();

    private NullClassType() {
        super("Null");
    }
}
