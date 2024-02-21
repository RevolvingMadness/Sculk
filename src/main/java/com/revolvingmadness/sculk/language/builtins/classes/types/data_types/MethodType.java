package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.CallableType;

public class MethodType extends BuiltinType {
    public static final MethodType TYPE = new MethodType();

    private MethodType() {
        super("Method", CallableType.TYPE);
    }
}
