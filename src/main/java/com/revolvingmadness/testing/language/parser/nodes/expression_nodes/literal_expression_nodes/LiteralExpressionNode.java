package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

public interface LiteralExpressionNode extends ExpressionNode {
    default LiteralExpressionNode add(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '+' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    default LiteralExpressionNode divide(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '/' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    default BooleanExpressionNode equalTo(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '==' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    default LiteralExpressionNode exponentiate(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '^' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    default BooleanExpressionNode greaterThan(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '>' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    IdentifierExpressionNode getType();

    default BooleanExpressionNode greaterThanOrEqualTo(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '>=' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    default LiteralExpressionNode interpret(ScriptNode script) {
        return this;
    }

    boolean isTruthy();

    default BooleanExpressionNode lessThan(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '<' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    default BooleanExpressionNode lessThanOrEqualTo(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '<=' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    default LiteralExpressionNode logicalNot() {
        throw new TypeError("Unsupported unary operator '!' for type '" + this.getType() + "'");
    }

    default LiteralExpressionNode mod(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '%' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    default LiteralExpressionNode multiply(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '*' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    default LiteralExpressionNode negate() {
        throw new TypeError("Unsupported unary operator '-' for type '" + this.getType() + "'");
    }

    default BooleanExpressionNode notEqualTo(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '!=' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    default LiteralExpressionNode subtract(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '-' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }
}
