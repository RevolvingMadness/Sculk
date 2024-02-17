package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class CallableType extends BuiltinType {
    public static final CallableType TYPE = new CallableType();

    private CallableType() {
        super("Callable");
    }
}
