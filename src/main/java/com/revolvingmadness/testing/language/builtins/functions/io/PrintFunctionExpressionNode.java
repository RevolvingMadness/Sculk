package com.revolvingmadness.testing.language.builtins.functions.io;

import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

import java.util.List;

public class PrintFunctionExpressionNode implements LiteralExpressionNode {
    @Override
    public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
        if (arguments.size() != 1) {
            throw new SyntaxError("Function 'print' requires 1 argument but got " + arguments.size() + " argument(s)");
        }

        LiteralExpressionNode interpretedFirstArgument = arguments.get(0).interpret(script);

        Logger.broadcast(interpretedFirstArgument.toStringType().value, true);

        return this;
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("function");
    }
}