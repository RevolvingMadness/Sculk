package com.revolvingmadness.testing.language.interpreter.errors;

public class StackOverflowError extends RuntimeException {
    public StackOverflowError(String message) {
        super(message);
    }
}
