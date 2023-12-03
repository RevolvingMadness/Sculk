package com.revolvingmadness.testing.language.parser.nodes.expression;

public interface LiteralExpressionNode extends ExpressionNode {
    LiteralExpressionNode add(LiteralExpressionNode other);

    LiteralExpressionNode divide(LiteralExpressionNode other);

    LiteralExpressionNode exponentiate(LiteralExpressionNode other);

    IdentifierExpressionNode getType();

    boolean isTruthy();

    LiteralExpressionNode logicalNot();

    LiteralExpressionNode mod(LiteralExpressionNode other);

    LiteralExpressionNode multiply(LiteralExpressionNode other);

    LiteralExpressionNode negate();

    LiteralExpressionNode subtract(LiteralExpressionNode other);
}
