package com.revolvingmadness.testing.language.parser;

import com.revolvingmadness.testing.backend.LangScript;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.lexer.Token;
import com.revolvingmadness.testing.language.lexer.TokenType;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.*;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.*;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.*;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

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

	private AssignmentStatementNode parseAssignmentStatement() {
		IdentifierExpressionNode typeOrName = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected variable type or name").value);

		if (this.current(TokenType.IDENTIFIER)) {
			IdentifierExpressionNode name = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected variable name").value);

			if (this.current(TokenType.SEMICOLON)) {
				return new AssignmentStatementNode(typeOrName, name, new NullExpressionNode());
			}

			this.consume(TokenType.EQUALS, "Expected equals after variable name");

			ExpressionNode expression = this.parseExpression();

			return new AssignmentStatementNode(typeOrName, name, expression);
		}

		if (this.current().isIncrementOrDecrementOperator()) {
			TokenType incrementOrDecrementOperator = this.consume().type;

			return new AssignmentStatementNode(null, typeOrName, new BinaryExpressionNode(typeOrName, incrementOrDecrementOperator, new IntegerExpressionNode(1)));
		}

		TokenType shorthandAssignmentOperator = null;

		if (this.current().isBinaryOperator()) {
			shorthandAssignmentOperator = this.consume().type;
		}

		this.consume(TokenType.EQUALS, "Expected equals after variable name");

		ExpressionNode expression = this.parseExpression();

		if (shorthandAssignmentOperator != null) {
			return new AssignmentStatementNode(null, typeOrName, new BinaryExpressionNode(typeOrName, shorthandAssignmentOperator, expression));
		}

		return new AssignmentStatementNode(null, typeOrName, expression);
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

	private StatementNode parseContinueStatement() {
		this.consume();

		return new ContinueStatementNode();
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

	private StatementNode parseForStatement() {
		this.consume();

		this.consume(TokenType.LEFT_PARENTHESIS, "Expected left parenthesis after 'for'");

		AssignmentStatementNode initialization = this.parseAssignmentStatement();

		this.consume(TokenType.SEMICOLON, "Expected semicolon after variable assignment");

		ExpressionNode condition = this.parseExpression();

		this.consume(TokenType.SEMICOLON, "Expected semicolon after variable assignment");

		AssignmentStatementNode update = this.parseAssignmentStatement();

		if (this.current(TokenType.SEMICOLON)) {
			this.consume();
		}

		this.consume(TokenType.RIGHT_PARENTHESIS, "Expected closing parenthesis after for update");

		List<StatementNode> body = this.parseBody();

		return new ForStatementNode(initialization, condition, update, body);
	}

	@NotNull
	private FunctionCallExpressionNode parseFunctionCallExpression(IdentifierExpressionNode name) {
		this.consume();

		List<ExpressionNode> arguments = new ArrayList<>();

		if (!this.current(TokenType.RIGHT_PARENTHESIS)) {
			arguments.add(this.parseExpression());
		}

		while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
			this.consume();

			arguments.add(this.parseExpression());
		}

		this.consume(TokenType.RIGHT_PARENTHESIS, "Expected closing parenthesis after function call arguments");

		return new FunctionCallExpressionNode(name, arguments);
	}

	@NotNull
	private StatementNode parseFunctionCallStatement() {
		StatementNode statement;
		IdentifierExpressionNode name = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected function name").value);

		this.consume();

		List<ExpressionNode> arguments = new ArrayList<>();

		if (!this.current(TokenType.RIGHT_PARENTHESIS)) {
			arguments.add(this.parseExpression());
		}

		while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
			this.consume();

			arguments.add(this.parseExpression());
		}

		this.consume(TokenType.RIGHT_PARENTHESIS, "Expected closing parenthesis after function call arguments");

		statement = new FunctionCallStatementNode(name, arguments);
		return statement;
	}

	private StatementNode parseFunctionDeclarationStatement() {
		this.consume();

		IdentifierExpressionNode name = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected function name").value);

		this.consume(TokenType.LEFT_PARENTHESIS, "Expected opening parenthesis after function declaration name");

		Map<IdentifierExpressionNode, IdentifierExpressionNode> arguments = new HashMap<>();

		if (this.current(TokenType.IDENTIFIER)) {
			IdentifierExpressionNode argumentType = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected argument type").value);
			IdentifierExpressionNode argumentName = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected argument name").value);
			arguments.put(argumentName, argumentType);
		}

		while (this.position < this.input.size() && this.current(TokenType.COMMA)) {
			this.consume();

			IdentifierExpressionNode argumentType = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected argument type").value);
			IdentifierExpressionNode argumentName = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected argument name").value);
			arguments.put(argumentName, argumentType);
		}

		this.consume(TokenType.RIGHT_PARENTHESIS, "Expected closing parenthesis after function declaration");

		IdentifierExpressionNode returnType;

		if (this.current(TokenType.RIGHT_ARROW)) {
			this.consume();

			returnType = new IdentifierExpressionNode((String) this.consume(TokenType.IDENTIFIER, "Expected return type").value);
		} else {
			returnType = new IdentifierExpressionNode("null");
		}

		List<StatementNode> body = this.parseBody();

		return new FunctionDeclarationStatement(name, arguments, returnType, body);
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
			IdentifierExpressionNode name = new IdentifierExpressionNode((String) this.consume().value);

			if (this.current(TokenType.LEFT_PARENTHESIS)) {
				return this.parseFunctionCallExpression(name);
			} else {
				return name;
			}
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
		StatementNode statement = null;

		if (this.current(TokenType.IMPORT)) {
			statement = this.parseImportStatement();
			this.consume(TokenType.SEMICOLON, "Expected semicolon after import statement");
		} else if (this.current(TokenType.IDENTIFIER)) {
			if (this.next(TokenType.LEFT_PARENTHESIS)) {
				statement = this.parseFunctionCallStatement();

				this.consume(TokenType.SEMICOLON, "Expected semicolon after function call");
			} else {
				statement = this.parseAssignmentStatement();
				this.consume(TokenType.SEMICOLON, "Expected semicolon after variable assignment");
			}
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
		} else if (this.current(TokenType.FUNCTION)) {
			statement = this.parseFunctionDeclarationStatement();
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
		}

		if (statement == null) {
			throw new SyntaxError("Expected 'IMPORT', 'IDENTIFIER', 'IF', 'WHILE', 'FOR', or 'FUNCTION', got '" + this.current().type + "'");
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

	private StatementNode parseWhileStatement() {
		this.consume();

		this.consume(TokenType.LEFT_PARENTHESIS, "Expected opening parenthesis after 'while'");

		ExpressionNode expression = this.parseExpression();

		this.consume(TokenType.RIGHT_PARENTHESIS, "Expected closing parenthesis after while condition");

		List<StatementNode> body = this.parseBody();

		return new WhileStatementNode(expression, body);
	}
}
