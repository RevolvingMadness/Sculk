package com.revolvingmadness.testing.language.lexer.error;

public class LexerError extends RuntimeException {
    public LexerError(String error) {
        super(error);
    }
}
