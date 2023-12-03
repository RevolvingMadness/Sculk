package com.revolvingmadness.testing.language.parser.nodes;

public class FloatExpressionNode implements NumberExpressionNode {
    public Double value;

    public FloatExpressionNode(Double value) {
        this.value = value;
    }

    @Override
    public ExpressionNode interpret(ScriptNode program) {
        return this;
    }

    @Override
    public IdentifierExpressionNode getType(ScriptNode program) {
        return new IdentifierExpressionNode("float");
    }

    @Override
    public ExpressionNode add(ScriptNode program, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value + floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(this.value + integerExpression.value);
        }

        throw new RuntimeException("Unsupported operator '+' for types '" + this.getType(program) + "' and '" + other.getType(program) + "'");
    }

    @Override
    public ExpressionNode subtract(ScriptNode program, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value - floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(this.value - integerExpression.value);
        }
        throw new RuntimeException("Unsupported operator '-' for types '" + this.getType(program) + "' and '" + other.getType(program) + "'");
    }

    @Override
    public ExpressionNode multiply(ScriptNode program, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value * floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(this.value * integerExpression.value);
        }
        throw new RuntimeException("Unsupported operator '*' for types '" + this.getType(program) + "' and '" + other.getType(program) + "'");
    }

    @Override
    public ExpressionNode divide(ScriptNode program, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value / floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(this.value / integerExpression.value);
        }
        throw new RuntimeException("Unsupported operator '/' for types '" + this.getType(program) + "' and '" + other.getType(program) + "'");
    }

    @Override
    public ExpressionNode exponentiate(ScriptNode program, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(Math.pow(this.value, floatExpression.value));
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(Math.pow(this.value, integerExpression.value));
        }
        throw new RuntimeException("Unsupported operator '^' for types '" + this.getType(program) + "' and '" + other.getType(program) + "'");
    }

    @Override
    public ExpressionNode mod(ScriptNode program, ExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value % floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(this.value % integerExpression.value);
        }
        throw new RuntimeException("Unsupported operator '%' for types '" + this.getType(program) + "' and '" + other.getType(program) + "'");
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
