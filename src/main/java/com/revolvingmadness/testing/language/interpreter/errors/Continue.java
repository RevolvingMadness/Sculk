package com.revolvingmadness.testing.language.interpreter.errors;

public class Continue extends RuntimeException {
    public Continue() {
        super("Unexpected 'continue' statement");
    }
}
