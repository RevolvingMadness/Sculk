package com.revolvingmadness.testing.language.lexer;

import com.revolvingmadness.testing.language.ErrorHolder;

import java.util.List;

public enum TokenType {
    FLOAT, INTEGER, PLUS, HYPHEN, STAR, FSLASH, CARET, PERCENT, IDENTIFIER, SEMICOLON, TRUE, FALSE, EQUALS, LEFT_PARENTHESIS, RIGHT_PARENTHESIS, EXCLAMATION_MARK, DOUBLE_PLUS, DOUBLE_HYPHEN, SINGLE_QUOTE, STRING, COLON, RESOURCE, IMPORT, EQUAL_TO, NOT_EQUAL_TO, GREATER_THAN_OR_EQUAL_TO, GREATER_THAN, LESS_THAN, LESS_THAN_OR_EQUAL_TO, IF, LEFT_BRACE, RIGHT_BRACE, WHILE, FOR, FUNCTION, COMMA, RIGHT_ARROW, LEFT_ARROW, NULL, RETURN, BREAK, CONTINUE, LEFT_BRACKET, RIGHT_BRACKET, CONST, AMPERSAND, DOUBLE_AMPERSAND, DOUBLE_PIPE, PIPE, PERIOD, VAR, CLASS, EXTENDS, ELSE, INSTANCE_OF, STATIC, DELETE, ABSTRACT, EOF, PUBLIC, PRIVATE;

    public static void validateClassAccessModifiers(List<TokenType> accessModifiers) {
        accessModifiers.forEach(accessModifier -> {
            if (!accessModifier.isClassAccessModifier()) {
                throw ErrorHolder.invalidClassAccessModifier(accessModifier);
            }
        });
    }

    public static void validateFieldAccessModifiers(List<TokenType> accessModifiers) {
        accessModifiers.forEach(accessModifier -> {
            if (!accessModifier.isFieldAccessModifier()) {
                throw ErrorHolder.invalidFieldAccessModifier(accessModifier);
            }
        });
    }

    public static void validateFunctionAccessModifiers(List<TokenType> accessModifiers) {
        accessModifiers.forEach(accessModifier -> {
            if (!accessModifier.isFunctionAccessModifier()) {
                throw ErrorHolder.invalidFunctionAccessModifier(accessModifier);
            }
        });
    }

    public static void validateMethodAccessModifiers(List<TokenType> accessModifiers) {
        accessModifiers.forEach(accessModifier -> {
            if (!accessModifier.isMethodAccessModifier()) {
                throw ErrorHolder.invalidMethodAccessModifier(accessModifier);
            }
        });
    }

    public static void validateVariableAccessModifiers(List<TokenType> accessModifiers) {
        accessModifiers.forEach(accessModifier -> {
            if (!accessModifier.isVariableAccessModifier()) {
                throw ErrorHolder.invalidVariableAccessModifier(accessModifier);
            }
        });
    }

    private boolean isClassAccessModifier() {
        return this == TokenType.CONST || this == TokenType.ABSTRACT;
    }

    private boolean isFieldAccessModifier() {
        return this == TokenType.CONST || this == TokenType.STATIC || this == TokenType.PUBLIC || this == TokenType.PRIVATE;
    }

    private boolean isFunctionAccessModifier() {
        return this == TokenType.CONST;
    }

    private boolean isMethodAccessModifier() {
        return this == TokenType.CONST || this == TokenType.STATIC || this == TokenType.ABSTRACT || this == TokenType.PUBLIC || this == TokenType.PRIVATE;
    }

    private boolean isVariableAccessModifier() {
        return this == TokenType.CONST;
    }
}
