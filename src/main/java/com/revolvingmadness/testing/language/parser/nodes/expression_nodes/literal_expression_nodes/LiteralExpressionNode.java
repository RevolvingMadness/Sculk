package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

public interface LiteralExpressionNode extends ExpressionNode {
    LiteralExpressionNode add(LiteralExpressionNode other);

    LiteralExpressionNode divide(LiteralExpressionNode other);

    BooleanExpressionNode equalTo(LiteralExpressionNode other);

    LiteralExpressionNode exponentiate(LiteralExpressionNode other);

    IdentifierExpressionNode getType();

    BooleanExpressionNode greaterThan(LiteralExpressionNode other);

    BooleanExpressionNode greaterThanOrEqualTo(LiteralExpressionNode other);

    boolean isTruthy();

    BooleanExpressionNode lessThan(LiteralExpressionNode other);

    BooleanExpressionNode lessThanOrEqualTo(LiteralExpressionNode other);

    LiteralExpressionNode logicalNot();

    LiteralExpressionNode mod(LiteralExpressionNode other);

    LiteralExpressionNode multiply(LiteralExpressionNode other);

    LiteralExpressionNode negate();

    BooleanExpressionNode notEqualTo(LiteralExpressionNode other);

    LiteralExpressionNode subtract(LiteralExpressionNode other);
}
