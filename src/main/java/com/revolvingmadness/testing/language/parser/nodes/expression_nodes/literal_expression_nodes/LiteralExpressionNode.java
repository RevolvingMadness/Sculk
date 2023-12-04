package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

public interface LiteralExpressionNode extends ExpressionNode {
    LiteralExpressionNode add(LiteralExpressionNode other);

    LiteralExpressionNode divide(LiteralExpressionNode other);

    LiteralExpressionNode exponentiate(LiteralExpressionNode other);

    IdentifierExpressionNode getType();

    boolean isTruthy();

    BooleanExpressionNode equalTo(LiteralExpressionNode other);

    BooleanExpressionNode notEqualTo(LiteralExpressionNode other);

    BooleanExpressionNode greaterThan(LiteralExpressionNode other);

    BooleanExpressionNode greaterThanOrEqualTo(LiteralExpressionNode other);

    BooleanExpressionNode lessThan(LiteralExpressionNode other);

    BooleanExpressionNode lessThanOrEqualTo(LiteralExpressionNode other);

    LiteralExpressionNode logicalNot();

    LiteralExpressionNode mod(LiteralExpressionNode other);

    LiteralExpressionNode multiply(LiteralExpressionNode other);

    LiteralExpressionNode negate();

    LiteralExpressionNode subtract(LiteralExpressionNode other);
}
