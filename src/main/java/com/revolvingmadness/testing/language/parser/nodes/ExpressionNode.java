package com.revolvingmadness.testing.language.parser.nodes;

public interface ExpressionNode extends Node {
    ExpressionNode interpret(ScriptNode program);

    IdentifierExpressionNode getType(ScriptNode program);

    ExpressionNode add(ScriptNode program, ExpressionNode other);
    ExpressionNode subtract(ScriptNode program, ExpressionNode other);
    ExpressionNode multiply(ScriptNode program, ExpressionNode other);
    ExpressionNode divide(ScriptNode program, ExpressionNode other);
    ExpressionNode exponentiate(ScriptNode program, ExpressionNode other);
    ExpressionNode mod(ScriptNode program, ExpressionNode other);
}
