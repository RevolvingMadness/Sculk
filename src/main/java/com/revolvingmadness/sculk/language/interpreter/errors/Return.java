package com.revolvingmadness.sculk.language.interpreter.errors;


import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.errors.Error;

public class Return extends Error {
    public final BuiltinClass value;

    public Return(BuiltinClass value) {
        super("Unexpected 'return' statement");
        this.value = value;
    }
}
