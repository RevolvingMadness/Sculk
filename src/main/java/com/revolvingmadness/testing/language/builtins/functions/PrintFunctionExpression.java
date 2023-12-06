package com.revolvingmadness.testing.language.builtins.functions;

import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.CallableExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.NullExpressionNode;

import java.util.List;
import java.util.Map;

public class PrintFunctionExpression extends CallableExpressionNode {
	public PrintFunctionExpression() {
		super(new IdentifierExpressionNode("print"), new IdentifierExpressionNode("null"), Map.of(new IdentifierExpressionNode("value"), new IdentifierExpressionNode("string")));
	}

	@Override
	public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
		this.processArguments(script, arguments);
		LiteralExpressionNode interpretedFirstArgument = arguments.get(0).interpret(script);
		Logger.broadcast(interpretedFirstArgument.toString(), true);
		return new NullExpressionNode();
	}

	@Override
	public IdentifierExpressionNode getType() {
		return new IdentifierExpressionNode("function");
	}
}
