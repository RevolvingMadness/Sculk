package com.revolvingmadness.testing.language.interpreter.errors;

public class ValueError extends RuntimeException {
    public ValueError(String message) {
        super(message);
    }
}
