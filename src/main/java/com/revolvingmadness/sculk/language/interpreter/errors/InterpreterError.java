package com.revolvingmadness.sculk.language.interpreter.errors;

public class InterpreterError extends InternalError {
    public InterpreterError(String message) {
        super(message);
    }
}
