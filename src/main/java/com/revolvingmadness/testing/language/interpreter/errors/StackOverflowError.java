package com.revolvingmadness.testing.language.interpreter.errors;

import com.revolvingmadness.testing.language.errors.Error;

public class StackOverflowError extends Error {
    public StackOverflowError(String message) {
        super(message);
    }
}
