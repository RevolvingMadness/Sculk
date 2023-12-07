package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

public class IntegerExpressionNode implements LiteralExpressionNode {
    public final Integer value;

    public IntegerExpressionNode(Integer value) {
        this.value = value;
    }

    @Override
    public LiteralExpressionNode abs() {
        return new IntegerExpressionNode(this.value);
    }

    @Override
    public LiteralExpressionNode add(LiteralExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value + floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new IntegerExpressionNode(this.value + integerExpression.value);
        }

        throw new TypeError("Unsupported binary operator '+' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode divide(LiteralExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value / floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new IntegerExpressionNode(this.value / integerExpression.value);
        }

        throw new TypeError("Unsupported binary operator '/' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public BooleanExpressionNode equalTo(LiteralExpressionNode other) {
        if (other instanceof IntegerExpressionNode integerExpression) {
            return new BooleanExpressionNode(this.value.equals(integerExpression.value));
        }

        return new BooleanExpressionNode(false);
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
    public LiteralExpressionNode exponentiate(LiteralExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(Math.pow(this.value, floatExpression.value));
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(Math.pow(this.value, integerExpression.value));
        }

        throw new TypeError("Unsupported binary operator '^' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("int");
    }

    @Override
    public BooleanExpressionNode greaterThan(LiteralExpressionNode other) {
        if (other instanceof IntegerExpressionNode integerExpression) {
            return new BooleanExpressionNode(this.value > integerExpression.value);
        } else if (other instanceof FloatExpressionNode floatExpression) {
            return new BooleanExpressionNode(this.value > floatExpression.value);
        }

        throw new TypeError("Unsupported binary operator '>' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public BooleanExpressionNode greaterThanOrEqualTo(LiteralExpressionNode other) {
        if (other instanceof IntegerExpressionNode integerExpression) {
            return new BooleanExpressionNode(this.value >= integerExpression.value);
        } else if (other instanceof FloatExpressionNode floatExpression) {
            return new BooleanExpressionNode(this.value >= floatExpression.value);
        }

        throw new TypeError("Unsupported binary operator '>' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public BooleanExpressionNode lessThan(LiteralExpressionNode other) {
        if (other instanceof IntegerExpressionNode integerExpression) {
            return new BooleanExpressionNode(this.value < integerExpression.value);
        } else if (other instanceof FloatExpressionNode floatExpression) {
            return new BooleanExpressionNode(this.value < floatExpression.value);
        }

        throw new TypeError("Unsupported binary operator '>' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public BooleanExpressionNode lessThanOrEqualTo(LiteralExpressionNode other) {
        if (other instanceof IntegerExpressionNode integerExpression) {
            return new BooleanExpressionNode(this.value <= integerExpression.value);
        } else if (other instanceof FloatExpressionNode floatExpression) {
            return new BooleanExpressionNode(this.value <= floatExpression.value);
        }

        throw new TypeError("Unsupported binary operator '>' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode mod(LiteralExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value % floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new IntegerExpressionNode(this.value % integerExpression.value);
        }

        throw new TypeError("Unsupported binary operator '%' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode multiply(LiteralExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value * floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new IntegerExpressionNode(this.value * integerExpression.value);
        } else if (other instanceof StringExpressionNode stringExpression) {
            return new StringExpressionNode(stringExpression.value.repeat(this.value));
        }

        throw new TypeError("Unsupported binary operator '*' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode negate() {
        return new IntegerExpressionNode(-this.value);
    }

    @Override
    public BooleanExpressionNode notEqualTo(LiteralExpressionNode other) {
        if (other instanceof IntegerExpressionNode integerExpression) {
            return new BooleanExpressionNode(!this.value.equals(integerExpression.value));
        }

        return new BooleanExpressionNode(true);
    }

    @Override
    public LiteralExpressionNode subtract(LiteralExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value - floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new IntegerExpressionNode(this.value - integerExpression.value);
        }

        throw new TypeError("Unsupported binary operator '-' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public BooleanExpressionNode toBoolean() {
        return new BooleanExpressionNode(this.value != 0);
    }

    @Override
    public LiteralExpressionNode toFloatType() {
        return new FloatExpressionNode(this.value.doubleValue());
    }

    @Override
    public LiteralExpressionNode toIntType() {
        return this;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public StringExpressionNode toStringType() {
        return new StringExpressionNode(this.value.toString());
    }
}
