package com.revolvingmadness.testing.language.parser.error;

public class SyntaxError extends RuntimeException {
    public SyntaxError(String error) {
        super(error);
    }
}
