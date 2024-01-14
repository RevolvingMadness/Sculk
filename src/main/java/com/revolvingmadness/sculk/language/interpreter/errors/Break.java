package com.revolvingmadness.sculk.language.interpreter.errors;

import com.revolvingmadness.sculk.language.errors.Error;

public class Break extends Error {
    public Break() {
        super("Unexpected 'break' statement");
    }
}
