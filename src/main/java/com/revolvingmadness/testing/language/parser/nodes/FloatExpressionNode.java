package com.revolvingmadness.testing.language.parser.nodes;

import com.revolvingmadness.testing.language.parser.error.TypeError;

public class FloatExpressionNode implements NumberExpressionNode {
    public Double value;

    public FloatExpressionNode(Double value) {
        this.value = value;
    }

    @Override
    public ExpressionNode interpret(ScriptNode script) {
        return this;
    }

    @Override
    public IdentifierExpressionNode getType(ScriptNode script) {
        return new IdentifierExpressionNode("float");
    }

    @Override
    public ExpressionNode add(ScriptNode script, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value + floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(this.value + integerExpression.value);
        }

        throw new TypeError("Unsupported operator '+' for types '" + this.getType(script) + "' and '" + other.getType(script) + "'");
    }

    @Override
    public ExpressionNode subtract(ScriptNode script, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value - floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(this.value - integerExpression.value);
        }
        throw new TypeError("Unsupported operator '-' for types '" + this.getType(script) + "' and '" + other.getType(script) + "'");
    }

    @Override
    public ExpressionNode multiply(ScriptNode script, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value * floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(this.value * integerExpression.value);
        }
        throw new TypeError("Unsupported operator '*' for types '" + this.getType(script) + "' and '" + other.getType(script) + "'");
    }

    @Override
    public ExpressionNode divide(ScriptNode script, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value / floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(this.value / integerExpression.value);
        }
        throw new TypeError("Unsupported operator '/' for types '" + this.getType(script) + "' and '" + other.getType(script) + "'");
    }

    @Override
    public ExpressionNode exponentiate(ScriptNode script, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(Math.pow(this.value, floatExpression.value));
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(Math.pow(this.value, integerExpression.value));
        }
        throw new TypeError("Unsupported operator '^' for types '" + this.getType(script) + "' and '" + other.getType(script) + "'");
    }

    @Override
    public ExpressionNode mod(ScriptNode script, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value % floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(this.value % integerExpression.value);
        }
        throw new TypeError("Unsupported operator '%' for types '" + this.getType(script) + "' and '" + other.getType(script) + "'");
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

        FloatExpressionNode otherFloatExpression = (FloatExpressionNode) otherObject;

        return value.equals(otherFloatExpression.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
