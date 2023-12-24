package com.revolvingmadness.testing.language.parser;

import com.revolvingmadness.testing.backend.LangScript;
import com.revolvingmadness.testing.language.builtins.classes.types.*;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.lexer.Token;
import com.revolvingmadness.testing.language.lexer.TokenType;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.*;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LangParser {
    public final List<Token> input;
    public final Map<Identifier, LangScript> scripts;
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

    private ExpressionNode parseAdditiveExpression() {
        ExpressionNode left = this.parseMultiplicativeExpression();

        while (this.current().isAdditiveOperator()) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parseMultiplicativeExpression();

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
        ExpressionNode expression = this.parseConditionalOrExpression();

        if (this.current().isIncrementOperator()) {
            TokenType incrementOperator = this.consume().type;

            return new VariableAssignmentExpressionNode(expression, new BinaryExpressionNode(expression, incrementOperator, new IntegerClass(1)));
        }

        if (this.current().isBinaryOperator()) {
            TokenType binaryOperator = this.consume().type;

            this.consume(TokenType.EQUALS, "Expected equals after binary operator");

            ExpressionNode right = this.parseExpression();

            return new VariableAssignmentExpressionNode(expression, new BinaryExpressionNode(expression, binaryOperator, right));
        }

        if (this.current(TokenType.EQUALS)) {
            this.consume();

            ExpressionNode value = this.parseConditionalOrExpression();

            return new VariableAssignmentExpressionNode(expression, value);
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

        while (this.position < this.input.size() && (this.current(TokenType.LEFT_PARENTHESIS) || this.current(TokenType.PERIOD) || this.current(TokenType.LEFT_BRACKET))) {
            if (this.current(TokenType.LEFT_PARENTHESIS)) {
                List<ExpressionNode> arguments = this.parseArguments();
                expression = new CallExpressionNode(expression, arguments);
            } else if (this.current(TokenType.PERIOD)) {
                this.consume();
                String propertyName = (String) this.consume(TokenType.IDENTIFIER, "Expected property name").value;
                expression = new GetExpressionNode(expression, propertyName);
            } else if (this.current(TokenType.LEFT_BRACKET)) {
                this.consume();
                ExpressionNode indexExpression = this.parseExpression();
                this.consume(TokenType.RIGHT_BRACKET, "Expected closing bracket for list indexing");
                expression = new IndexExpressionNode(expression, indexExpression);
            }
        }

        return expression;
    }

    private List<StatementNode> parseClassBody() {
        this.consume(TokenType.LEFT_BRACE, "Expected opening brace after class name");

        List<TokenType> accessModifiers = new ArrayList<>();

        while (this.position < this.input.size() && this.current().isAccessModifier()) {
            accessModifiers.add(this.consume().type);
        }

        List<StatementNode> body = new ArrayList<>();

        while (this.position < this.input.size() && !this.current(TokenType.RIGHT_BRACE)) {
            if (this.current(TokenType.FUNCTION)) {
                StatementNode statement = this.parseMethodDeclarationStatement(accessModifiers);

                if (this.current(TokenType.SEMICOLON)) {
                    this.consume();
                }

                body.add(statement);
            } else if (this.current(TokenType.VAR)) {
                StatementNode statement = this.parseFieldDeclarationStatement(accessModifiers);

                this.consume(TokenType.SEMICOLON, "Expected semicolon");

                body.add(statement);
            } else {
                throw new SyntaxError("Expected class element");
            }
        }

        this.consume(TokenType.RIGHT_BRACE, "Expected closing brace after class body");

        return body;
    }

    private StatementNode parseClassDeclarationStatement() {
        boolean isConstant = false;

        if (this.current(TokenType.CONST)) {
            this.consume();
            isConstant = true;
        }

        this.consume();

        String name = (String) this.consume(TokenType.IDENTIFIER, "Expected class name").value;

        String superClassName = null;

        if (this.current(TokenType.EXTENDS)) {
            this.consume();
            superClassName = (String) this.consume(TokenType.IDENTIFIER, "Expected super class name").value;
        }

        List<StatementNode> body = this.parseClassBody();

        return new ClassDeclarationStatementNode(isConstant, name, superClassName, body);
    }

    private ExpressionNode parseConditionalAndExpression() {
        ExpressionNode left = this.parseEqualityExpression();

        while (this.current().isAndOperator()) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parseEqualityExpression();

            left = new BinaryExpressionNode(left, operator, right);
        }

        return left;
    }

    private ExpressionNode parseConditionalOrExpression() {
        ExpressionNode left = this.parseConditionalAndExpression();

        while (this.current(TokenType.DOUBLE_PIPE)) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parseConditionalAndExpression();

            left = new BinaryExpressionNode(left, operator, right);
        }

        return left;
    }

    private StatementNode parseContinueStatement() {
        this.consume();

        return new ContinueStatementNode();
    }

    private StatementNode parseDeclarationStatement() {
        StatementNode statement;
        List<TokenType> accessModifiers = new ArrayList<>();

        while (this.position < this.input.size() && this.current().isAccessModifier()) {
            accessModifiers.add(this.consume().type);
        }

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
            if (accessModifiers.size() > 0) {
                throw new SyntaxError("Cannot apply access modifiers to expression");
            }

            statement = this.parseExpressionStatement();
            this.consume(TokenType.SEMICOLON, "Expected semicolon after expression statement");
        }

        return statement;
    }

    private ExpressionNode parseDictionaryExpression() {
        this.consume();

        Map<ExpressionNode, ExpressionNode> dictionary = new HashMap<>();

        if (!this.current(TokenType.RIGHT_BRACE)) {
            ExpressionNode key = this.parseExpression();

            this.consume(TokenType.COLON, "Expected colon");

            ExpressionNode value = this.parseExpression();

            dictionary.put(key, value);
        }

        while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
            this.consume();

            if (this.current(TokenType.RIGHT_BRACE)) {
                throw new SyntaxError("Found trailing comma in dictionary");
            }

            ExpressionNode key = this.parseExpression();

            this.consume(TokenType.COLON, "Expected colon");

            ExpressionNode value = this.parseExpression();

            dictionary.put(key, value);
        }

        this.consume(TokenType.RIGHT_BRACE, "Expected closing brace for dictionary");

        return new DictionaryExpressionNode(dictionary);
    }

    private ExpressionNode parseEqualityExpression() {
        ExpressionNode left = this.parseRelationalExpression();

        while (this.current().isEqualityOperator()) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parseRelationalExpression();

            left = new BinaryExpressionNode(left, operator, right);
        }

        return left;
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

    private FieldDeclarationStatementNode parseFieldDeclarationStatement(List<TokenType> accessModifiers) {
        boolean isConstant = false;

        if (this.current(TokenType.CONST)) {
            this.consume();
            isConstant = true;
        }

        this.consume(TokenType.VAR, "Expected 'var' keyword");

        String name = (String) this.consume(TokenType.IDENTIFIER, "Expected variable name").value;

        if (this.current(TokenType.SEMICOLON)) {
            return new FieldDeclarationStatementNode(accessModifiers, isConstant, name, new NullClass());
        }

        this.consume(TokenType.EQUALS, "Expected equals after variable name");

        ExpressionNode expression = this.parseExpression();

        return new FieldDeclarationStatementNode(accessModifiers, isConstant, name, expression);
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
            condition = new BooleanClass(true);
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

        String name = (String) this.consume(TokenType.IDENTIFIER, "Expected function name").value;

        this.consume(TokenType.LEFT_PARENTHESIS, "Expected opening parenthesis after function name");

        List<String> arguments = new ArrayList<>();

        if (this.current(TokenType.IDENTIFIER)) {
            String argumentName = (String) this.consume(TokenType.IDENTIFIER, "Expected argument name").value;
            arguments.add(argumentName);
        }

        while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
            this.consume();

            String argumentName = (String) this.consume(TokenType.IDENTIFIER, "Expected argument name").value;
            arguments.add(argumentName);
        }

        this.consume(TokenType.RIGHT_PARENTHESIS, "Expected closing parenthesis after function declaration");

        List<StatementNode> body = this.parseBody();

        return new FunctionDeclarationStatementNode(isConstant, name, arguments, body);
    }

    private ExpressionNode parseFunctionExpression() {
        this.consume();

        this.consume(TokenType.LEFT_PARENTHESIS, "Expected opening parenthesis for function expression");

        List<String> arguments = new ArrayList<>();

        if (!this.current(TokenType.RIGHT_PARENTHESIS)) {
            arguments.add((String) this.consume(TokenType.IDENTIFIER, "Expected argument name").value);
        }

        while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
            this.consume();

            arguments.add((String) this.consume(TokenType.IDENTIFIER, "Expected argument name").value);
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

        return new FunctionClass("anonymous", arguments, body);
    }

    private StatementNode parseIfStatement() {
        this.consume();

        this.consume(TokenType.LEFT_PARENTHESIS, "Expected opening parenthesis after 'if'");

        ExpressionNode ifCondition = this.parseExpression();

        this.consume(TokenType.RIGHT_PARENTHESIS, "Expected closing parenthesis after 'if' condition");

        List<StatementNode> ifConditionBody = this.parseBody();

        Pair<ExpressionNode, List<StatementNode>> ifConditionPair = new Pair<>(ifCondition, ifConditionBody);

        List<Pair<ExpressionNode, List<StatementNode>>> elseIfConditionPairs = new ArrayList<>();

        List<StatementNode> elseBody = new ArrayList<>();

        while (this.position < this.input.size() && this.current(TokenType.ELSE)) {
            this.consume();

            if (this.current(TokenType.IF)) {
                this.consume();

                this.consume(TokenType.LEFT_PARENTHESIS, "Expected opening parenthesis after 'else if'");

                ExpressionNode elseIfCondition = this.parseExpression();

                this.consume(TokenType.RIGHT_PARENTHESIS, "Expected closing parenthesis after 'else if' condition");

                List<StatementNode> elseIfBody = this.parseBody();

                elseIfConditionPairs.add(new Pair<>(elseIfCondition, elseIfBody));
            } else {
                elseBody = this.parseBody();
                break;
            }
        }

        return new IfStatementNode(ifConditionPair, elseIfConditionPairs, elseBody);
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

    private MethodDeclarationStatementNode parseMethodDeclarationStatement(List<TokenType> accessModifiers) {
        this.consume();

        String name = (String) this.consume(TokenType.IDENTIFIER, "Expected method name").value;

        this.consume(TokenType.LEFT_PARENTHESIS, "Expected opening parenthesis after method name");

        List<String> arguments = new ArrayList<>();

        if (this.current(TokenType.IDENTIFIER)) {
            String argumentName = (String) this.consume(TokenType.IDENTIFIER, "Expected argument name").value;
            arguments.add(argumentName);
        }

        while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
            this.consume();

            String argumentName = (String) this.consume(TokenType.IDENTIFIER, "Expected argument name").value;
            arguments.add(argumentName);
        }

        this.consume(TokenType.RIGHT_PARENTHESIS, "Expected closing parenthesis after method arguments");

        List<StatementNode> body = this.parseBody();

        return new MethodDeclarationStatementNode(accessModifiers, name, arguments, body);
    }

    private ExpressionNode parseMultiplicativeExpression() {
        ExpressionNode left = this.parseUnaryExpression();

        while (this.current().isMultiplicativeOperator()) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parseUnaryExpression();

            left = new BinaryExpressionNode(left, operator, right);
        }

        return left;
    }

    private ExpressionNode parsePostfixExpression() {
        ExpressionNode expression = this.parseExponentiationExpression();

        if (this.current().isPostfixOperator()) {
            TokenType operator = this.consume().type;

            return new PostfixExpressionNode(expression, operator);
        }

        return expression;
    }

    private ExpressionNode parsePrimaryExpression() {
        if (this.current(TokenType.INTEGER)) {
            return new IntegerClass((Integer) this.consume().value);
        } else if (this.current(TokenType.FLOAT)) {
            return new FloatClass((Double) this.consume().value);
        } else if (this.current(TokenType.IDENTIFIER)) {
            return new IdentifierExpressionNode((String) this.consume().value);
        } else if (this.current(TokenType.LEFT_PARENTHESIS)) {
            this.consume();

            ExpressionNode expression = this.parseExpression();

            this.consume(TokenType.RIGHT_PARENTHESIS, "Expected closing parenthesis after grouped expression");

            return expression;
        } else if (this.current(TokenType.TRUE)) {
            this.consume();
            return new BooleanClass(true);
        } else if (this.current(TokenType.FALSE)) {
            this.consume();
            return new BooleanClass(false);
        } else if (this.current(TokenType.STRING)) {
            return new StringClass((String) this.consume().value);
        } else if (this.current(TokenType.RESOURCE)) {
            return new ResourceClass(Identifier.tryParse((String) this.consume().value));
        } else if (this.current(TokenType.NULL)) {
            this.consume();
            return new NullClass();
        } else if (this.current(TokenType.LEFT_BRACKET)) {
            return this.parseListExpression();
        } else if (this.current(TokenType.FUNCTION)) {
            return this.parseFunctionExpression();
        } else if (this.current(TokenType.LEFT_BRACE)) {
            return this.parseDictionaryExpression();
        }

        throw new SyntaxError("Unknown expression type '" + this.current().type + "'");
    }

    private ExpressionNode parseRelationalExpression() {
        ExpressionNode left = this.parseAdditiveExpression();

        while (this.current().isRelationOperator()) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parseAdditiveExpression();

            left = new BinaryExpressionNode(left, operator, right);
        }

        return left;
    }

    private StatementNode parseReturnStatement() {
        this.consume();

        if (this.current(TokenType.SEMICOLON)) {
            return new ReturnStatementNode(new NullClass());
        } else {
            ExpressionNode expression = this.parseExpression();

            return new ReturnStatementNode(expression);
        }
    }

    private StatementNode parseStatement() {
        StatementNode statement;

        if (this.current(TokenType.IF)) {
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

            ExpressionNode expression = this.parsePostfixExpression();

            return new UnaryExpressionNode(unaryOperator, expression);
        }

        return this.parsePostfixExpression();
    }

    private VariableDeclarationStatementNode parseVariableDeclarationStatement() {
        boolean isConstant = false;

        if (this.current(TokenType.CONST)) {
            this.consume();
            isConstant = true;
        }

        this.consume(TokenType.VAR, "Expected 'var' keyword");

        String name = (String) this.consume(TokenType.IDENTIFIER, "Expected variable name").value;

        if (this.current(TokenType.SEMICOLON)) {
            return new VariableDeclarationStatementNode(isConstant, name, new NullClass());
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
