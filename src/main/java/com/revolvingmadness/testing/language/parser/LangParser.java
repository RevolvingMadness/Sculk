package com.revolvingmadness.testing.language.parser;

import com.revolvingmadness.testing.backend.LangScript;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.lexer.Token;
import com.revolvingmadness.testing.language.lexer.TokenType;
import com.revolvingmadness.testing.language.parser.errors.ParseError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.*;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.*;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.*;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
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

    private ExpressionNode parseLogicalExpression() {
        ExpressionNode left = this.parseAdditionExpression();

        while (this.current().isLogicalOperator()) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parseAdditionExpression();

            left = new BinaryExpressionNode(left, operator, right);
        }

        return left;
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
        return this.parseLogicalExpression();
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
        ExpressionNode expression;

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
            IdentifierExpressionNode name = new IdentifierExpressionNode((String) this.consume().value);

            if (this.current(TokenType.LEFT_PARENTHESIS)) {
                this.consume(TokenType.LEFT_PARENTHESIS);

                List<ExpressionNode> arguments = new ArrayList<>();

                if (!this.current(TokenType.RIGHT_PARENTHESIS)) {
                    arguments.add(this.parseExpression());
                }

                while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
                    this.consume(TokenType.COMMA);

                    arguments.add(this.parseExpression());
                }

                this.consume(TokenType.RIGHT_PARENTHESIS);

                return new FunctionCallExpressionNode(name, arguments);
            } else {
                expression = name;
            }
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
        } else if (this.current(TokenType.NULL)) {
            this.consume();
            expression = new NullExpressionNode();
        } else {
            throw new ParseError("Unknown expression type '" + this.current().type + "'");
        }

        if (unaryOperator != null) {
            return new UnaryExpression(unaryOperator, expression);
        }

        return expression;
    }

    private StatementNode parseStatement() {
        StatementNode statement = null;

        if (this.current(TokenType.IMPORT)) {
            statement = this.parseImportStatement();
            this.consume(TokenType.SEMICOLON);
        } else if (this.current(TokenType.IDENTIFIER)) {
            if (this.next(TokenType.LEFT_PARENTHESIS)) {
                IdentifierExpressionNode name = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER).value);

                this.consume(TokenType.LEFT_PARENTHESIS);

                List<ExpressionNode> arguments = new ArrayList<>();

                if (!this.current(TokenType.RIGHT_PARENTHESIS)) {
                    arguments.add(this.parseExpression());
                }

                while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
                    this.consume(TokenType.COMMA);

                    arguments.add(this.parseExpression());
                }

                this.consume(TokenType.RIGHT_PARENTHESIS);

                statement = new FunctionCallStatementNode(name, arguments);
            } else {
                statement = this.parseAssignmentStatement();
            }
            this.consume(TokenType.SEMICOLON);
        } else if (this.current(TokenType.IF)) {
            statement = this.parseIfStatement();
            if (this.current(TokenType.SEMICOLON)) {
                this.consume(TokenType.SEMICOLON);
            }
        } else if (this.current(TokenType.WHILE)) {
            statement = this.parseWhileStatement();
            if (this.current(TokenType.SEMICOLON)) {
                this.consume(TokenType.SEMICOLON);
            }
        } else if (this.current(TokenType.FOR)) {
            statement = this.parseForStatement();
            if (this.current(TokenType.SEMICOLON)) {
                this.consume(TokenType.SEMICOLON);
            }
        } else if (this.current(TokenType.FUNCTION)) {
            statement = this.parseFunctionStatement();
            if (this.current(TokenType.SEMICOLON)) {
                this.consume(TokenType.SEMICOLON);
            }
        }

        if (statement == null) {
            throw new SyntaxError("Expected 'IMPORT', 'IDENTIFIER', 'IF', 'WHILE', 'FOR', or 'FUNCTION', got '" + this.current().type + "'");
        }

        return statement;
    }

    private boolean next(TokenType type) {
        if (this.position + 1 >= this.input.size()) {
            return false;
        }

        return this.input.get(this.position + 1).type == type;
    }

    private StatementNode parseFunctionStatement() {
        this.consume(TokenType.FUNCTION);

        IdentifierExpressionNode name = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER).value);

        this.consume(TokenType.LEFT_PARENTHESIS);

        Map<IdentifierExpressionNode, IdentifierExpressionNode> arguments = new HashMap<>();

        if (this.current(TokenType.IDENTIFIER)) {
            IdentifierExpressionNode type = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER).value);
            IdentifierExpressionNode argumentName = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER).value);
            arguments.put(type, argumentName);
        }

        while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
            this.consume(TokenType.COMMA);

            IdentifierExpressionNode type = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER).value);
            IdentifierExpressionNode argumentName = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER).value);
            arguments.put(type, argumentName);
        }

        this.consume(TokenType.RIGHT_PARENTHESIS);

        this.consume(TokenType.RIGHT_ARROW);

        IdentifierExpressionNode returnType = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER).value);

        List<StatementNode> body = this.parseBody();

        return new FunctionDeclarationStatement(name, arguments, returnType, body);
    }

    private StatementNode parseIfStatement() {
        this.consume(TokenType.IF);

        this.consume(TokenType.LEFT_PARENTHESIS);

        ExpressionNode expression = this.parseExpression();

        this.consume(TokenType.RIGHT_PARENTHESIS);

        List<StatementNode> body = this.parseBody();

        return new IfStatementNode(expression, body);
    }

    private StatementNode parseForStatement() {
        this.consume(TokenType.FOR);

        this.consume(TokenType.LEFT_PARENTHESIS);

        AssignmentStatementNode initialization = this.parseAssignmentStatement();

        this.consume(TokenType.SEMICOLON);

        ExpressionNode condition = this.parseExpression();

        this.consume(TokenType.SEMICOLON);

        AssignmentStatementNode update = this.parseAssignmentStatement();

        if (this.current(TokenType.SEMICOLON)) {
            this.consume(TokenType.SEMICOLON);
        }

        this.consume(TokenType.RIGHT_PARENTHESIS);

        List<StatementNode> body = this.parseBody();

        return new ForStatementNode(initialization, condition, update, body);
    }

    private StatementNode parseWhileStatement() {
        this.consume(TokenType.WHILE);

        this.consume(TokenType.LEFT_PARENTHESIS);

        ExpressionNode expression = this.parseExpression();

        this.consume(TokenType.RIGHT_PARENTHESIS);

        List<StatementNode> body = this.parseBody();

        return new WhileStatementNode(expression, body);
    }

    private List<StatementNode> parseBody() {
        this.consume(TokenType.LEFT_BRACE);

        List<StatementNode> body = new ArrayList<>();

        while (this.position < this.input.size() && !this.current(TokenType.RIGHT_BRACE)) {
            body.add(this.parseStatement());
        }

        this.consume(TokenType.RIGHT_BRACE);

        return body;
    }

    private StatementNode parseImportStatement() {
        this.consume(TokenType.IMPORT);

        String[] splitIdentifier = ((String) this.consume(TokenType.RESOURCE).value).split(":");
        String path = splitIdentifier[0];
        String namespace = splitIdentifier[1];

        Identifier resource = Identifier.of(path, namespace);

        return new ImportStatementNode(resource);
    }

    private AssignmentStatementNode parseAssignmentStatement() {
        IdentifierExpressionNode typeOrName = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER).value);

        if (this.current(TokenType.IDENTIFIER)) {
            IdentifierExpressionNode name = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER).value);

            if (this.current(TokenType.SEMICOLON)) {
                return new AssignmentStatementNode(typeOrName, name, new NullExpressionNode());
            }

            this.consume(TokenType.EQUALS);

            ExpressionNode expression = this.parseExpression();

            return new AssignmentStatementNode(typeOrName, name, expression);
        }

        TokenType shorthandAssignmentOperator = null;

        if (this.current().isBinaryOperator()) {
            shorthandAssignmentOperator = this.consume().type;
        }

        if (this.current().isIncrementOrDecrementOperator()) {
            TokenType incrementOrDecrementOperator = this.consume().type;

            return new AssignmentStatementNode(null, typeOrName, new BinaryExpressionNode(typeOrName, incrementOrDecrementOperator, new IntegerExpressionNode(1)));
        }

        this.consume(TokenType.EQUALS);

        ExpressionNode expression = this.parseExpression();

        if (shorthandAssignmentOperator != null) {
            return new AssignmentStatementNode(null, typeOrName, new BinaryExpressionNode(typeOrName, shorthandAssignmentOperator, expression));
        }

        return new AssignmentStatementNode(null, typeOrName, expression);
    }
}
