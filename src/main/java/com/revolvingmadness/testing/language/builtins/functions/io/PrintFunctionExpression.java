package com.revolvingmadness.testing.language.builtins.functions.io;

import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.interpreter.FunctionSignature;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

import java.util.List;

public class PrintFunctionExpression implements LiteralExpressionNode {
    public final FunctionSignature signature;

    public PrintFunctionExpression() {
        this.signature = new FunctionSignature(new IdentifierExpressionNode("print"), List.of(new IdentifierExpressionNode("value")));
    }

    @Override
    public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
        if (arguments.size() != 1) {
            throw new SyntaxError("Function '" + this.signature.name + "' requires 1 argument but was passed " + arguments.size());
        }

        LiteralExpressionNode interpretedFirstArgument = arguments.get(0).interpret(script);

        Logger.broadcast(interpretedFirstArgument.toString(), true);

        return this;
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("function");
    }
}
