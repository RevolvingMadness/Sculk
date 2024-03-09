package com.revolvingmadness.sculk.language.lexer;

public class Token {
    public final int columnNumber;
    public final int lineNumber;
    public final TokenType type;
    public final Object value;

    public Token(int lineNumber, int columnNumber, TokenType type) {
        this(lineNumber, columnNumber, type, null);
    }

    public Token(int lineNumber, int columnNumber, TokenType type, Object value) {
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
        this.type = type;
        this.value = value;
    }

    public boolean isAccessModifier() {
        return this.type == TokenType.PUBLIC || this.type == TokenType.PRIVATE || this.type == TokenType.ABSTRACT || this.type == TokenType.STATIC || this.type == TokenType.CONST || this.type == TokenType.NONULL;
    }

    public boolean isAdditiveOperator() {
        return this.type == TokenType.PLUS || this.type == TokenType.HYPHEN;
    }

    public boolean isAndOperator() {
        return this.type == TokenType.DOUBLE_AMPERSAND || this.type == TokenType.DOUBLE_PIPE;
    }

    public boolean isEqualityOperator() {
        return this.type == TokenType.EQUAL_TO || this.type == TokenType.NOT_EQUAL_TO;
    }

    public boolean isExponentiationOperator() {
        return this.type == TokenType.CARET;
    }

    public boolean isIncrementOperator() {
        return this.type == TokenType.DOUBLE_PLUS || this.type == TokenType.DOUBLE_HYPHEN;
    }

    public boolean isMultiplicativeOperator() {
        return this.type == TokenType.STAR || this.type == TokenType.FSLASH || this.type == TokenType.PERCENT;
    }

    public boolean isPostfixOperator() {
        return this.type == TokenType.DOUBLE_PLUS || this.type == TokenType.DOUBLE_HYPHEN;
    }

    public boolean isRelationOperator() {
        return this.type == TokenType.GREATER_THAN || this.type == TokenType.GREATER_THAN_OR_EQUAL_TO || this.type == TokenType.LESS_THAN || this.type == TokenType.LESS_THAN_OR_EQUAL_TO || this.type == TokenType.INSTANCEOF || this.type == TokenType.SPACESHIP;
    }

    public boolean isUnaryOperator() {
        return this.type == TokenType.HYPHEN || this.type == TokenType.EXCLAMATION_MARK || this.type == TokenType.PLUS || this.type == TokenType.DOUBLE_PLUS || this.type == TokenType.DOUBLE_HYPHEN;
    }

    public boolean isShortBinaryOperator() {
        return this.type == TokenType.PLUS_EQUALS || this.type == TokenType.HYPHEN_EQUALS || this.type == TokenType.STAR_EQUALS || this.type == TokenType.FSLASH_EQUALS || this.type == TokenType.CARET_EQUALS || this.type == TokenType.PERCENT_EQUALS || this.type == TokenType.EQUALS;
    }
}
