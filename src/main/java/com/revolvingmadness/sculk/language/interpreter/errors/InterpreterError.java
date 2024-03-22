package com.revolvingmadness.sculk.language.interpreter.errors;

import com.revolvingmadness.sculk.language.errors.InternalError;

public class InterpreterError extends InternalError {
    public InterpreterError(String message) {
        super(message);
    }
}
