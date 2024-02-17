package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class FunctionType extends BuiltinType {
    public static final FunctionType TYPE = new FunctionType();

    private FunctionType() {
        super("Function", CallableType.TYPE);
    }
}
