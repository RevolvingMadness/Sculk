package com.revolvingmadness.testing.language.lexer;

import com.revolvingmadness.testing.Testing;

import java.util.ArrayList;
import java.util.List;

public class LangLexer {
    private final String input;
    private int position;

    public LangLexer(String input) {
        this.input = input;
    }

    public Character current() {
        return this.input.charAt(this.position);
    }

    public Character next() {
        if (this.position+1 >= this.input.length()) {
            return null;
        }

        return this.input.charAt(this.position+1);
    }

    public Character consume() {
        return this.input.charAt(this.position++);
    }

    public List<Token> lex() {
        List<Token> tokens = new ArrayList<>();

        while (this.position < this.input.length()) {
            if (Character.isDigit(this.current()) || (this.current() == '.' && Character.isDigit(this.next()))) {
                tokens.add(this.lexDigit());
            } else if (this.current() == '+') {
                tokens.add(new Token(TokenType.PLUS));
                this.consume();
            } else if (this.current() == '-') {
                tokens.add(new Token(TokenType.MINUS));
                this.consume();
            } else if (this.current() == '*') {
                tokens.add(new Token(TokenType.STAR));
                this.consume();
            } else if (this.current() == '/') {
                tokens.add(new Token(TokenType.FSLASH));
                this.consume();
            } else if (this.current() == '^') {
                tokens.add(new Token(TokenType.CARET));
                this.consume();
            } else if (this.current() == '%') {
                tokens.add(new Token(TokenType.PERCENT));
                this.consume();
            } else if (Character.isWhitespace(this.current())) {
                this.consume();
            } else {
                Testing.LOGGER.error("Unknown token '" + this.current() + "'");
            }
        }

        tokens.add(new Token(TokenType.EOF));

        return tokens;
    }

    private Token lexDigit() {
        StringBuilder digit = new StringBuilder();
        boolean isFloat = false;

        while (this.position < this.input.length() && (Character.isDigit(this.current()) || this.current() == '.')) {
            if (this.current() == '.') {
                if (isFloat) {
                    break;
                }

                isFloat = true;
            }

            digit.append(this.consume());
        }

        String digitString = digit.toString();

        if (isFloat) {
            return new Token(TokenType.FLOAT, Double.parseDouble(digitString));
        }

        return new Token(TokenType.INTEGER, Integer.parseInt(digitString));
    }
}
