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
        return this.type == TokenType.PLUS || this.type == TokenType.HYPHEN;
    }

    public boolean isAndOperator() {
        return this.type == TokenType.DOUBLE_AMPERSAND || this.type == TokenType.DOUBLE_PIPE;
    }

    public boolean isBinaryOperator() {
        return this.isAdditionOperator() || this.isMultiplicationOperator() || this.isExponentiationOperator();
    }

    public boolean isExponentiationOperator() {
        return this.type == TokenType.CARET;
    }

    public boolean isIncrementOperator() {
        return this.type == TokenType.DOUBLE_PLUS || this.type == TokenType.DOUBLE_HYPHEN;
    }

    public boolean isLogicalOperator() {
        return this.type == TokenType.EQUAL_TO || this.type == TokenType.NOT_EQUAL_TO || this.type == TokenType.GREATER_THAN || this.type == TokenType.GREATER_THAN_OR_EQUAL_TO || this.type == TokenType.LESS_THAN || this.type == TokenType.LESS_THAN_OR_EQUAL_TO;
    }

    public boolean isMultiplicationOperator() {
        return this.type == TokenType.STAR || this.type == TokenType.FSLASH || this.type == TokenType.PERCENT;
    }

    public boolean isUnaryOperator() {
        return this.type == TokenType.HYPHEN || this.type == TokenType.EXCLAMATION_MARK;
    }

    @Override
    public String toString() {
        if (this.value == null) {
            return "Token(type=" + this.type + ")";
        }

        return "Token(type=" + this.type + ", value=" + this.value + ")";
    }
}
