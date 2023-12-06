package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

public class NullExpressionNode implements LiteralExpressionNode {

    @Override
    public BooleanExpressionNode equalTo(LiteralExpressionNode other) {
        if (other instanceof NullExpressionNode) {
            return new BooleanExpressionNode(true);
        }

        return new BooleanExpressionNode(false);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("null");
    }

	@Override
    public boolean isTruthy() {
        return false;
    }

    @Override
    public BooleanExpressionNode notEqualTo(LiteralExpressionNode other) {
        if (other instanceof NullExpressionNode) {
            return new BooleanExpressionNode(false);
        }

        return new BooleanExpressionNode(true);
    }

    @Override
    public String toString() {
        return "null";
    }
}
