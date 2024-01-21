package com.revolvingmadness.sculk.language.errors;

public class DivisionByZeroError extends Error {
    public DivisionByZeroError() {
        super("Cannot divide by zero");
    }
}
