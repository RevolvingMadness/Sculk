package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.NullExpressionNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VariableScope {
	public final List<Variable> variables;

	public VariableScope() {
		this.variables = new ArrayList<>();
	}

	public void declare(IdentifierExpressionNode type, IdentifierExpressionNode name) {
		Logger.info("Declaring '" + name + "'");

		Optional<Variable> variable = this.get(name);

		if (variable.isPresent()) {
			throw new NameError("Variable '" + name + "' has already been declared");
		}

		this.variables.add(new Variable(type, name, new NullExpressionNode()));
	}

	public Optional<Variable> get(IdentifierExpressionNode name) {
		return this.variables.stream().filter(variable -> variable.name.equals(name)).findAny();
	}
}
