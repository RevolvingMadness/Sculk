package com.revolvingmadness.sculk.language.parser;

import com.revolvingmadness.sculk.language.*;
import com.revolvingmadness.sculk.language.errors.SyntaxError;
import com.revolvingmadness.sculk.language.lexer.Token;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import com.revolvingmadness.sculk.language.parser.nodes.ScriptNode;
import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.*;
import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.literal_expression_nodes.*;
import com.revolvingmadness.sculk.language.parser.nodes.statement_nodes.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    public final List<Token> input;
    private int position;

    public Parser(List<Token> input) {
        this.input = input;
        this.position = 0;
    }

    private Token consume() {
        return this.input.get(this.position++);
    }

    private Token consume(TokenType type) {
        Token token = this.consume();

        if (token.type != type) {
            throw new SyntaxError("Expected '" + type + "' at " + token.lineNumber + ":" + token.columnNumber + ", got '" + token.type + "'");
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
        ScriptNode script = new ScriptNode();

        while (!this.current(TokenType.EOF)) {
            script.statements.add(this.parseStatement());
        }

        return script;
    }

    private List<TokenType> parseAccessModifiers() {
        List<TokenType> accessModifiers = new ArrayList<>();

        while (this.position < this.input.size() && this.current().isAccessModifier()) {
            accessModifiers.add(this.consume().type);
        }

        return accessModifiers;
    }

    private ExpressionNode parseAdditiveExpression() {
        ExpressionNode left = this.parseMultiplicativeExpression();

        while (this.position < this.input.size() && this.current().isAdditiveOperator()) {
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

        this.consume(TokenType.RIGHT_PARENTHESIS);

        return arguments;
    }

    private ExpressionNode parseAssignmentExpression() {
        ExpressionNode expression = this.parseTernaryExpression();

        if (this.current().isIncrementOperator()) {
            TokenType incrementOperator = this.consume().type;

            return new VariableAssignmentExpressionNode(expression, new BinaryExpressionNode(expression, incrementOperator, new IntegerExpressionNode(1L)));
        }

        if (this.current().isBinaryOperator()) {
            TokenType binaryOperator = this.consume().type;

            this.consume(TokenType.EQUALS);

            ExpressionNode right = this.parseExpression();

            return new VariableAssignmentExpressionNode(expression, new BinaryExpressionNode(expression, binaryOperator, right));
        }

        if (this.current(TokenType.EQUALS)) {
            this.consume();

            ExpressionNode value = this.parseTernaryExpression();

            return new VariableAssignmentExpressionNode(expression, value);
        }

        return expression;
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

    private StatementNode parseBreakStatement() {
        this.consume(TokenType.BREAK);

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
                String propertyName = (String) this.consume(TokenType.IDENTIFIER).value;
                expression = new GetExpressionNode(expression, propertyName);
            } else if (this.current(TokenType.LEFT_BRACKET)) {
                this.consume();
                ExpressionNode indexExpression = this.parseExpression();
                this.consume(TokenType.RIGHT_BRACKET);
                expression = new IndexExpressionNode(expression, indexExpression);
            }
        }

        return expression;
    }

    private List<StatementNode> parseClassBody() {
        this.consume(TokenType.LEFT_BRACE);

        List<StatementNode> body = new ArrayList<>();

        while (this.position < this.input.size() && !this.current(TokenType.RIGHT_BRACE)) {
            List<TokenType> accessModifiers = this.parseAccessModifiers();

            if (this.current(TokenType.FUNCTION)) {
                StatementNode statement = this.parseMethodDeclarationStatement(accessModifiers);

                if (this.current(TokenType.SEMICOLON)) {
                    this.consume();
                }

                body.add(statement);
            } else if (this.current(TokenType.VAR)) {
                StatementNode statement = this.parseFieldDeclarationStatement(accessModifiers);

                this.consume(TokenType.SEMICOLON);

                body.add(statement);
            } else {
                throw new SyntaxError("Expected class element");
            }
        }

        this.consume(TokenType.RIGHT_BRACE);

        return body;
    }

    private StatementNode parseClassDeclarationStatement(List<TokenType> accessModifiers) {
        TokenType.validateClassAccessModifiers(accessModifiers);

        this.consume();

        String name = (String) this.consume(TokenType.IDENTIFIER).value;

        String superClassName = null;

        if (this.current(TokenType.EXTENDS)) {
            this.consume();
            superClassName = (String) this.consume(TokenType.IDENTIFIER).value;
        }

        List<StatementNode> body = this.parseClassBody();

        return new ClassDeclarationStatementNode(accessModifiers, name, superClassName, body);
    }

    private ExpressionNode parseConditionalAndExpression() {
        ExpressionNode left = this.parseEqualityExpression();

        while (this.position < this.input.size() && this.current().isAndOperator()) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parseEqualityExpression();

            left = new BinaryExpressionNode(left, operator, right);
        }

        return left;
    }

    private ExpressionNode parseConditionalOrExpression() {
        ExpressionNode left = this.parseConditionalAndExpression();

        while (this.position < this.input.size() && this.current(TokenType.DOUBLE_PIPE)) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parseConditionalAndExpression();

            left = new BinaryExpressionNode(left, operator, right);
        }

        return left;
    }

    private StatementNode parseContinueStatement() {
        this.consume(TokenType.CONTINUE);

        return new ContinueStatementNode();
    }

    private Argument parseDeclarationArgument() {
        String name = (String) this.consume(TokenType.IDENTIFIER).value;

        this.consume(TokenType.COLON);

        String type = (String) this.consume(TokenType.IDENTIFIER).value;

        return new Argument(name, type);
    }

    private List<Argument> parseDeclarationArguments() {
        List<Argument> arguments = new ArrayList<>();

        if (this.current(TokenType.IDENTIFIER)) {
            arguments.add(this.parseDeclarationArgument());
        }

        while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
            this.consume();

            arguments.add(this.parseDeclarationArgument());
        }

        return arguments;
    }

    private StatementNode parseDeclarationStatement() {
        StatementNode statement;

        List<TokenType> accessModifiers = this.parseAccessModifiers();

        if (this.current(TokenType.FUNCTION)) {
            statement = this.parseFunctionDeclarationStatement(accessModifiers);
            if (this.current(TokenType.SEMICOLON)) {
                this.consume();
            }
        } else if (this.current(TokenType.CLASS)) {
            statement = this.parseClassDeclarationStatement(accessModifiers);
            if (this.current(TokenType.SEMICOLON)) {
                this.consume();
            }
        } else if (this.current(TokenType.ENUM)) {
            statement = this.parseEnumDeclarationStatement(accessModifiers);
            if (this.current(TokenType.SEMICOLON)) {
                this.consume();
            }
        } else if (this.current(TokenType.VAR)) {
            statement = this.parseVariableDeclarationStatement(accessModifiers);
            this.consume(TokenType.SEMICOLON);
        } else {
            statement = this.parseExpressionStatement();
            this.consume(TokenType.SEMICOLON);
        }

        return statement;
    }

    private StatementNode parseDeleteStatement() {
        this.consume(TokenType.DELETE);

        ExpressionNode expression = this.parseExpression();

        return new DeleteStatementNode(expression);
    }

    private ExpressionNode parseDictionaryExpression() {
        this.consume(TokenType.LEFT_BRACE);

        Map<ExpressionNode, ExpressionNode> dictionary = new HashMap<>();

        if (!this.current(TokenType.RIGHT_BRACE)) {
            ExpressionNode key = this.parseExpression();

            this.consume(TokenType.COLON);

            ExpressionNode value = this.parseExpression();

            dictionary.put(key, value);
        }

        while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
            this.consume();

            if (this.current(TokenType.RIGHT_BRACE)) {
                throw new SyntaxError("Found trailing comma in dictionary");
            }

            ExpressionNode key = this.parseExpression();

            this.consume(TokenType.COLON);

            ExpressionNode value = this.parseExpression();

            dictionary.put(key, value);
        }

        this.consume(TokenType.RIGHT_BRACE);

        return new DictionaryExpressionNode(dictionary);
    }

    private List<String> parseEnumBody() {
        this.consume(TokenType.LEFT_BRACE);

        List<String> values = new ArrayList<>();

        values.add((String) this.consume(TokenType.IDENTIFIER).value);

        while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
            this.consume();

            values.add((String) this.consume(TokenType.IDENTIFIER).value);
        }

        this.consume(TokenType.SEMICOLON);

        this.consume(TokenType.RIGHT_BRACE);

        return values;
    }

    private StatementNode parseEnumDeclarationStatement(List<TokenType> accessModifiers) {
        TokenType.validateEnumAccessModifiers(accessModifiers);

        this.consume();

        String name = (String) this.consume(TokenType.IDENTIFIER).value;

        List<String> values = this.parseEnumBody();

        return new EnumDeclarationStatementNode(accessModifiers, name, values);
    }

    private ExpressionNode parseEqualityExpression() {
        ExpressionNode left = this.parseRelationalExpression();

        while (this.position < this.input.size() && this.current().isEqualityOperator()) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parseRelationalExpression();

            left = new BinaryExpressionNode(left, operator, right);
        }

        return left;
    }

    private ExpressionNode parseExponentiationExpression() {
        ExpressionNode left = this.parseCallExpression();

        while (this.position < this.input.size() && this.current().isExponentiationOperator()) {
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
        TokenType.validateFieldAccessModifiers(accessModifiers);

        this.consume();

        String name = (String) this.consume(TokenType.IDENTIFIER).value;

        String type = null;

        if (this.current(TokenType.COLON)) {
            this.consume(TokenType.COLON);

            type = (String) this.consume(TokenType.IDENTIFIER).value;
        }

        if (this.current(TokenType.SEMICOLON)) {
            return new FieldDeclarationStatementNode(accessModifiers, type, name, new NullExpressionNode());
        }

        this.consume(TokenType.EQUALS);

        ExpressionNode expression = this.parseExpression();

        return new FieldDeclarationStatementNode(accessModifiers, type, name, expression);
    }

    private StatementNode parseForStatement() {
        this.consume(TokenType.FOR);

        this.consume(TokenType.LEFT_PARENTHESIS);

        StatementNode initialization = null;

        if (this.current(TokenType.SEMICOLON)) {
            this.consume();
        } else {
            List<TokenType> accessModifiers = this.parseAccessModifiers();

            if (this.current(TokenType.VAR)) {
                initialization = this.parseVariableDeclarationStatement(accessModifiers);
            } else {
                if (accessModifiers.size() != 0) {
                    throw new SyntaxError("Expected expression statement, got '" + this.current().type + "'");
                }
                initialization = this.parseExpressionStatement();
            }
            this.consume(TokenType.SEMICOLON);
        }

        ExpressionNode condition;

        if (this.current(TokenType.SEMICOLON)) {
            condition = new BooleanExpressionNode(true);
            this.consume();
        } else {
            condition = this.parseExpression();
            this.consume(TokenType.SEMICOLON);
        }

        ExpressionNode update = null;

        if (this.current(TokenType.RIGHT_PARENTHESIS)) {
            this.consume();
        } else {
            update = this.parseExpression();
            this.consume(TokenType.RIGHT_PARENTHESIS);
        }

        List<StatementNode> body = this.parseBody();

        return new ForStatementNode(initialization, condition, update, body);
    }

    private StatementNode parseForeachStatement() {
        this.consume(TokenType.FOREACH);

        this.consume(TokenType.LEFT_PARENTHESIS);

        String variableName = (String) this.consume(TokenType.IDENTIFIER).value;

        this.consume(TokenType.COLON);

        ExpressionNode variableToIterate = this.parseExpression();

        this.consume(TokenType.RIGHT_PARENTHESIS);

        List<StatementNode> body = this.parseBody();

        return new ForeachStatementNode(variableName, variableToIterate, body);
    }

    private StatementNode parseFromStatement() {
        this.consume(TokenType.FROM);

        String identifierString = (String) this.consume(TokenType.STRING).value;

        Identifier identifier = Identifier.tryParse(identifierString);

        if (identifier == null) {
            throw ErrorHolder.invalidIdentifier(identifierString);
        }

        this.consume(TokenType.IMPORT);

        List<String> variablesToImport = new ArrayList<>();

        variablesToImport.add((String) this.consume(TokenType.IDENTIFIER).value);

        while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
            this.consume();

            variablesToImport.add((String) this.consume(TokenType.IDENTIFIER).value);
        }

        return new FromStatementNode(identifier, variablesToImport);
    }

    private StatementNode parseFunctionDeclarationStatement(List<TokenType> accessModifiers) {
        TokenType.validateFunctionAccessModifiers(accessModifiers);

        this.consume();

        String name = (String) this.consume(TokenType.IDENTIFIER).value;

        this.consume(TokenType.LEFT_PARENTHESIS);

        List<Argument> arguments = this.parseDeclarationArguments();

        this.consume(TokenType.RIGHT_PARENTHESIS);

        this.consume(TokenType.RIGHT_ARROW);

        String returnType = (String) this.consume(TokenType.IDENTIFIER).value;

        List<StatementNode> body = this.parseBody();

        return new FunctionDeclarationStatementNode(accessModifiers, name, arguments, returnType, body);
    }

    private ExpressionNode parseFunctionExpression() {
        this.consume(TokenType.FUNCTION);

        this.consume(TokenType.LEFT_PARENTHESIS);

        List<Argument> arguments = this.parseDeclarationArguments();

        this.consume(TokenType.RIGHT_PARENTHESIS);

        this.consume(TokenType.RIGHT_ARROW);

        String returnType = (String) this.consume(TokenType.IDENTIFIER).value;

        List<StatementNode> body = new ArrayList<>();

        if (this.current(TokenType.DOUBLE_RIGHT_ARROW)) {
            this.consume();

            ExpressionNode expression = this.parseExpression();

            body.add(new ReturnStatementNode(expression));
        } else {
            body.addAll(this.parseBody());
        }

        return new FunctionExpressionNode("anonymous", arguments, returnType, body);
    }

    private StatementNode parseIfStatement() {
        this.consume(TokenType.IF);

        this.consume(TokenType.LEFT_PARENTHESIS);

        ExpressionNode ifCondition = this.parseExpression();

        this.consume(TokenType.RIGHT_PARENTHESIS);

        List<StatementNode> ifConditionBody = this.parseBody();

        Pair<ExpressionNode, List<StatementNode>> ifConditionPair = new Pair<>(ifCondition, ifConditionBody);

        List<Pair<ExpressionNode, List<StatementNode>>> elseIfConditionPairs = new ArrayList<>();

        List<StatementNode> elseBody = new ArrayList<>();

        while (this.position < this.input.size() && this.current(TokenType.ELSE)) {
            this.consume();

            if (this.current(TokenType.IF)) {
                this.consume();

                this.consume(TokenType.LEFT_PARENTHESIS);

                ExpressionNode elseIfCondition = this.parseExpression();

                this.consume(TokenType.RIGHT_PARENTHESIS);

                List<StatementNode> elseIfBody = this.parseBody();

                elseIfConditionPairs.add(new Pair<>(elseIfCondition, elseIfBody));
            } else {
                elseBody = this.parseBody();
                break;
            }
        }

        return new IfStatementNode(ifConditionPair, elseIfConditionPairs, elseBody);
    }

    private StatementNode parseImportStatement() {
        this.consume(TokenType.IMPORT);

        String identifierString = (String) this.consume(TokenType.STRING).value;

        Identifier identifier = Identifier.tryParse(identifierString);

        if (identifier == null) {
            throw ErrorHolder.invalidIdentifier(identifierString);
        }

        String importAs = null;

        if (this.current(TokenType.AS)) {
            this.consume();

            importAs = (String) this.consume(TokenType.IDENTIFIER).value;
        }

        return new ImportStatementNode(identifier, importAs);
    }

    private ExpressionNode parseListExpression() {
        this.consume(TokenType.LEFT_BRACKET);

        List<ExpressionNode> elements = new ArrayList<>();

        if (!this.current(TokenType.RIGHT_BRACKET)) {
            ExpressionNode element = this.parseExpression();

            elements.add(element);
        }

        while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
            this.consume();

            if (this.current(TokenType.RIGHT_BRACKET)) {
                throw new SyntaxError("Found trailing comma in list");
            }

            ExpressionNode element = this.parseExpression();

            elements.add(element);
        }

        this.consume(TokenType.RIGHT_BRACKET);

        return new ListExpressionNode(elements);
    }

    private MethodDeclarationStatementNode parseMethodDeclarationStatement(List<TokenType> accessModifiers) {
        TokenType.validateMethodAccessModifiers(accessModifiers);

        this.consume();

        String name = (String) this.consume(TokenType.IDENTIFIER).value;

        this.consume(TokenType.LEFT_PARENTHESIS);

        List<Argument> arguments = this.parseDeclarationArguments();

        this.consume(TokenType.RIGHT_PARENTHESIS);

        this.consume(TokenType.RIGHT_ARROW);

        String returnType = (String) this.consume(TokenType.IDENTIFIER).value;

        List<StatementNode> body = new ArrayList<>();

        if (!accessModifiers.contains(TokenType.ABSTRACT)) {
            body = this.parseBody();
        }

        if (this.current(TokenType.LEFT_BRACE)) {
            throw new SyntaxError("Abstract method '" + name + "' cannot have a body");
        }

        return new MethodDeclarationStatementNode(accessModifiers, name, arguments, returnType, body);
    }

    private ExpressionNode parseMultiplicativeExpression() {
        ExpressionNode left = this.parseUnaryExpression();

        while (this.position < this.input.size() && this.current().isMultiplicativeOperator()) {
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
            return new IntegerExpressionNode((long) this.consume().value);
        } else if (this.current(TokenType.FLOAT)) {
            return new FloatExpressionNode((double) this.consume().value);
        } else if (this.current(TokenType.IDENTIFIER)) {
            return new IdentifierExpressionNode((String) this.consume().value);
        } else if (this.current(TokenType.LEFT_PARENTHESIS)) {
            this.consume();

            ExpressionNode expression = this.parseExpression();

            this.consume(TokenType.RIGHT_PARENTHESIS);

            return expression;
        } else if (this.current(TokenType.TRUE)) {
            this.consume();
            return new BooleanExpressionNode(true);
        } else if (this.current(TokenType.FALSE)) {
            this.consume();
            return new BooleanExpressionNode(false);
        } else if (this.current(TokenType.STRING)) {
            return new StringExpressionNode((String) this.consume().value);
        } else if (this.current(TokenType.NULL)) {
            this.consume();
            return new NullExpressionNode();
        } else if (this.current(TokenType.LEFT_BRACKET)) {
            return this.parseListExpression();
        } else if (this.current(TokenType.FUNCTION)) {
            return this.parseFunctionExpression();
        } else if (this.current(TokenType.LEFT_BRACE)) {
            return this.parseDictionaryExpression();
        } else if (this.current(TokenType.SWITCH)) {
            return this.parseSwitchExpression();
        }

        throw new SyntaxError("Unknown expression type '" + this.current().type + "'");
    }

    private ExpressionNode parseRelationalExpression() {
        ExpressionNode left = this.parseAdditiveExpression();

        while (this.position < this.input.size() && this.current().isRelationOperator()) {
            TokenType operator = this.consume().type;

            ExpressionNode right = this.parseAdditiveExpression();

            left = new BinaryExpressionNode(left, operator, right);
        }

        return left;
    }

    private StatementNode parseReturnStatement() {
        this.consume(TokenType.RETURN);

        if (this.current(TokenType.SEMICOLON)) {
            return new ReturnStatementNode(new NullExpressionNode());
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
        } else if (this.current(TokenType.FOREACH)) {
            statement = this.parseForeachStatement();
            if (this.current(TokenType.SEMICOLON)) {
                this.consume();
            }
        } else if (this.current(TokenType.RETURN)) {
            statement = this.parseReturnStatement();
            this.consume(TokenType.SEMICOLON);
        } else if (this.current(TokenType.BREAK)) {
            statement = this.parseBreakStatement();
            this.consume(TokenType.SEMICOLON);
        } else if (this.current(TokenType.CONTINUE)) {
            statement = this.parseContinueStatement();
            this.consume(TokenType.SEMICOLON);
        } else if (this.current(TokenType.DELETE)) {
            statement = this.parseDeleteStatement();
            this.consume(TokenType.SEMICOLON);
        } else if (this.current(TokenType.IMPORT)) {
            statement = this.parseImportStatement();
            this.consume(TokenType.SEMICOLON);
        } else if (this.current(TokenType.FROM)) {
            statement = this.parseFromStatement();
            this.consume(TokenType.SEMICOLON);
        } else if (this.current(TokenType.SWITCH)) {
            statement = this.parseSwitchStatement();

            if (this.current(TokenType.SEMICOLON)) {
                this.consume();
            }
        } else if (this.current(TokenType.YIELD)) {
            statement = this.parseYieldStatement();
            this.consume(TokenType.SEMICOLON);
        } else {
            return this.parseDeclarationStatement();
        }

        return statement;
    }

    private ExpressionNode parseSwitchExpression() {
        this.consume(TokenType.SWITCH);

        this.consume(TokenType.LEFT_PARENTHESIS);

        ExpressionNode toSwitch = this.parseExpression();

        this.consume(TokenType.RIGHT_PARENTHESIS);

        SwitchExpressionBody switchBody = this.parseSwitchExpressionBody();

        return new SwitchExpressionNode(toSwitch, switchBody);
    }

    private SwitchExpressionBody parseSwitchExpressionBody() {
        List<SwitchExpressionCase> body = new ArrayList<>();
        List<StatementNode> defaultCase = null;

        this.consume(TokenType.LEFT_BRACE);

        while (this.position < this.input.size() && (this.current(TokenType.CASE) || this.current(TokenType.DEFAULT))) {
            if (this.current(TokenType.DEFAULT)) {
                if (defaultCase != null) {
                    throw ErrorHolder.aSwitchStatementCanOnlyHave1DefaultCase();
                }

                this.consume();

                defaultCase = this.parseSwitchExpressionCaseBody();

                continue;
            }

            SwitchExpressionCase switchCase = this.parseSwitchExpressionCase();

            body.add(switchCase);
        }

        this.consume(TokenType.RIGHT_BRACE);

        return new SwitchExpressionBody(body, defaultCase);
    }

    private SwitchExpressionCase parseSwitchExpressionCase() {
        this.consume();

        List<ExpressionNode> expressions = new ArrayList<>();

        expressions.add(this.parseExpression());

        while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
            this.consume();

            expressions.add(this.parseExpression());
        }

        return new SwitchExpressionCase(expressions, this.parseSwitchExpressionCaseBody());
    }

    private List<StatementNode> parseSwitchExpressionCaseBody() {
        if (this.current(TokenType.RIGHT_ARROW)) {
            this.consume();

            ExpressionNode expression = this.parseExpression();

            this.consume(TokenType.SEMICOLON);

            return List.of(new YieldStatementNode(expression));
        } else if (this.current(TokenType.LEFT_BRACE)) {
            this.consume();

            List<StatementNode> body = new ArrayList<>();

            while (this.position < this.input.size() && !this.current(TokenType.RIGHT_BRACE)) {
                body.add(this.parseStatement());
            }

            this.consume();

            if (this.current(TokenType.SEMICOLON)) {
                this.consume();
            }

            return body;
        } else {
            throw new SyntaxError("Expected '->' or '{'");
        }
    }

    private StatementNode parseSwitchStatement() {
        this.consume(TokenType.SWITCH);

        this.consume(TokenType.LEFT_PARENTHESIS);

        ExpressionNode toSwitch = this.parseExpression();

        this.consume(TokenType.RIGHT_PARENTHESIS);

        SwitchStatementBody switchBody = this.parseSwitchStatementBody();

        return new SwitchStatementNode(toSwitch, switchBody);
    }

    private SwitchStatementBody parseSwitchStatementBody() {
        List<SwitchStatementCase> body = new ArrayList<>();
        List<StatementNode> defaultCase = null;

        this.consume(TokenType.LEFT_BRACE);

        while (this.position < this.input.size() && (this.current(TokenType.CASE) || this.current(TokenType.DEFAULT))) {
            if (this.current(TokenType.DEFAULT)) {
                if (defaultCase != null) {
                    throw ErrorHolder.aSwitchStatementCanOnlyHave1DefaultCase();
                }

                this.consume();

                defaultCase = this.parseSwitchStatementCaseBody();

                continue;
            }

            SwitchStatementCase switchCase = this.parseSwitchStatementCase();

            body.add(switchCase);
        }

        this.consume(TokenType.RIGHT_BRACE);

        return new SwitchStatementBody(body, defaultCase);
    }

    private SwitchStatementCase parseSwitchStatementCase() {
        this.consume();

        List<ExpressionNode> expressions = new ArrayList<>();

        expressions.add(this.parseExpression());

        while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
            this.consume();

            expressions.add(this.parseExpression());
        }

        return new SwitchStatementCase(expressions, this.parseSwitchStatementCaseBody());
    }

    private List<StatementNode> parseSwitchStatementCaseBody() {
        if (this.current(TokenType.RIGHT_ARROW)) {
            this.consume();

            return List.of(this.parseStatement());
        } else if (this.current(TokenType.LEFT_BRACE)) {
            this.consume();

            List<StatementNode> body = new ArrayList<>();

            while (this.position < this.input.size() && !this.current(TokenType.RIGHT_BRACE)) {
                body.add(this.parseStatement());
            }

            this.consume();

            if (this.current(TokenType.SEMICOLON)) {
                this.consume();
            }

            return body;
        } else {
            throw new SyntaxError("Expected '{' or '->'");
        }
    }

    private ExpressionNode parseTernaryExpression() {
        ExpressionNode condition = this.parseConditionalOrExpression();

        if (this.current(TokenType.QUESTION_MARK)) {
            this.consume(TokenType.QUESTION_MARK);

            ExpressionNode ifTrue = this.parseExpression();

            this.consume(TokenType.COLON);

            ExpressionNode ifFalse = this.parseExpression();

            return new TernaryExpressionNode(condition, ifTrue, ifFalse);
        }

        return condition;
    }

    private ExpressionNode parseUnaryExpression() {
        if (this.current().isUnaryOperator()) {
            TokenType unaryOperator = this.consume().type;

            ExpressionNode expression = this.parsePostfixExpression();

            return new UnaryExpressionNode(unaryOperator, expression);
        }

        return this.parsePostfixExpression();
    }

    private VariableDeclarationStatementNode parseVariableDeclarationStatement(List<TokenType> accessModifiers) {
        TokenType.validateVariableAccessModifiers(accessModifiers);

        this.consume();

        String name = (String) this.consume(TokenType.IDENTIFIER).value;

        String type = null;

        if (this.current(TokenType.COLON)) {
            this.consume(TokenType.COLON);

            type = (String) this.consume(TokenType.IDENTIFIER).value;
        }

        if (this.current(TokenType.SEMICOLON)) {
            return new VariableDeclarationStatementNode(accessModifiers, type, name, new NullExpressionNode());
        }

        this.consume(TokenType.EQUALS);

        ExpressionNode expression = this.parseExpression();

        return new VariableDeclarationStatementNode(accessModifiers, type, name, expression);
    }

    private StatementNode parseWhileStatement() {
        this.consume(TokenType.WHILE);

        this.consume(TokenType.LEFT_PARENTHESIS);

        ExpressionNode expression = this.parseExpression();

        this.consume(TokenType.RIGHT_PARENTHESIS);

        List<StatementNode> body = this.parseBody();

        return new WhileStatementNode(expression, body);
    }

    private StatementNode parseYieldStatement() {
        this.consume(TokenType.YIELD);

        ExpressionNode expression = this.parseExpression();

        return new YieldStatementNode(expression);
    }
}
