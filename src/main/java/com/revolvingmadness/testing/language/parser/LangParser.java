package com.revolvingmadness.testing.language.parser;

import com.revolvingmadness.testing.backend.LangScript;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.lexer.Token;
import com.revolvingmadness.testing.language.lexer.TokenType;
import com.revolvingmadness.testing.language.parser.errors.ParseError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.BinaryExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.UnaryExpression;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.*;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.AssignmentStatementNode;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.ImportStatementNode;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.StatementNode;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

public class LangParser {
    public final Map<Identifier, LangScript> scripts;
    private final List<Token> input;
    private Integer position;

    public LangParser(Map<Identifier, LangScript> scripts, List<Token> input) {
        this.scripts = scripts;
        this.input = input;
        this.position = 0;
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

    private Token current() {
        return this.input.get(this.position);
    }

    private boolean current(TokenType type) {
        return this.current().type == type;
    }

    public ScriptNode parse() {
        ScriptNode script = new ScriptNode(this.scripts);

        while (!this.current(TokenType.EOF)) {
            script.statements.add(this.parseStatement());
        }

        return script;
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

    private ExpressionNode parseExponentiationExpression() {
        ExpressionNode left = this.parsePrimaryExpression();

        while (this.current().isExponentiationOperator()) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parsePrimaryExpression();

            left = new BinaryExpressionNode(left, operator, right);
        }

        return left;
    }

    private ExpressionNode parseExpression() {
        return this.parseAdditionExpression();
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

    private ExpressionNode parsePrimaryExpression() {
        UnaryOperatorType unaryOperator = null;
        ExpressionNode expression = null;

        if (this.current(TokenType.HYPHEN)) {
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
        } else if (this.current(TokenType.STRING)) {
            expression = new StringExpressionNode((String) this.consume().value);
        } else if (this.current(TokenType.RESOURCE)) {
            expression = new ResourceExpressionNode(Identifier.tryParse((String) this.consume().value));
        }

        if (expression == null) {
            throw new ParseError("Unknown expression type '" + this.current().type + "'");
        }

        if (unaryOperator != null) {
            return new UnaryExpression(unaryOperator, expression);
        }

        return expression;
    }

    private StatementNode parseStatement() {
        if (this.current(TokenType.IMPORT)) {
            return this.parseImportStatement();
        } else if (this.current(TokenType.IDENTIFIER)) {
            return this.parseAssignmentStatement();
        }

        throw new SyntaxError("Expected 'IMPORT' or 'IDENTIFIER', got '" + this.current().type + "'");
    }

    private StatementNode parseImportStatement() {
        this.consume(TokenType.IMPORT);

        String[] splitIdentifier = ((String) this.consume(TokenType.RESOURCE).value).split(":");
        String path = splitIdentifier[0];
        String namespace = splitIdentifier[1];

        Identifier resource = Identifier.of(path, namespace);

        this.consume(TokenType.SEMICOLON);

        return new ImportStatementNode(resource);
    }

    private AssignmentStatementNode parseAssignmentStatement() {
        IdentifierExpressionNode typeOrName = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER).value);

        if (this.current(TokenType.IDENTIFIER)) {
            IdentifierExpressionNode name = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER).value);

            this.consume(TokenType.EQUALS);

            ExpressionNode expression = this.parseExpression();

            this.consume(TokenType.SEMICOLON);

            return new AssignmentStatementNode(typeOrName, name, expression);
        }

        TokenType shorthandAssignmentOperator = null;

        if (this.current().isOperator()) {
            shorthandAssignmentOperator = this.consume().type;
        }

        if (this.current().isIncrementOrDecrementOperator()) {
            TokenType incrementOrDecrementOperator = this.consume().type;

            this.consume(TokenType.SEMICOLON);

            return new AssignmentStatementNode(null, typeOrName, new BinaryExpressionNode(typeOrName, incrementOrDecrementOperator, new IntegerExpressionNode(1)));
        }

        this.consume(TokenType.EQUALS);

        ExpressionNode expression = this.parseExpression();

        this.consume(TokenType.SEMICOLON);

        if (shorthandAssignmentOperator != null) {
            return new AssignmentStatementNode(null, typeOrName, new BinaryExpressionNode(typeOrName, shorthandAssignmentOperator, expression));
        }

        return new AssignmentStatementNode(null, typeOrName, expression);
    }
}
