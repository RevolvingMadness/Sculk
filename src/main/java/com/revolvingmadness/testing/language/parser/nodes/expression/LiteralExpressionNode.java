package com.revolvingmadness.testing.language.parser.nodes.expression;

public interface LiteralExpressionNode extends ExpressionNode {
    IdentifierExpressionNode getType();

    Boolean isTruthy();

    LiteralExpressionNode negate();

    LiteralExpressionNode logicalNot();

    LiteralExpressionNode add(LiteralExpressionNode other);

    LiteralExpressionNode subtract(LiteralExpressionNode other);

    LiteralExpressionNode multiply(LiteralExpressionNode other);

    LiteralExpressionNode divide(LiteralExpressionNode other);

    LiteralExpressionNode exponentiate(LiteralExpressionNode other);

    LiteralExpressionNode mod(LiteralExpressionNode other);
}
