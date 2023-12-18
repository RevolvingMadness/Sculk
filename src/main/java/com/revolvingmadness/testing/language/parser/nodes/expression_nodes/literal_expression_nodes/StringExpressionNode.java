package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;

import java.util.Objects;

public class StringExpressionNode implements LiteralExpressionNode {
    public final String value;

    public StringExpressionNode(String value) {
        this.value = value;
    }

    @Override
    public LiteralExpressionNode add(LiteralExpressionNode other) {
        if (other instanceof StringExpressionNode stringExpression) {
            return new StringExpressionNode(this.value + stringExpression.value);
        }

        throw new TypeError("Unsupported binary operator '+' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || this.getClass() != otherObject.getClass())
            return false;

        StringExpressionNode otherStringExpression = (StringExpressionNode) otherObject;

        return this.value.equals(otherStringExpression.value);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("string");
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public LiteralExpressionNode multiply(LiteralExpressionNode other) {
        if (other instanceof IntegerExpressionNode integerExpression) {
            return new StringExpressionNode(this.value.repeat(integerExpression.value));
        }

        throw new TypeError("Unsupported binary operator '*' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public BooleanExpressionNode toBooleanType() {
        return new BooleanExpressionNode(!Objects.equals(this.value, ""));
    }

    @Override
    public String toString() {
        return this.value;
    }
}
