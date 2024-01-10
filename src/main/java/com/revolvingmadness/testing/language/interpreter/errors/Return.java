package com.revolvingmadness.testing.language.interpreter.errors;


import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.errors.Error;

public class Return extends Error {
    public final BuiltinClass value;

    public Return(BuiltinClass value) {
        super("Unexpected 'return' statement");
        this.value = value;
    }
}
