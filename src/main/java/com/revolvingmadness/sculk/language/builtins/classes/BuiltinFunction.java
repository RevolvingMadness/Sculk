package com.revolvingmadness.sculk.language.builtins.classes;

import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.FunctionType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public abstract class BuiltinFunction extends BuiltinClass {
    public abstract BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments);

    @Override
    public BuiltinType getType() {
        return FunctionType.TYPE;
    }

    @Override
    public BuiltinFunction toFunction() {
        return this;
    }
}
