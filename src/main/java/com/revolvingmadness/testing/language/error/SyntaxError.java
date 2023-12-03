package com.revolvingmadness.testing.language.error;

public class SyntaxError extends RuntimeException {
    public SyntaxError(String error) {
        super(error);
    }
}
