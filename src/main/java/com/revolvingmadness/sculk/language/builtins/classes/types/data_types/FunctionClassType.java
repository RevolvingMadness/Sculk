package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.CallableClassType;

public class FunctionClassType extends BuiltinClassType {
    public static final FunctionClassType TYPE = new FunctionClassType();

    private FunctionClassType() {
        super("Function", CallableClassType.TYPE);
    }
}
