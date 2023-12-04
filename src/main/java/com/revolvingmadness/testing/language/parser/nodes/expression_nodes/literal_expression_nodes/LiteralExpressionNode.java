package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

public interface LiteralExpressionNode extends ExpressionNode {
    LiteralExpressionNode add(LiteralExpressionNode other);

    LiteralExpressionNode divide(LiteralExpressionNode other);

    LiteralExpressionNode exponentiate(LiteralExpressionNode other);

    IdentifierExpressionNode getType();

    boolean isTruthy();

    LiteralExpressionNode equalTo(LiteralExpressionNode other);

    LiteralExpressionNode notEqualTo(LiteralExpressionNode other);

    LiteralExpressionNode greaterThan(LiteralExpressionNode other);

    LiteralExpressionNode greaterThanOrEqualTo(LiteralExpressionNode other);

    LiteralExpressionNode lessThan(LiteralExpressionNode other);

    LiteralExpressionNode lessThanOrEqualTo(LiteralExpressionNode other);

    LiteralExpressionNode logicalNot();

    LiteralExpressionNode mod(LiteralExpressionNode other);

    LiteralExpressionNode multiply(LiteralExpressionNode other);

    LiteralExpressionNode negate();

    LiteralExpressionNode subtract(LiteralExpressionNode other);
}
