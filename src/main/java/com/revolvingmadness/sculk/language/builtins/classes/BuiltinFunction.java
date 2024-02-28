package com.revolvingmadness.sculk.language.builtins.classes;

import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.FunctionClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public abstract class BuiltinFunction extends BuiltinClass {
    public BuiltinFunction() {
        super(FunctionClassType.TYPE);
    }

    public abstract BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments);

    @Override
    public BuiltinFunction toFunction() {
        return this;
    }
}
