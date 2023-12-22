package com.revolvingmadness.testing.language.lexer;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.language.lexer.errors.LexerError;

import java.util.ArrayList;
import java.util.List;

public class LangLexer {
    public final String input;
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
        if (this.position >= this.input.length()) {
            return null;
        }

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
                    this.consume();
                    tokens.add(new Token(TokenType.DOUBLE_HYPHEN));
                } else if (this.current('>')) {
                    this.consume();
                    tokens.add(new Token(TokenType.RIGHT_ARROW));
                } else {
                    tokens.add(new Token(TokenType.HYPHEN));
                }
            } else if (this.current('*')) {
                this.consume();
                tokens.add(new Token(TokenType.STAR));
            } else if (this.current('/')) {
                this.consume();

                if (this.current('/')) {
                    this.consume();
                    this.lexComment();
                } else {
                    tokens.add(new Token(TokenType.FSLASH));
                }
            } else if (this.current('^')) {
                this.consume();
                tokens.add(new Token(TokenType.CARET));
            } else if (this.current('%')) {
                this.consume();
                tokens.add(new Token(TokenType.PERCENT));
            } else if (Character.isWhitespace(this.current())) {
                this.consume();
            } else if (Character.isAlphabetic(this.current())) {
                tokens.add(this.lexIdentifier());
            } else if (this.current(';')) {
                this.consume();
                tokens.add(new Token(TokenType.SEMICOLON));
            } else if (this.current('=')) {
                this.consume();

                if (this.current('=')) {
                    this.consume();
                    tokens.add(new Token(TokenType.EQUAL_TO));
                } else {
                    tokens.add(new Token(TokenType.EQUALS));
                }
            } else if (this.current('(')) {
                this.consume();
                tokens.add(new Token(TokenType.LEFT_PARENTHESIS));
            } else if (this.current(')')) {
                this.consume();
                tokens.add(new Token(TokenType.RIGHT_PARENTHESIS));
            } else if (this.current('!')) {
                this.consume();

                if (this.current('=')) {
                    this.consume();
                    tokens.add(new Token(TokenType.NOT_EQUAL_TO));
                } else {
                    tokens.add(new Token(TokenType.EXCLAMATION_MARK));
                }
            } else if (this.current('"')) {
                this.consume();
                tokens.add(this.lexString());
                this.consume();
            } else if (this.current('\'')) {
                this.consume();
                tokens.add(new Token(TokenType.SINGLE_QUOTE));
            } else if (this.current(':')) {
                this.consume();
                tokens.add(new Token(TokenType.COLON));
            } else if (this.current('>')) {
                this.consume();

                if (this.current('=')) {
                    this.consume();
                    tokens.add(new Token(TokenType.GREATER_THAN_OR_EQUAL_TO));
                } else {
                    tokens.add(new Token(TokenType.GREATER_THAN));
                }
            } else if (this.current('<')) {
                this.consume();

                if (this.current('=')) {
                    this.consume();
                    tokens.add(new Token(TokenType.LESS_THAN_OR_EQUAL_TO));
                } else if (this.current('-')) {
                    this.consume();
                    tokens.add(new Token(TokenType.LEFT_ARROW));
                } else {
                    tokens.add(new Token(TokenType.LESS_THAN));
                }
            } else if (this.current('{')) {
                this.consume();
                tokens.add(new Token(TokenType.LEFT_BRACE));
            } else if (this.current('}')) {
                this.consume();
                tokens.add(new Token(TokenType.RIGHT_BRACE));
            } else if (this.current(',')) {
                this.consume();
                tokens.add(new Token(TokenType.COMMA));
            } else if (this.current('[')) {
                this.consume();
                tokens.add(new Token(TokenType.LEFT_BRACKET));
            } else if (this.current(']')) {
                this.consume();
                tokens.add(new Token(TokenType.RIGHT_BRACKET));
            } else if (this.current('&')) {
                this.consume();

                if (this.current('&')) {
                    this.consume();
                    tokens.add(new Token(TokenType.DOUBLE_AMPERSAND));
                } else {
                    tokens.add(new Token(TokenType.AMPERSAND));
                }
            } else if (this.current('|')) {
                this.consume();

                if (this.current('|')) {
                    this.consume();
                    tokens.add(new Token(TokenType.DOUBLE_PIPE));
                } else {
                    tokens.add(new Token(TokenType.PIPE));
                }
            } else if (this.current('.')) {
                this.consume();
                tokens.add(new Token(TokenType.PERIOD));
            } else {
                throw new LexerError("Unknown token '" + this.current() + "'");
            }
        }

        tokens.add(new Token(TokenType.EOF));

        return tokens;
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

    private Character lexEscapeSequence() {
        this.consume();
        Character escapeChar = this.consume();

        return switch (escapeChar) {
            case 't' -> '\t';
            case 'b' -> '\b';
            case 'n' -> '\n';
            case 'r' -> '\r';
            case 'f' -> '\f';
            case '\'' -> '\'';
            case '"' -> '"';
            case '\\' -> '\\';
            default -> throw new LexerError("Unsupported escape character '" + escapeChar + "'");
        };
    }

    private Token lexIdentifier() {
        StringBuilder identifier = new StringBuilder();

        while (this.position < this.input.length() && (Character.isLetterOrDigit(this.current()) || this.current('_'))) {
            identifier.append(this.consume());
        }

        String identifierString = identifier.toString();

        if (Testing.keywords.containsKey(identifierString)) {
            return new Token(Testing.keywords.get(identifierString));
        }

        if (this.current(':')) {
            this.consume();

            Token resourcePath = this.lexIdentifier();

            return new Token(TokenType.RESOURCE, identifierString + ":" + resourcePath.value);
        }

        return new Token(TokenType.IDENTIFIER, identifierString);
    }

    private Token lexString() {
        StringBuilder string = new StringBuilder();

        while (this.position < this.input.length() && !this.current('"')) {
            if (this.current('\\')) {
                string.append(this.lexEscapeSequence());
            } else {
                string.append(this.consume());
            }
        }

        return new Token(TokenType.STRING, string.toString());
    }

    public Character next() {
        if (this.position + 1 >= this.input.length()) {
            return null;
        }

        return this.input.charAt(this.position + 1);
    }
}
