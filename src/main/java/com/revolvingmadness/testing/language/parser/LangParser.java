package com.revolvingmadness.testing.language.parser;

import com.revolvingmadness.testing.language.lexer.Token;
import com.revolvingmadness.testing.language.lexer.TokenType;
import com.revolvingmadness.testing.language.parser.error.ParseError;
import com.revolvingmadness.testing.language.parser.error.SyntaxError;
import com.revolvingmadness.testing.language.parser.nodes.*;

import java.util.List;

public class LangParser {
    private final List<Token> input;
    private Integer position;

    public LangParser(List<Token> input) {
        this.input = input;
        this.position = 0;
    }

    private Token current() {
        return this.input.get(this.position);
    }

    private Token consume() {
        return this.input.get(this.position++);
    }

    private Token consume(TokenType type) {
        Token token = this.input.get(this.position++);

        if (token.type != type) {
            throw new SyntaxError("Expected token type '" + type + "', but got '" + token.type + "'");
        }

        return token;
    }

    private Boolean current(TokenType type) {
        return this.current().type == type;
    }

    public ScriptNode parse() {
        ScriptNode program = new ScriptNode();

        while (!this.current(TokenType.EOF)) {
            program.statements.add(this.parseStatement());
        }

        return program;
    }

    private StatementNode parseStatement() {
        IdentifierExpressionNode type = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER).value);
        IdentifierExpressionNode name = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER).value);

        this.consume(TokenType.EQUALS);

        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(type, name, this.parseExpression());

        this.consume(TokenType.SEMICOLON);

        return assignmentStatementNode;
    }

    private ExpressionNode parseExpression() {
        return this.parseAdditionExpression();
    }

    private ExpressionNode parseAdditionExpression() {
        ExpressionNode left = this.parseMultiplicationExpression();

        while (this.current().isAdditionOperator()) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parseMultiplicationExpression();

            left = new BinaryExpressionNode(left, operator, right);
        }

        return left;
    }

    private ExpressionNode parseMultiplicationExpression() {
        ExpressionNode left = this.parseExponentiationExpression();

        while (this.current().isMultiplicationOperator()) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parseExponentiationExpression();

            left = new BinaryExpressionNode(left, operator, right);
        }

        return left;
    }

    private ExpressionNode parseExponentiationExpression() {
        ExpressionNode left = this.parsePrimaryExpression();

        while (this.current().isExponentiationOperator()) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parsePrimaryExpression();

            left = new BinaryExpressionNode(left, operator, right);
        }

        return left;
    }

    private ExpressionNode parsePrimaryExpression() {
        if (this.current(TokenType.INTEGER)) {
            return new IntegerExpressionNode((Integer) this.consume().value);
        } else if (this.current(TokenType.FLOAT)) {
            return new FloatExpressionNode((Double) this.consume().value);
        } else if (this.current(TokenType.IDENTIFIER)) {
            return new IdentifierExpressionNode((String) this.consume().value);
        }

        throw new ParseError("Unknown expression type '" + this.current().type + "'");
    }
}
