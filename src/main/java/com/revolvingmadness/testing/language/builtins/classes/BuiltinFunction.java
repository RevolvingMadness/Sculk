package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.FunctionType;
import com.revolvingmadness.testing.language.interpreter.Interpreter;

import java.util.List;

public abstract class BuiltinFunction extends BuiltinClass {
    @Override
    public BuiltinType getType() {
        return new FunctionType();
    }

    @Override
    public BuiltinFunction toFunction() {
        return this;
    }

    public abstract BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments);
}
