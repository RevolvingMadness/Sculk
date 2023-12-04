package com.revolvingmadness.testing.language.lexer;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.language.lexer.errors.LexerError;

import java.util.ArrayList;
import java.util.List;

public class LangLexer {
    private final String input;
    private Integer position;

    public LangLexer(String input) {
        this.input = input;
        this.position = 0;
    }

    public Character consume() {
        return this.input.charAt(this.position++);
    }

    public boolean current(Character character) {
        return this.current() == character;
    }

    public Character current() {
        return this.input.charAt(this.position);
    }

    public List<Token> lex() {
        List<Token> tokens = new ArrayList<>();

        while (this.position < this.input.length()) {
            if (Character.isDigit(this.current()) || (this.current('.') && Character.isDigit(this.next()))) {
                tokens.add(this.lexDigit());
            } else if (this.current('+')) {
                this.consume();

                if (this.current('+')) {
                    this.consume();

                    tokens.add(new Token(TokenType.DOUBLE_PLUS));
                } else {
                    tokens.add(new Token(TokenType.PLUS));
                }
            } else if (this.current('-')) {
                this.consume();

                if (this.current('-')) {
                    tokens.add(new Token(TokenType.DOUBLE_HYPHEN));
                } else {
                    tokens.add(new Token(TokenType.HYPHEN));
                }
            } else if (this.current('*')) {
                tokens.add(new Token(TokenType.STAR));
                this.consume();
            } else if (this.current('/')) {
                this.consume();

                if (this.current('/')) {
                    this.consume();
                    this.lexComment();
                } else {
                    tokens.add(new Token(TokenType.FSLASH));
                }
            } else if (this.current('^')) {
                tokens.add(new Token(TokenType.CARET));
                this.consume();
            } else if (this.current('%')) {
                tokens.add(new Token(TokenType.PERCENT));
                this.consume();
            } else if (Character.isWhitespace(this.current())) {
                this.consume();
            } else if (Character.isAlphabetic(this.current())) {
                tokens.add(this.lexIdentifier(true));
            } else if (this.current(';')) {
                tokens.add(new Token(TokenType.SEMICOLON));
                this.consume();
            } else if (this.current('=')) {
                this.consume();

                if (this.current('=')) {
                    tokens.add(new Token(TokenType.EQUAL_TO));
                    this.consume();
                } else {
                    tokens.add(new Token(TokenType.EQUALS));
                }
            } else if (this.current('(')) {
                tokens.add(new Token(TokenType.LEFT_PARENTHESIS));
                this.consume();
            } else if (this.current(')')) {
                tokens.add(new Token(TokenType.RIGHT_PARENTHESIS));
                this.consume();
            } else if (this.current('!')) {
                this.consume();

                if (this.current('=')) {
                    tokens.add(new Token(TokenType.NOT_EQUAL_TO));
                    this.consume();
                } else {
                    tokens.add(new Token(TokenType.EXCLAMATION_MARK));
                }
            } else if (this.current('"')) {
                this.consume();
                tokens.add(this.lexString());
                this.consume();
            } else if (this.current('\'')) {
                tokens.add(new Token(TokenType.SINGLE_QUOTE));
                this.consume();
            } else if (this.current(':')) {
                tokens.add(new Token(TokenType.COLON));
                this.consume();
            } else if (this.current('>')) {
                this.consume();

                if (this.current('=')) {
                    tokens.add(new Token(TokenType.GREATER_THAN_OR_EQUAL_TO));
                    this.consume();
                } else {
                    tokens.add(new Token(TokenType.GREATER_THAN));
                }
            } else if (this.current('<')) {
                this.consume();

                if (this.current('=')) {
                    tokens.add(new Token(TokenType.LESS_THAN_OR_EQUAL_TO));
                    this.consume();
                } else {
                    tokens.add(new Token(TokenType.LESS_THAN));
                }
            } else {
                throw new LexerError("Unknown token '" + this.current() + "'");
            }
        }

        tokens.add(new Token(TokenType.EOF));

        return tokens;
    }

    private Token lexString() {
        StringBuilder string = new StringBuilder();

        while (this.position < this.input.length() && !this.current('"')) {
            string.append(this.consume());
        }

        return new Token(TokenType.STRING, string.toString());
    }

    private void lexComment() {
        while (this.position < this.input.length() && !this.current('\n')) {
            this.consume();
        }
    }

    private Token lexDigit() {
        StringBuilder digit = new StringBuilder();
        boolean isFloat = false;

        while (this.position < this.input.length() && (Character.isDigit(this.current()) || this.current('.'))) {
            if (this.current('.')) {
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

    private Token lexIdentifier(boolean checkForKeywords) {
        StringBuilder identifier = new StringBuilder();

        while (this.position < this.input.length() && (Character.isLetterOrDigit(this.current()) || this.current('_'))) {
            identifier.append(this.consume());
        }

        String identifierString = identifier.toString();

        if (this.current(':')) {
            this.consume();

            Token resourcePath = this.lexIdentifier(false);

            return new Token(TokenType.RESOURCE, identifierString + ":" + resourcePath.value);
        }

        if (Testing.keywords.containsKey(identifierString) && checkForKeywords) {
            return new Token(Testing.keywords.get(identifierString));
        }

        return new Token(TokenType.IDENTIFIER, identifierString);
    }

    public Character next() {
        if (this.position + 1 >= this.input.length()) {
            return null;
        }

        return this.input.charAt(this.position + 1);
    }
}
