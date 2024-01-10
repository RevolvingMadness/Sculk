package com.revolvingmadness.testing.language.interpreter.errors;

import com.revolvingmadness.testing.language.errors.Error;

public class Break extends Error {
    public Break() {
        super("Unexpected 'break' statement");
    }
}
