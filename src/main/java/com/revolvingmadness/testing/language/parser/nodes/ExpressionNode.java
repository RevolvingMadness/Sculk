package com.revolvingmadness.testing.language.parser.nodes;

public interface ExpressionNode extends Node {
    ExpressionNode interpret(ScriptNode script);

    IdentifierExpressionNode getType(ScriptNode script);

    ExpressionNode add(ScriptNode script, ExpressionNode other);

    ExpressionNode subtract(ScriptNode script, ExpressionNode other);

    ExpressionNode multiply(ScriptNode script, ExpressionNode other);

    ExpressionNode divide(ScriptNode script, ExpressionNode other);

    ExpressionNode exponentiate(ScriptNode script, ExpressionNode other);

    ExpressionNode mod(ScriptNode script, ExpressionNode other);
}
