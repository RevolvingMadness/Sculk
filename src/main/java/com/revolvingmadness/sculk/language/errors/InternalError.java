package com.revolvingmadness.sculk.language.errors;

public abstract class InternalError extends Error {
    public InternalError(String message) {
        super(message);
    }
}
