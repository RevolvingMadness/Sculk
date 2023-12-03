package com.revolvingmadness.testing.language.parser.nodes;

import com.revolvingmadness.testing.language.parser.error.TypeError;

public class IntegerExpressionNode implements NumberExpressionNode {
    public Integer value;

    public IntegerExpressionNode(Integer value) {
        this.value = value;
    }

    @Override
    public ExpressionNode interpret(ScriptNode program) {
        return this;
    }

    @Override
    public IdentifierExpressionNode getType(ScriptNode program) {
        return new IdentifierExpressionNode("int");
    }

    @Override
    public ExpressionNode add(ScriptNode program, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value + floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new IntegerExpressionNode(this.value + integerExpression.value);
        }

        throw new TypeError("Unsupported operator '+' for types '" + this.getType(program) + "' and '" + other.getType(program) + "'");
    }

    @Override
    public ExpressionNode subtract(ScriptNode program, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value - floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new IntegerExpressionNode(this.value - integerExpression.value);
        }

        throw new TypeError("Unsupported operator '-' for types '" + this.getType(program) + "' and '" + other.getType(program) + "'");
    }

    @Override
    public ExpressionNode multiply(ScriptNode program, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value * floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new IntegerExpressionNode(this.value * integerExpression.value);
        }

        throw new TypeError("Unsupported operator '*' for types '" + this.getType(program) + "' and '" + other.getType(program) + "'");
    }

    @Override
    public ExpressionNode divide(ScriptNode program, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value / floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new IntegerExpressionNode(this.value / integerExpression.value);
        }

        throw new TypeError("Unsupported operator '/' for types '" + this.getType(program) + "' and '" + other.getType(program) + "'");
    }

    @Override
    public ExpressionNode exponentiate(ScriptNode program, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(Math.pow(this.value, floatExpression.value));
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(Math.pow(this.value, integerExpression.value));
        }

        throw new TypeError("Unsupported operator '^' for types '" + this.getType(program) + "' and '" + other.getType(program) + "'");
    }

    @Override
    public ExpressionNode mod(ScriptNode program, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value % floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new IntegerExpressionNode(this.value % integerExpression.value);
        }

        throw new TypeError("Unsupported operator '%' for types '" + this.getType(program) + "' and '" + other.getType(program) + "'");
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

        IntegerExpressionNode otherIntegerExpression = (IntegerExpressionNode) otherObject;

        return value.equals(otherIntegerExpression.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
