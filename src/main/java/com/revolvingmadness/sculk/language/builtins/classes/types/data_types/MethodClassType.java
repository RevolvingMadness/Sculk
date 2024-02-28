package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.CallableClassType;

public class MethodClassType extends BuiltinClassType {
    public static final MethodClassType TYPE = new MethodClassType();

    private MethodClassType() {
        super("Method", CallableClassType.TYPE);
    }
}
