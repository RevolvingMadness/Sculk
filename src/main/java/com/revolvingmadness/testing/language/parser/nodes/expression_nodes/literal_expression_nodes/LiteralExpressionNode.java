package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.errors.ValueError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;

import java.util.List;

public interface LiteralExpressionNode extends ExpressionNode {
    default LiteralExpressionNode abs() {
        throw new TypeError("Cannot get absolute value of type '" + this.getType() + "'");
    }

    default LiteralExpressionNode add(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '+' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    default LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
        throw new TypeError("Type '" + this.getType() + "' is not callable");
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

    IdentifierExpressionNode getType();

    default BooleanExpressionNode greaterThan(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '>' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    default BooleanExpressionNode greaterThanOrEqualTo(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '>=' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    default LiteralExpressionNode interpret(ScriptNode script) {
        return this;
    }

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

    default BooleanExpressionNode toBooleanType() {
        throw new TypeError("Cannot convert type '" + this.getType() + "' to boolean");
    }

    default LiteralExpressionNode toFloatType() {
        throw new TypeError("Cannot convert type '" + this.getType() + "' to float");
    }

    default LiteralExpressionNode toIntType() {
        throw new TypeError("Cannot convert type '" + this.getType() + "' to int");
    }

    default StringExpressionNode toStringType() {
        throw new TypeError("Cannot convert type '" + this.getType() + "' to string");
    }

    default Variable getProperty(IdentifierExpressionNode propertyName) {
        throw new ValueError("Type '" + this.getType() + "' has no properties");
    }
}
