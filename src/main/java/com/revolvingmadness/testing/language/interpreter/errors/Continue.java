package com.revolvingmadness.testing.language.interpreter.errors;

import com.revolvingmadness.testing.language.errors.Error;

public class Continue extends Error {
    public Continue() {
        super("Unexpected 'continue' statement");
    }
}
