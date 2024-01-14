package com.revolvingmadness.sculk.language.interpreter.errors;

import com.revolvingmadness.sculk.language.errors.Error;

public class Continue extends Error {
    public Continue() {
        super("Unexpected 'continue' statement");
    }
}
