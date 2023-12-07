package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

public class BooleanExpressionNode implements LiteralExpressionNode {
    public final Boolean value;

    public BooleanExpressionNode(boolean value) {
        this.value = value;
    }

    @Override
    public BooleanExpressionNode equalTo(LiteralExpressionNode other) {
        if (other instanceof BooleanExpressionNode booleanExpression) {
            return new BooleanExpressionNode(this.value.equals(booleanExpression.value));
        }

        return new BooleanExpressionNode(false);
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;

        BooleanExpressionNode otherBooleanExpression = (BooleanExpressionNode) otherObject;

        return value.equals(otherBooleanExpression.value);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("boolean");
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean isTruthy() {
        return value;
    }

    @Override
    public LiteralExpressionNode logicalNot() {
        return new BooleanExpressionNode(!this.value);
    }

    @Override
    public BooleanExpressionNode notEqualTo(LiteralExpressionNode other) {
        if (other instanceof BooleanExpressionNode booleanExpression) {
            return new BooleanExpressionNode(!this.value.equals(booleanExpression.value));
        }

        return new BooleanExpressionNode(true);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
