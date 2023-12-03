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

    public boolean isAdditionOperator() {
        return this.type == TokenType.PLUS || this.type == TokenType.DASH;
    }

    public boolean isMultiplicationOperator() {
        return this.type == TokenType.STAR || this.type == TokenType.FSLASH || this.type == TokenType.PERCENT;
    }

    public boolean isExponentiationOperator() {
        return this.type == TokenType.CARET;
    }

    @Override
    public String toString() {
        if (this.value == null) {
            return "Token(type=" + type + ")";
        }

        return "Token(type=" + type + ", value=" + value + ")";
    }
}
