package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

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
    public BooleanExpressionNode equalTo(LiteralExpressionNode other) {
        if (other instanceof StringExpressionNode stringExpression) {
            return new BooleanExpressionNode(this.value.equals(stringExpression.value));
        }

        return new BooleanExpressionNode(false);
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;

        StringExpressionNode otherStringExpression = (StringExpressionNode) otherObject;

        return value.equals(otherStringExpression.value);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("string");
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public LiteralExpressionNode multiply(LiteralExpressionNode other) {
        if (other instanceof IntegerExpressionNode integerExpression) {
            return new StringExpressionNode(this.value.repeat(integerExpression.value));
        }

        throw new TypeError("Unsupported binary operator '*' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public BooleanExpressionNode notEqualTo(LiteralExpressionNode other) {
        if (other instanceof StringExpressionNode stringExpression) {
            return new BooleanExpressionNode(!this.value.equals(stringExpression.value));
        }

        return new BooleanExpressionNode(true);
    }

    @Override
    public BooleanExpressionNode toBooleanType() {
        return new BooleanExpressionNode(!Objects.equals(this.value, ""));
    }

    @Override
    public String toString() {
        return "\"" + this.value + "\"";
    }

    @Override
    public StringExpressionNode toStringType() {
        return new StringExpressionNode(this.value);
    }
}
