package com.revolvingmadness.testing.language.interpreter.errors;

public class Break extends RuntimeException {
    public Break() {
        super("Unexpected 'break' statement");
    }
}
