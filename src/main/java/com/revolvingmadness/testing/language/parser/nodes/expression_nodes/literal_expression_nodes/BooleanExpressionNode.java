package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

public class BooleanExpressionNode implements LiteralExpressionNode {
    public final Boolean value;

    public BooleanExpressionNode(boolean value) {
        this.value = value;
    }

    @Override
    public LiteralExpressionNode add(LiteralExpressionNode other) {
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
        return new IdentifierExpressionNode("boolean");
    }

    @Override
    public boolean isTruthy() {
        return value;
    }

    @Override
    public BooleanExpressionNode equalTo(LiteralExpressionNode other) {
        if (other instanceof BooleanExpressionNode booleanExpression) {
            return new BooleanExpressionNode(this.value.equals(booleanExpression.value));
        }

        return new BooleanExpressionNode(false);
    }

    @Override
    public BooleanExpressionNode notEqualTo(LiteralExpressionNode other) {
        if (other instanceof BooleanExpressionNode booleanExpression) {
            return new BooleanExpressionNode(!this.value.equals(booleanExpression.value));
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
        return new BooleanExpressionNode(!this.value);
    }

    @Override
    public LiteralExpressionNode mod(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '%' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode multiply(LiteralExpressionNode other) {
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

        BooleanExpressionNode otherBooleanExpression = (BooleanExpressionNode) otherObject;

        return value.equals(otherBooleanExpression.value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public LiteralExpressionNode interpret(ScriptNode script) {
        return this;
    }
}
