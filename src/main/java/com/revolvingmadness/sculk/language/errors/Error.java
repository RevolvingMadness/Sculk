package com.revolvingmadness.sculk.language.errors;

public abstract class Error extends RuntimeException {
    public final String message;

    public Error(String message) {
        this.message = message;
    }
}
