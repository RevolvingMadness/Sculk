package com.revolvingmadness.testing.language.parser;

import com.revolvingmadness.testing.language.lexer.Token;
import com.revolvingmadness.testing.language.lexer.TokenType;
import com.revolvingmadness.testing.language.parser.error.ParseError;
import com.revolvingmadness.testing.language.parser.error.SyntaxError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.UnaryOperatorType;
import com.revolvingmadness.testing.language.parser.nodes.expression.*;
import com.revolvingmadness.testing.language.parser.nodes.statement.AssignmentStatementNode;
import com.revolvingmadness.testing.language.parser.nodes.statement.StatementNode;

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
            throw new SyntaxError("Expected '" + type + "', got '" + token.type + "'");
        }

        return token;
    }

    private Boolean current(TokenType type) {
        return this.current().type == type;
    }

    public ScriptNode parse() {
        ScriptNode script = new ScriptNode();

        while (!this.current(TokenType.EOF)) {
            script.statements.add(this.parseStatement());
        }

        return script;
    }

    private StatementNode parseStatement() {
        IdentifierExpressionNode typeOrName = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER).value);

        if (this.current(TokenType.IDENTIFIER)) {
            IdentifierExpressionNode name = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER).value);

            this.consume(TokenType.EQUALS);

            ExpressionNode expression = this.parseExpression();

            this.consume(TokenType.SEMICOLON);

            return new AssignmentStatementNode(typeOrName, name, expression);
        }

        this.consume(TokenType.EQUALS);

        ExpressionNode expression = this.parseExpression();

        this.consume(TokenType.SEMICOLON);

        return new AssignmentStatementNode(null, typeOrName, expression);
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
        UnaryOperatorType unaryOperator = null;
        ExpressionNode expression = null;

        if (this.current(TokenType.DASH)) {
            this.consume();
            unaryOperator = UnaryOperatorType.NEGATION;
        } else if (this.current(TokenType.EXCLAMATION_MARK)) {
            this.consume();
            unaryOperator = UnaryOperatorType.NOT;
        }

        if (this.current(TokenType.INTEGER)) {
            expression = new IntegerExpressionNode((Integer) this.consume().value);
        } else if (this.current(TokenType.FLOAT)) {
            expression = new FloatExpressionNode((Double) this.consume().value);
        } else if (this.current(TokenType.IDENTIFIER)) {
            expression = new IdentifierExpressionNode((String) this.consume().value);
        } else if (this.current(TokenType.LEFT_PARENTHESIS)) {
            this.consume(TokenType.LEFT_PARENTHESIS);

            expression = this.parseExpression();

            this.consume(TokenType.RIGHT_PARENTHESIS);
        } else if (this.current(TokenType.TRUE)) {
            this.consume();
            expression = new BooleanExpressionNode(true);
        } else if (this.current(TokenType.FALSE)) {
            this.consume();
            expression = new BooleanExpressionNode(false);
        }

        if (expression == null) {
            throw new ParseError("Unknown expression type '" + this.current().type + "'");
        }

        if (unaryOperator != null) {
            return new UnaryExpression(unaryOperator, expression);
        }

        return expression;
    }
}
