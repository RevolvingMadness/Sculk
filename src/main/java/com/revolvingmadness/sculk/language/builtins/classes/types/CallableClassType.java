package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class CallableClassType extends BuiltinClassType {
    public static final CallableClassType TYPE = new CallableClassType();

    private CallableClassType() {
        super("Callable");
    }
}
