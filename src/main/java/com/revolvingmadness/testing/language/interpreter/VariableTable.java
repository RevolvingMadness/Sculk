package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.language.builtins.functions.PrintFunctionExpression;
import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

import java.util.ListIterator;
import java.util.Objects;
import java.util.Optional;
import java.util.Stack;

public class VariableTable {
	public final ScriptNode script;
	public final Stack<VariableScope> variableScopes;

	public VariableTable(ScriptNode script) {
		this.script = script;
		this.variableScopes = new Stack<>();
		this.reset();
	}

	private void assign(IdentifierExpressionNode name, ExpressionNode value) {
		Objects.requireNonNull(name);

		LiteralExpressionNode interpretedValue = value.interpret(this.script);

		Logger.info("Assigning '" + name + "' to the value '" + interpretedValue + "'");

		Variable existingVariable = this.getOrThrow(name);

		IdentifierExpressionNode interpretedValueType = interpretedValue.getType();

		if (!existingVariable.type.equals(interpretedValueType) && !interpretedValueType.value.equals("null")) {
			throw new TypeError("Expected type '" + interpretedValueType + "', but got type '" + existingVariable.type + "'");
		}

		existingVariable.value = interpretedValue;
	}

	public void createScope() {
		this.variableScopes.add(new VariableScope());
	}

	private void declare(IdentifierExpressionNode type, IdentifierExpressionNode name) {
		Objects.requireNonNull(name);

		Logger.info("Declaring '" + name + "'");

		Optional<Variable> variable = this.variableScopes.peek().get(name);

		if (variable.isPresent()) {
			throw new NameError("Variable '" + name + "' has already been declared");
		}

		this.variableScopes.peek().declare(type, name);
	}

	public void declareAndOrAssign(IdentifierExpressionNode type, IdentifierExpressionNode name, ExpressionNode value) {
		Objects.requireNonNull(name);

		if (type != null) {
			this.declare(type, name);
		}

		if (value != null) {
			this.assign(name, value);
		}
	}

	public void exitScope() {
		this.variableScopes.pop();
	}

	public Optional<Variable> get(IdentifierExpressionNode name) {
		Objects.requireNonNull(name);

		ListIterator<VariableScope> variableScopesIterator = this.variableScopes.listIterator();

		while (variableScopesIterator.hasNext()) {
			variableScopesIterator.next();
		}

		while (variableScopesIterator.hasPrevious()) {
			VariableScope variableScope = variableScopesIterator.previous();

			Optional<Variable> variable = variableScope.get(name);

			if (variable.isPresent()) {
				return variable;
			}
		}

		return Optional.empty();
	}

	public Variable getOrThrow(IdentifierExpressionNode name) {
		Objects.requireNonNull(name);

		Optional<Variable> variable = this.get(name);

		if (variable.isEmpty()) {
			throw new NameError("Variable '" + name + "' has not been declared");
		}

		return variable.get();
	}

	public void reset() {
		this.variableScopes.clear();
		this.variableScopes.add(new VariableScope());

		this.declareAndOrAssign(new IdentifierExpressionNode("function"), new IdentifierExpressionNode("print"), new PrintFunctionExpression());
	}
}
