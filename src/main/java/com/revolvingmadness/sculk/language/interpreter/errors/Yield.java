package com.revolvingmadness.sculk.language.interpreter.errors;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.errors.Error;

public class Yield extends Error {
    public final BuiltinClass expression;

    public Yield(BuiltinClass expression) {
        super("Unexpected yield");

        this.expression = expression;
    }
}
