package com.revolvingmadness.testing.language.lexer.error;

import com.revolvingmadness.testing.language.InternalError;

public class LexerError extends InternalError {
    public LexerError(String message) {
        super(message);
    }
}
