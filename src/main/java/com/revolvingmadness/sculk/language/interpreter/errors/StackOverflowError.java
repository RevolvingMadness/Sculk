package com.revolvingmadness.sculk.language.interpreter.errors;

import com.revolvingmadness.sculk.language.errors.Error;

public class StackOverflowError extends Error {
    public StackOverflowError(String message) {
        super(message);
    }
}
