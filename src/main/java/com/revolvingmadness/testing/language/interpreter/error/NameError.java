package com.revolvingmadness.testing.language.interpreter.error;

public class NameError extends RuntimeException {
    public NameError(String error) {
        super(error);
    }
}
