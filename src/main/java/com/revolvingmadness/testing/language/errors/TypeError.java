package com.revolvingmadness.testing.language.errors;

public class TypeError extends RuntimeException {
    public TypeError(String error) {
        super(error);
    }
}
