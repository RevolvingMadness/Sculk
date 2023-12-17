package com.revolvingmadness.testing.language.parser;

import com.revolvingmadness.testing.backend.LangScript;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.lexer.Token;
import com.revolvingmadness.testing.language.lexer.TokenType;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.*;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.LValueExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.PropertyExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.*;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.*;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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

    private Token consume(TokenType type, String message) {
        Token token = this.input.get(this.position++);

        if (token.type != type) {
            throw new SyntaxError(message);
        }

        return token;
    }

    private Token current() {
        return this.input.get(this.position);
    }

    private boolean current(TokenType type) {
        return this.current().type == type;
    }

    private boolean next(TokenType type) {
        if (this.position + 1 >= this.input.size()) {
            return false;
        }

        return this.input.get(this.position + 1).type == type;
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

    private ExpressionNode parseAndExpression() {
        ExpressionNode left = this.parseLogicalExpression();

        while (this.current().isAndOperator()) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parseLogicalExpression();

            left = new BinaryExpressionNode(left, operator, right);
        }

        return left;
    }

    private List<ExpressionNode> parseArguments() {
        this.consume();

        List<ExpressionNode> arguments = new ArrayList<>();

        if (!this.current(TokenType.RIGHT_PARENTHESIS)) {
            arguments.add(this.parseExpression());
        }

        while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
            this.consume();

            arguments.add(this.parseExpression());
        }

        this.consume(TokenType.RIGHT_PARENTHESIS, "Expected closing parenthesis for arguments");

        return arguments;
    }

    private ExpressionNode parseAssignmentExpression() {
        ExpressionNode expression = this.parseAndExpression();

        if (this.current().isIncrementOperator()) {
            TokenType incrementOperator = this.consume().type;

            if (!(expression instanceof LValueExpressionNode lValueExpression)) {
                throw new SyntaxError("Cannot assign to r-value");
            }

            return new VariableAssignmentExpressionNode(lValueExpression, new BinaryExpressionNode(expression, incrementOperator, new IntegerExpressionNode(1)));
        }

        if (this.current().isBinaryOperator()) {
            TokenType binaryOperator = this.consume().type;

            if (!(expression instanceof LValueExpressionNode lValueExpression)) {
                throw new SyntaxError("Cannot assign to r-value");
            }

            this.consume(TokenType.EQUALS, "Expected equals after binary operator");

            ExpressionNode right = this.parseExpression();

            return new VariableAssignmentExpressionNode(lValueExpression, new BinaryExpressionNode(lValueExpression, binaryOperator, right));
        }

        if (this.current(TokenType.EQUALS)) {
            this.consume();

            if (!(expression instanceof LValueExpressionNode lValueExpression)) {
                throw new SyntaxError("Cannot assign to r-value");
            }

            ExpressionNode value = this.parseAndExpression();

            return new VariableAssignmentExpressionNode(lValueExpression, value);
        }

        return expression;
    }

    private List<StatementNode> parseBody() {
        this.consume();

        List<StatementNode> body = new ArrayList<>();

        while (this.position < this.input.size() && !this.current(TokenType.RIGHT_BRACE)) {
            body.add(this.parseStatement());
        }

        this.consume(TokenType.RIGHT_BRACE, "Expected closing brace for body");

        return body;
    }

    private StatementNode parseBreakStatement() {
        this.consume();

        return new BreakStatementNode();
    }

    @NotNull
    private ExpressionNode parseCallExpression() {
        ExpressionNode expression = this.parsePrimaryExpression();

        while (this.position < this.input.size() && (this.current(TokenType.LEFT_PARENTHESIS) || this.current(TokenType.PERIOD))) {
            if (this.current(TokenType.LEFT_PARENTHESIS)) {
                List<ExpressionNode> arguments = this.parseArguments();
                expression = new CallExpressionNode(expression, arguments);
            } else if (this.current(TokenType.PERIOD)) {
                this.consume();
                IdentifierExpressionNode propertyName = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected property name").value);
                expression = new PropertyExpressionNode(expression, propertyName);
            }
        }

        return expression;
    }

    private StatementNode parseClassDeclarationStatement() {
        boolean isConstant = false;

        if (this.current(TokenType.CONST)) {
            this.consume();
            isConstant = true;
        }

        this.consume();

        IdentifierExpressionNode name = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected class name").value);

        IdentifierExpressionNode superClassName = null;

        if (this.current(TokenType.EXTENDS)) {
            this.consume();
            superClassName = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected super class name").value);
        }

        this.consume(TokenType.LEFT_BRACE, "Expected opening brace after class name");

        List<StatementNode> body = new ArrayList<>();

        while (this.position < this.input.size() && !this.current(TokenType.RIGHT_BRACE)) {
            body.add(this.parseDeclarationStatement());
        }

        this.consume(TokenType.RIGHT_BRACE, "Expected closing brace after class methods");

        return new ClassDeclarationStatementNode(isConstant, name, superClassName, body);
    }

    private StatementNode parseContinueStatement() {
        this.consume();

        return new ContinueStatementNode();
    }

    private StatementNode parseDeclarationStatement() {
        StatementNode statement;

        if (this.current(TokenType.FUNCTION) || (this.current(TokenType.CONST) && this.next(TokenType.FUNCTION))) {
            statement = this.parseFunctionDeclarationStatement();
            if (this.current(TokenType.SEMICOLON)) {
                this.consume();
            }
        } else if (this.current(TokenType.CLASS) || (this.current(TokenType.CONST) && this.next(TokenType.CLASS))) {
            statement = this.parseClassDeclarationStatement();
            if (this.current(TokenType.SEMICOLON)) {
                this.consume();
            }
        } else if (this.current(TokenType.VAR) || (this.current(TokenType.CONST) && this.next(TokenType.VAR))) {
            statement = this.parseVariableDeclarationStatement();
            this.consume(TokenType.SEMICOLON, "Expected semicolon after variable declaration statement");
        } else {
            statement = this.parseExpressionStatement();
            this.consume(TokenType.SEMICOLON, "Expected semicolon after expression statement");
        }

        return statement;
    }

    private ExpressionNode parseExponentiationExpression() {
        ExpressionNode left = this.parseCallExpression();

        while (this.current().isExponentiationOperator()) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parseCallExpression();

            left = new BinaryExpressionNode(left, operator, right);
        }

        return left;
    }

    private ExpressionNode parseExpression() {
        return this.parseAssignmentExpression();
    }

    private StatementNode parseExpressionStatement() {
        ExpressionNode expression = this.parseExpression();

        return new ExpressionStatementNode(expression);
    }

    private StatementNode parseForStatement() {
        this.consume();

        this.consume(TokenType.LEFT_PARENTHESIS, "Expected left parenthesis after 'for'");

        StatementNode initialization = null;

        if (this.current(TokenType.SEMICOLON)) {
            this.consume();
        } else {
            if (this.current(TokenType.VAR)) {
                initialization = this.parseVariableDeclarationStatement();
            } else {
                initialization = this.parseExpressionStatement();
            }
            this.consume(TokenType.SEMICOLON, "Expected semicolon after for-loop initialization");
        }

        ExpressionNode condition;

        if (this.current(TokenType.SEMICOLON)) {
            condition = new BooleanExpressionNode(true);
            this.consume();
        } else {
            condition = this.parseExpression();
            this.consume(TokenType.SEMICOLON, "Expected semicolon after for-loop condition");
        }

        ExpressionNode update = null;

        if (this.current(TokenType.RIGHT_PARENTHESIS)) {
            this.consume();
        } else {
            update = this.parseExpression();
            this.consume(TokenType.RIGHT_PARENTHESIS, "Expected closing parenthesis after for-loop update");
        }

        List<StatementNode> body = this.parseBody();

        return new ForStatementNode(initialization, condition, update, body);
    }

    private StatementNode parseFunctionDeclarationStatement() {
        boolean isConstant = false;

        if (this.current(TokenType.CONST)) {
            this.consume();
            isConstant = true;
        }

        this.consume();

        IdentifierExpressionNode name = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected function name").value);

        this.consume(TokenType.LEFT_PARENTHESIS, "Expected opening parenthesis after function name");

        List<IdentifierExpressionNode> arguments = new ArrayList<>();

        if (this.current(TokenType.IDENTIFIER)) {
            IdentifierExpressionNode argumentName = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected argument name").value);
            arguments.add(argumentName);
        }

        while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
            this.consume();

            IdentifierExpressionNode argumentName = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected argument name").value);
            arguments.add(argumentName);
        }

        this.consume(TokenType.RIGHT_PARENTHESIS, "Expected closing parenthesis after function declaration");

        List<StatementNode> body = this.parseBody();

        return new FunctionDeclarationStatementNode(isConstant, name, arguments, body);
    }

    private ExpressionNode parseFunctionExpression() {
        this.consume();

        this.consume(TokenType.LEFT_PARENTHESIS, "Expected opening parenthesis for function expression");

        List<IdentifierExpressionNode> arguments = new ArrayList<>();

        if (!this.current(TokenType.RIGHT_PARENTHESIS)) {
            arguments.add(new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected argument name").value));
        }

        while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
            this.consume();

            arguments.add(new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected argument name").value));
        }

        this.consume(TokenType.RIGHT_PARENTHESIS, "Expected closing parenthesis for function expression");

        List<StatementNode> body = new ArrayList<>();

        if (this.current(TokenType.RIGHT_ARROW)) {
            this.consume();

            ExpressionNode expression = this.parseExpression();

            body.add(new ReturnStatementNode(expression));
        } else {
            body.addAll(this.parseBody());
        }

        return new FunctionExpressionNode(new IdentifierExpressionNode("anonymous"), arguments, body);
    }

    private StatementNode parseIfStatement() {
        this.consume();

        this.consume(TokenType.LEFT_PARENTHESIS, "Expected opening parenthesis after 'if'");

        ExpressionNode expression = this.parseExpression();

        this.consume(TokenType.RIGHT_PARENTHESIS, "Expected closing parenthesis after if condition");

        List<StatementNode> body = this.parseBody();

        return new IfStatementNode(expression, body);
    }

    private StatementNode parseImportStatement() {
        this.consume();

        String[] splitIdentifier = ((String) this.consume(TokenType.RESOURCE, "Expected resource after 'import'").value).split(":");
        String path = splitIdentifier[0];
        String namespace = splitIdentifier[1];

        Identifier resource = Identifier.of(path, namespace);

        return new ImportStatementNode(resource);
    }

    private ExpressionNode parseListExpression() {
        this.consume();

        List<ExpressionNode> elements = new ArrayList<>();

        if (!this.current(TokenType.RIGHT_BRACKET)) {
            ExpressionNode element = this.parseExpression();

            elements.add(element);
        }

        while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
            this.consume();

            ExpressionNode element = this.parseExpression();

            elements.add(element);
        }

        this.consume(TokenType.RIGHT_BRACKET, "Expected closing bracket for list");

        return new ListExpressionNode(elements);
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

    private ExpressionNode parseMultiplicationExpression() {
        ExpressionNode left = this.parseUnaryExpression();

        while (this.current().isMultiplicationOperator()) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parseUnaryExpression();

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
        } else if (this.current(TokenType.LEFT_PARENTHESIS)) {
            this.consume();

            ExpressionNode expression = this.parseExpression();

            this.consume(TokenType.RIGHT_PARENTHESIS, "Expected closing parenthesis after grouped expression");

            return expression;
        } else if (this.current(TokenType.TRUE)) {
            this.consume();
            return new BooleanExpressionNode(true);
        } else if (this.current(TokenType.FALSE)) {
            this.consume();
            return new BooleanExpressionNode(false);
        } else if (this.current(TokenType.STRING)) {
            return new StringExpressionNode((String) this.consume().value);
        } else if (this.current(TokenType.RESOURCE)) {
            return new ResourceExpressionNode(Identifier.tryParse((String) this.consume().value));
        } else if (this.current(TokenType.NULL)) {
            this.consume();
            return new NullExpressionNode();
        } else if (this.current(TokenType.LEFT_BRACKET)) {
            return this.parseListExpression();
        } else if (this.current(TokenType.FUNCTION)) {
            return this.parseFunctionExpression();
        }

        throw new SyntaxError("Unknown expression type '" + this.current().type + "'");
    }

    private StatementNode parseReturnStatement() {
        this.consume();

        if (this.current(TokenType.SEMICOLON)) {
            return new ReturnStatementNode(new NullExpressionNode());
        } else {
            ExpressionNode expression = this.parseExpression();

            return new ReturnStatementNode(expression);
        }
    }

    private StatementNode parseStatement() {
        StatementNode statement;

        if (this.current(TokenType.IMPORT)) {
            statement = this.parseImportStatement();
            this.consume(TokenType.SEMICOLON, "Expected semicolon after import statement");
        } else if (this.current(TokenType.IF)) {
            statement = this.parseIfStatement();
            if (this.current(TokenType.SEMICOLON)) {
                this.consume();
            }
        } else if (this.current(TokenType.WHILE)) {
            statement = this.parseWhileStatement();
            if (this.current(TokenType.SEMICOLON)) {
                this.consume();
            }
        } else if (this.current(TokenType.FOR)) {
            statement = this.parseForStatement();
            if (this.current(TokenType.SEMICOLON)) {
                this.consume();
            }
        } else if (this.current(TokenType.RETURN)) {
            statement = this.parseReturnStatement();
            this.consume(TokenType.SEMICOLON, "Expected semicolon after return statement");
        } else if (this.current(TokenType.BREAK)) {
            statement = this.parseBreakStatement();
            this.consume(TokenType.SEMICOLON, "Expected semicolon after break statement");
        } else if (this.current(TokenType.CONTINUE)) {
            statement = this.parseContinueStatement();
            this.consume(TokenType.SEMICOLON, "Expected semicolon after continue statement");
        } else {
            return this.parseDeclarationStatement();
        }

        return statement;
    }

    private ExpressionNode parseUnaryExpression() {
        if (this.current().isUnaryOperator()) {
            TokenType unaryOperator = this.consume().type;

            ExpressionNode expression = this.parseExponentiationExpression();

            return new UnaryExpressionNode(unaryOperator, expression);
        }

        return this.parseExponentiationExpression();
    }

    private VariableDeclarationStatementNode parseVariableDeclarationStatement() {
        boolean isConstant = false;

        if (this.current(TokenType.CONST)) {
            this.consume();
            isConstant = true;
        }

        this.consume(TokenType.VAR, "Expected 'var' keyword");

        IdentifierExpressionNode name = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected variable name").value);

        if (this.current(TokenType.SEMICOLON)) {
            return new VariableDeclarationStatementNode(isConstant, name, new NullExpressionNode());
        }

        this.consume(TokenType.EQUALS, "Expected equals after variable name");

        ExpressionNode expression = this.parseExpression();

        return new VariableDeclarationStatementNode(isConstant, name, expression);
    }

    private StatementNode parseWhileStatement() {
        this.consume();

        this.consume(TokenType.LEFT_PARENTHESIS, "Expected opening parenthesis after 'while'");

        ExpressionNode expression = this.parseExpression();

        this.consume(TokenType.RIGHT_PARENTHESIS, "Expected closing parenthesis after while condition");

        List<StatementNode> body = this.parseBody();

        return new WhileStatementNode(expression, body);
    }
}
