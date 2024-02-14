package com.revolvingmadness.sculk.language.lexer;

import com.revolvingmadness.sculk.language.errors.SyntaxError;

import java.util.List;

public enum TokenType {
    FLOAT, INTEGER, PLUS, HYPHEN, STAR, FSLASH, CARET, PERCENT, IDENTIFIER, SEMICOLON, TRUE, FALSE, EQUALS, LEFT_PARENTHESIS, RIGHT_PARENTHESIS, EXCLAMATION_MARK, DOUBLE_PLUS, DOUBLE_HYPHEN, SINGLE_QUOTE, STRING, COLON, IMPORT, EQUAL_TO, NOT_EQUAL_TO, GREATER_THAN_OR_EQUAL_TO, GREATER_THAN, LESS_THAN, LESS_THAN_OR_EQUAL_TO, IF, LEFT_BRACE, RIGHT_BRACE, WHILE, FOR, FUNCTION, COMMA, RIGHT_ARROW, LEFT_ARROW, NULL, RETURN, BREAK, CONTINUE, LEFT_BRACKET, RIGHT_BRACKET, CONST, AMPERSAND, DOUBLE_AMPERSAND, DOUBLE_PIPE, PIPE, PERIOD, VAR, CLASS, EXTENDS, ELSE, INSTANCEOF, STATIC, DELETE, ABSTRACT, EOF, PUBLIC, PRIVATE, SPACESHIP, FOREACH, ENUM, AS, FROM, SWITCH, CASE, DEFAULT, YIELD, QUESTION_MARK, DOUBLE_RIGHT_ARROW;

    public static void validateClassAccessModifiers(List<TokenType> accessModifiers) {
        accessModifiers.forEach(accessModifier -> {
            if (!accessModifier.isClassAccessModifier()) {
                throw new SyntaxError("Invalid class access modifier '" + accessModifier + "'");
            }
        });
    }

    public static void validateEnumAccessModifiers(List<TokenType> accessModifiers) {
        accessModifiers.forEach(accessModifier -> {
            if (!accessModifier.isEnumAccessModifier()) {
                throw new SyntaxError("Invalid enum access modifier '" + accessModifier + "'");
            }
        });
    }

    public static void validateFieldAccessModifiers(List<TokenType> accessModifiers) {
        accessModifiers.forEach(accessModifier -> {
            if (!accessModifier.isFieldAccessModifier()) {
                throw new SyntaxError("Invalid field access modifier '" + accessModifier + "'");
            }
        });
    }

    public static void validateFunctionAccessModifiers(List<TokenType> accessModifiers) {
        accessModifiers.forEach(accessModifier -> {
            if (!accessModifier.isFunctionAccessModifier()) {
                throw new SyntaxError("Invalid function access modifier '" + accessModifier + "'");
            }
        });
    }

    public static void validateMethodAccessModifiers(List<TokenType> accessModifiers) {
        accessModifiers.forEach(accessModifier -> {
            if (!accessModifier.isMethodAccessModifier()) {
                throw new SyntaxError("Invalid method access modifier '" + accessModifier + "'");
            }
        });
    }

    public static void validateVariableAccessModifiers(List<TokenType> accessModifiers) {
        accessModifiers.forEach(accessModifier -> {
            if (!accessModifier.isVariableAccessModifier()) {
                throw new SyntaxError("Invalid variable access modifier '" + accessModifier + "'");
            }
        });
    }

    private boolean isClassAccessModifier() {
        return this == TokenType.CONST || this == TokenType.ABSTRACT;
    }

    private boolean isEnumAccessModifier() {
        return this == TokenType.CONST;
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

    @Override
    public String toString() {
        return switch (this) {
            case FLOAT -> "<float>";
            case INTEGER -> "<integer>";
            case PLUS -> "+";
            case HYPHEN -> "-";
            case STAR -> "*";
            case FSLASH -> "/";
            case CARET -> "^";
            case PERCENT -> "%";
            case IDENTIFIER -> "<identifier>";
            case SEMICOLON -> ";";
            case TRUE -> "true";
            case FALSE -> "false";
            case EQUALS -> "=";
            case LEFT_PARENTHESIS -> "(";
            case RIGHT_PARENTHESIS -> ")";
            case EXCLAMATION_MARK -> "!";
            case DOUBLE_PLUS -> "++";
            case DOUBLE_HYPHEN -> "--";
            case SINGLE_QUOTE -> "'";
            case STRING -> "STRING";
            case COLON -> ":";
            case IMPORT -> "import";
            case EQUAL_TO -> "==";
            case NOT_EQUAL_TO -> "!=";
            case GREATER_THAN_OR_EQUAL_TO -> ">=";
            case GREATER_THAN -> ">";
            case LESS_THAN -> "<";
            case LESS_THAN_OR_EQUAL_TO -> "<=";
            case IF -> "if";
            case LEFT_BRACE -> "{";
            case RIGHT_BRACE -> "}";
            case WHILE -> "while";
            case FOR -> "for";
            case FUNCTION -> "function";
            case COMMA -> ",";
            case RIGHT_ARROW -> "->";
            case LEFT_ARROW -> "<-";
            case NULL -> "null";
            case RETURN -> "return";
            case BREAK -> "break";
            case CONTINUE -> "continue";
            case LEFT_BRACKET -> "[";
            case RIGHT_BRACKET -> "]";
            case CONST -> "const";
            case AMPERSAND -> "&";
            case DOUBLE_AMPERSAND -> "&&";
            case DOUBLE_PIPE -> "||";
            case PIPE -> "|";
            case PERIOD -> ".";
            case VAR -> "var";
            case CLASS -> "class";
            case EXTENDS -> "extends";
            case ELSE -> "else";
            case INSTANCEOF -> "instanceof";
            case STATIC -> "static";
            case DELETE -> "delete";
            case ABSTRACT -> "abstract";
            case EOF -> "EOF";
            case PUBLIC -> "public";
            case PRIVATE -> "private";
            case SPACESHIP -> "<=>";
            case FOREACH -> "foreach";
            case ENUM -> "enum";
            case AS -> "as";
            case FROM -> "from";
            case SWITCH -> "switch";
            case CASE -> "case";
            case DEFAULT -> "default";
            case YIELD -> "yield";
            case QUESTION_MARK -> "?";
            case DOUBLE_RIGHT_ARROW -> "=>";
        };
    }
}
