package com.revolvingmadness.testing.language.interpreter.errors;

public class MaxArgumentError extends RuntimeException {
    public MaxArgumentError(String message) {
        super(message);
    }
}
