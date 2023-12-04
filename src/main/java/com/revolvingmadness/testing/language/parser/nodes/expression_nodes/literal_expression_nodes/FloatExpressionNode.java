package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

public class FloatExpressionNode implements NumberExpressionNode {
    public final Double value;

    public FloatExpressionNode(Double value) {
        this.value = value;
    }

    @Override
    public LiteralExpressionNode add(LiteralExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value + floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(this.value + integerExpression.value);
        }

        throw new TypeError("Unsupported binary operator '+' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode divide(LiteralExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value / floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(this.value / integerExpression.value);
        }
        throw new TypeError("Unsupported binary operator '/' for types '" + this.getType() + "' and '" + other.getType() + "'");
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
        return new IdentifierExpressionNode("float");
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public LiteralExpressionNode interpret(ScriptNode script) {
        return this;
    }

    @Override
    public boolean isTruthy() {
        return this.value != 0.0;
    }

    @Override
    public LiteralExpressionNode equalTo(LiteralExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new BooleanExpressionNode(this.value.equals(floatExpression.value));
        }

        return new BooleanExpressionNode(false);
    }

    @Override
    public LiteralExpressionNode notEqualTo(LiteralExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new BooleanExpressionNode(!this.value.equals(floatExpression.value));
        }

        return new BooleanExpressionNode(true);
    }

    @Override
    public LiteralExpressionNode greaterThan(LiteralExpressionNode other) {
        if (other instanceof IntegerExpressionNode integerExpression) {
            return new BooleanExpressionNode(this.value > integerExpression.value);
        } else if (other instanceof FloatExpressionNode floatExpression) {
            return new BooleanExpressionNode(this.value > floatExpression.value);
        }

        throw new TypeError("Unsupported binary operator '>' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode greaterThanOrEqualTo(LiteralExpressionNode other) {
        if (other instanceof IntegerExpressionNode integerExpression) {
            return new BooleanExpressionNode(this.value >= integerExpression.value);
        } else if (other instanceof FloatExpressionNode floatExpression) {
            return new BooleanExpressionNode(this.value >= floatExpression.value);
        }

        throw new TypeError("Unsupported binary operator '>=' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode lessThan(LiteralExpressionNode other) {
        if (other instanceof IntegerExpressionNode integerExpression) {
            return new BooleanExpressionNode(this.value < integerExpression.value);
        } else if (other instanceof FloatExpressionNode floatExpression) {
            return new BooleanExpressionNode(this.value < floatExpression.value);
        }

        throw new TypeError("Unsupported binary operator '<' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode lessThanOrEqualTo(LiteralExpressionNode other) {
        if (other instanceof IntegerExpressionNode integerExpression) {
            return new BooleanExpressionNode(this.value <= integerExpression.value);
        } else if (other instanceof FloatExpressionNode floatExpression) {
            return new BooleanExpressionNode(this.value <= floatExpression.value);
        }

        throw new TypeError("Unsupported binary operator '<=' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode logicalNot() {
        throw new TypeError("Unsupported unary operator '!' for type '" + this.getType() + "'");
    }

    @Override
    public LiteralExpressionNode mod(LiteralExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value % floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(this.value % integerExpression.value);
        }
        throw new TypeError("Unsupported binary operator '%' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode multiply(LiteralExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value * floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(this.value * integerExpression.value);
        }
        throw new TypeError("Unsupported binary operator '*' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode negate() {
        return new FloatExpressionNode(-this.value);
    }

    @Override
    public LiteralExpressionNode subtract(LiteralExpressionNode other) {
        if (other instanceof FloatExpressionNode floatExpression) {
            return new FloatExpressionNode(this.value - floatExpression.value);
        } else if (other instanceof IntegerExpressionNode integerExpression) {
            return new FloatExpressionNode(this.value - integerExpression.value);
        }
        throw new TypeError("Unsupported binary operator '-' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}