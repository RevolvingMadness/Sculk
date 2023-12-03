package com.revolvingmadness.testing.language.parser.nodes.expression;

import com.revolvingmadness.testing.language.parser.error.TypeError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;

public class BooleanExpressionNode implements LiteralExpressionNode {
    public final Boolean value;

    public BooleanExpressionNode(Boolean value) {
        this.value = value;
    }

    @Override
    public LiteralExpressionNode interpret(ScriptNode script) {
        return this;
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("boolean");
    }

    @Override
    public Boolean isTruthy() {
        return value;
    }

    @Override
    public LiteralExpressionNode negate() {
        throw new TypeError("Unsupported unary operator '-' for type '" + this.getType() + "'");
    }

    @Override
    public LiteralExpressionNode logicalNot() {
        return new BooleanExpressionNode(!this.value);
    }

    @Override
    public LiteralExpressionNode add(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '+' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode subtract(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '-' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode multiply(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '*' for types '" + this.getType() + "' and '" + other.getType() + "'");
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
    public LiteralExpressionNode mod(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '%' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;

        BooleanExpressionNode otherIntegerExpression = (BooleanExpressionNode) otherObject;

        return value.equals(otherIntegerExpression.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
