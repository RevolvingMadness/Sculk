package com.revolvingmadness.testing.language.interpreter.errors;

public class InterpreterError extends InternalError {
    public InterpreterError(String message) {
        super(message);
    }
}
