package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class MethodType extends BuiltinType {
    public static final MethodType TYPE = new MethodType();

    private MethodType() {
        super("Method");
    }
}
