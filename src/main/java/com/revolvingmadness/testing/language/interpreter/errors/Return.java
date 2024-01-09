package com.revolvingmadness.testing.language.interpreter.errors;


import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;

public class Return extends RuntimeException {
    public final BuiltinClass value;

    public Return(BuiltinClass value) {
        super("Unexpected 'return' statement");
        this.value = value;
    }
}
