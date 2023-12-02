package com.revolvingmadness.testing.language.lexer;

public class Token {
    public final TokenType type;
    public final Object value;

    public Token(TokenType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Token(TokenType type) {
        this.type = type;
        this.value = null;
    }

    @Override
    public String toString() {
        if (this.value == null) {
            return "Token(type=" + type + ")";
        }

        return "Token(type=" + type + ", value=" + value + ")";
    }
}
