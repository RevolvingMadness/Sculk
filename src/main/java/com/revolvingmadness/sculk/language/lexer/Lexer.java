package com.revolvingmadness.sculk.language.lexer;

import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.language.errors.LexError;
import com.revolvingmadness.sculk.language.errors.SyntaxError;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;
    private final List<Token> tokens;
    private int currentColumnNumber;
    private int currentLineNumber;
    private int position;

    public Lexer(String input) {
        this.input = input;

        this.tokens = new ArrayList<>();
        this.currentColumnNumber = 1;
        this.currentLineNumber = 1;
        this.position = 0;
    }

    public void addToken(TokenType type, Object value) {
        this.tokens.add(new Token(this.currentLineNumber, this.currentColumnNumber, type, value));
    }

    public void addToken(TokenType type) {
        this.addToken(type, null);
    }

    public Character consume() {
        if (this.position >= this.input.length()) {
            return '\0';
        }

        char character = this.input.charAt(this.position++);

        if (character == '\n') {
            this.currentLineNumber++;
            this.currentColumnNumber = 1;
        } else {
            this.currentColumnNumber++;
        }

        return character;
    }

    @SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
    private char consume(Character character) {
        Character current = this.consume();

        if (current.charValue() != character) {
            if (current == '\0') {
                throw new LexError("Expected '" + character + "' got 'EOF' at " + this.currentLineNumber + ":" + this.currentColumnNumber);
            }

            throw new LexError("Expected '" + character + "' got '" + current + "' at " + this.currentLineNumber + ":" + this.currentColumnNumber);
        }

        return current;
    }

    public boolean current(Character character) {
        return this.current() == character;
    }

    public Character current() {
        if (this.position >= this.input.length()) {
            return '\0';
        }

        return this.input.charAt(this.position);
    }

    public List<Token> lex() {
        while (this.position < this.input.length()) {
            if (Character.isDigit(this.current()) || (this.current('.') && Character.isDigit(this.next()))) {
                this.tokens.add(this.lexDigit());
            } else if (this.current('+')) {
                this.consume();

                if (this.current('+')) {
                    this.consume();

                    this.addToken(TokenType.DOUBLE_PLUS);
                } else {
                    this.addToken(TokenType.PLUS);
                }
            } else if (this.current('-')) {
                this.consume();

                if (this.current('-')) {
                    this.consume();
                    this.addToken(TokenType.DOUBLE_HYPHEN);
                } else if (this.current('>')) {
                    this.consume();
                    this.addToken(TokenType.RIGHT_ARROW);
                } else {
                    this.addToken(TokenType.HYPHEN);
                }
            } else if (this.current('*')) {
                this.consume();
                this.addToken(TokenType.STAR);
            } else if (this.current('/')) {
                this.consume();

                if (this.current('/')) {
                    this.consume();
                    this.lexComment();
                } else if (this.current('*')) {
                    this.consume();
                    this.lexMultilineComment();
                } else {
                    this.addToken(TokenType.FSLASH);
                }
            } else if (this.current('^')) {
                this.consume();
                this.addToken(TokenType.CARET);
            } else if (this.current('%')) {
                this.consume();
                this.addToken(TokenType.PERCENT);
            } else if (Character.isWhitespace(this.current())) {
                this.consume();
            } else if (Character.isAlphabetic(this.current()) || this.current('_')) {
                this.tokens.add(this.lexIdentifier());
            } else if (this.current(';')) {
                this.consume();
                this.addToken(TokenType.SEMICOLON);
            } else if (this.current('=')) {
                this.consume();

                if (this.current('=')) {
                    this.consume();
                    this.addToken(TokenType.EQUAL_TO);
                } else {
                    this.addToken(TokenType.EQUALS);
                }
            } else if (this.current('(')) {
                this.consume();
                this.addToken(TokenType.LEFT_PARENTHESIS);
            } else if (this.current(')')) {
                this.consume();
                this.addToken(TokenType.RIGHT_PARENTHESIS);
            } else if (this.current('!')) {
                this.consume();

                if (this.current('=')) {
                    this.consume();
                    this.addToken(TokenType.NOT_EQUAL_TO);
                } else {
                    this.addToken(TokenType.EXCLAMATION_MARK);
                }
            } else if (this.current('"')) {
                this.consume();
                this.tokens.add(this.lexString());
                this.consume();
            } else if (this.current('\'')) {
                this.consume();
                this.addToken(TokenType.SINGLE_QUOTE);
            } else if (this.current(':')) {
                this.consume();
                this.addToken(TokenType.COLON);
            } else if (this.current('>')) {
                this.consume();

                if (this.current('=')) {
                    this.consume();
                    this.addToken(TokenType.GREATER_THAN_OR_EQUAL_TO);
                } else {
                    this.addToken(TokenType.GREATER_THAN);
                }
            } else if (this.current('<')) {
                this.consume();

                if (this.current('=')) {
                    this.consume();

                    if (this.current('>')) {
                        this.consume();

                        this.addToken(TokenType.SPACESHIP);
                    } else {
                        this.addToken(TokenType.LESS_THAN_OR_EQUAL_TO);
                    }
                } else if (this.current('-')) {
                    this.consume();
                    this.addToken(TokenType.LEFT_ARROW);
                } else {
                    this.addToken(TokenType.LESS_THAN);
                }
            } else if (this.current('{')) {
                this.consume();
                this.addToken(TokenType.LEFT_BRACE);
            } else if (this.current('}')) {
                this.consume();
                this.addToken(TokenType.RIGHT_BRACE);
            } else if (this.current(',')) {
                this.consume();
                this.addToken(TokenType.COMMA);
            } else if (this.current('[')) {
                this.consume();
                this.addToken(TokenType.LEFT_BRACKET);
            } else if (this.current(']')) {
                this.consume();
                this.addToken(TokenType.RIGHT_BRACKET);
            } else if (this.current('&')) {
                this.consume();

                if (this.current('&')) {
                    this.consume();
                    this.addToken(TokenType.DOUBLE_AMPERSAND);
                } else {
                    this.addToken(TokenType.AMPERSAND);
                }
            } else if (this.current('|')) {
                this.consume();

                if (this.current('|')) {
                    this.consume();
                    this.addToken(TokenType.DOUBLE_PIPE);
                } else {
                    this.addToken(TokenType.PIPE);
                }
            } else if (this.current('.')) {
                this.consume();
                this.addToken(TokenType.PERIOD);
            } else {
                throw new SyntaxError("Unexpected character '" + this.current() + "'");
            }
        }

        this.addToken(TokenType.EOF);

        return this.tokens;
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
            return new Token(this.currentLineNumber, this.currentColumnNumber, TokenType.FLOAT, Double.parseDouble(digitString));
        }

        return new Token(this.currentLineNumber, this.currentColumnNumber, TokenType.INTEGER, Integer.parseInt(digitString));
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
            default -> throw new SyntaxError("Unsupported escape character '" + escapeChar + "'");
        };
    }

    private Token lexIdentifier() {
        StringBuilder identifier = new StringBuilder();

        while (this.position < this.input.length() && (Character.isLetterOrDigit(this.current()) || this.current('_'))) {
            identifier.append(this.consume());
        }

        String identifierString = identifier.toString();

        if (identifierString.isEmpty()) {
            throw new SyntaxError("Expected identifier at " + this.currentLineNumber + ":" + this.currentColumnNumber);
        }

        if (Sculk.keywords.containsKey(identifierString)) {
            return new Token(this.currentLineNumber, this.currentColumnNumber, Sculk.keywords.get(identifierString));
        }

        if (this.current(':')) {
            this.consume();

            StringBuilder path = new StringBuilder((String) this.lexIdentifier().value);

            while (this.current('/')) {
                this.consume();

                path.append("/");
                path.append((String) this.lexIdentifier().value);
            }

            return new Token(this.currentLineNumber, this.currentColumnNumber, TokenType.RESOURCE, new Identifier(identifierString, path.toString()));
        }

        return new Token(this.currentLineNumber, this.currentColumnNumber, TokenType.IDENTIFIER, identifierString);
    }

    private void lexMultilineComment() {
        while (this.position < this.input.length() && !this.current('*')) {
            this.consume();
        }

        this.consume();
        this.consume('/');
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

        return new Token(this.currentLineNumber, this.currentColumnNumber, TokenType.STRING, string.toString());
    }

    public Character next() {
        if (this.position + 1 >= this.input.length()) {
            return null;
        }

        return this.input.charAt(this.position + 1);
    }
}
