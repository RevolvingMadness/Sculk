package com.revolvingmadness.testing.language.parser.errors;

import com.revolvingmadness.testing.language.InternalError;

public class ParseError extends InternalError {
    public ParseError(String message) {
        super(message);
    }
}
