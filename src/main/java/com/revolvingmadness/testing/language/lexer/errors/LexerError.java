package com.revolvingmadness.testing.language.lexer.errors;

import com.revolvingmadness.testing.language.InternalError;

public class LexerError extends InternalError {
    public LexerError(String message) {
        super(message);
    }
}
