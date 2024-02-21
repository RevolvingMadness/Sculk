package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class NullType extends BuiltinType {
    public static final NullType TYPE = new NullType();

    private NullType() {
        super("Null");
    }
}
