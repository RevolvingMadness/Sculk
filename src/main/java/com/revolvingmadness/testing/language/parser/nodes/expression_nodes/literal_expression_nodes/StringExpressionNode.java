package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
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
    public LiteralExpressionNode divide(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '/' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode exponentiate(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '^' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("string");
    }

    @Override
    public boolean isTruthy() {
        return !Objects.equals(this.value, "");
    }

    @Override
    public BooleanExpressionNode equalTo(LiteralExpressionNode other) {
        if (other instanceof StringExpressionNode stringExpression) {
            return new BooleanExpressionNode(this.value.equals(stringExpression.value));
        }

        return new BooleanExpressionNode(false);
    }

    @Override
    public BooleanExpressionNode notEqualTo(LiteralExpressionNode other) {
        if (other instanceof StringExpressionNode stringExpression) {
            return new BooleanExpressionNode(!this.value.equals(stringExpression.value));
        }

        return new BooleanExpressionNode(true);
    }

    @Override
    public BooleanExpressionNode greaterThan(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '>' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public BooleanExpressionNode greaterThanOrEqualTo(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '>=' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public BooleanExpressionNode lessThan(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '<' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public BooleanExpressionNode lessThanOrEqualTo(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '<=' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode logicalNot() {
        throw new TypeError("Unsupported unary operator '!' for type '" + this.getType() + "'");
    }

    @Override
    public LiteralExpressionNode mod(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '%' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode multiply(LiteralExpressionNode other) {
        if (other instanceof IntegerExpressionNode integerExpression) {
            return new StringExpressionNode(this.value.repeat(integerExpression.value));
        }

        throw new TypeError("Unsupported binary operator '*' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode negate() {
        throw new TypeError("Unsupported unary operator '-' for type '" + this.getType() + "'");
    }

    @Override
    public LiteralExpressionNode subtract(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '-' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public int hashCode() {
        return value.hashCode();
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
    public String toString() {
        return this.value;
    }

    @Override
    public LiteralExpressionNode interpret(ScriptNode script) {
        return this;
    }
}
