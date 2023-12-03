package com.revolvingmadness.testing.language.errors;

public class SyntaxError extends RuntimeException {
    public SyntaxError(String error) {
        super(error);
    }
}
