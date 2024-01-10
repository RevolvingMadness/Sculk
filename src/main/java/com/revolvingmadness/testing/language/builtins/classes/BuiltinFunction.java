package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.FunctionType;

public abstract class BuiltinFunction extends BuiltinClass {
    @Override
    public BuiltinType getType() {
        return new FunctionType();
    }

    @Override
    public BuiltinFunction toFunction() {
        return this;
    }
}
