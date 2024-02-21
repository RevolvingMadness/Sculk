package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.CallableType;

public class FunctionType extends BuiltinType {
    public static final FunctionType TYPE = new FunctionType();

    private FunctionType() {
        super("Function", CallableType.TYPE);
    }
}
