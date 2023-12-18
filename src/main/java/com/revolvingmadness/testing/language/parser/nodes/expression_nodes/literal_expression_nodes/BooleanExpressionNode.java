package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;

public class BooleanExpressionNode implements LiteralExpressionNode {
    public final Boolean value;

    public BooleanExpressionNode(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || this.getClass() != otherObject.getClass())
            return false;

        BooleanExpressionNode otherBooleanExpression = (BooleanExpressionNode) otherObject;

        return this.value.equals(otherBooleanExpression.value);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("boolean");
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public LiteralExpressionNode logicalNot() {
        return new BooleanExpressionNode(!this.value);
    }

    @Override
    public BooleanExpressionNode toBooleanType() {
        return this;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
