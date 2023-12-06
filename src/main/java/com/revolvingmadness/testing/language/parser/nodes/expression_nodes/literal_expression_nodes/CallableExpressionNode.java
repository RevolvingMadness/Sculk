package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

import java.util.List;
import java.util.Map;

public abstract class CallableExpressionNode implements LiteralExpressionNode {
	public final Map<IdentifierExpressionNode, IdentifierExpressionNode> arguments;
	public final IdentifierExpressionNode name;
	public final IdentifierExpressionNode returnType;

	protected CallableExpressionNode(IdentifierExpressionNode name, IdentifierExpressionNode returnType, Map<IdentifierExpressionNode, IdentifierExpressionNode> arguments) {
		this.name = name;
		this.returnType = returnType;
		this.arguments = arguments;
	}

	public abstract LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments);

	public boolean isTruthy() {
		return true;
	}

	public void processArguments(ScriptNode script, List<ExpressionNode> callArguments) {
		if (callArguments.size() != this.arguments.size()) {
			throw new TypeError("Function '" + this.name + "' takes '" + this.arguments.size() + "' argument(s) but '" + callArguments.size() + "' argument(s) were given");
		}

		int argumentNumber = 0;

		for (Map.Entry<IdentifierExpressionNode, IdentifierExpressionNode> entry : this.arguments.entrySet()) {
			IdentifierExpressionNode argumentName = entry.getKey();
			IdentifierExpressionNode argumentType = entry.getValue();

			LiteralExpressionNode argumentValue = callArguments.get(argumentNumber++).interpret(script);

			if (!argumentType.equals(argumentValue.getType())) {
				throw new TypeError("Expected type '" + argumentType + "' for argument '" + argumentName + "' but got type '" + argumentValue.getType() + "'");
			}

			script.variableTable.declareAndOrAssign(argumentType, argumentName, argumentValue);
		}
	}
}
