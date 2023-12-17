package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

import com.revolvingmadness.testing.language.interpreter.errors.InterpreterError;
import com.revolvingmadness.testing.language.lexer.TokenType;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.BooleanExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

public class BinaryExpressionNode implements ExpressionNode {
    public final ExpressionNode left;
    public final TokenType operator;
    public final ExpressionNode right;

    public BinaryExpressionNode(ExpressionNode left, TokenType operator, ExpressionNode right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || this.getClass() != otherObject.getClass())
            return false;

        BinaryExpressionNode otherBinaryExpression = (BinaryExpressionNode) otherObject;

        if (!this.left.equals(otherBinaryExpression.left))
            return false;
        if (this.operator != otherBinaryExpression.operator)
            return false;
        return this.right.equals(otherBinaryExpression.right);
    }

    @Override
    public int hashCode() {
        int result = this.left.hashCode();
        result = 31 * result + this.operator.hashCode();
        result = 31 * result + this.right.hashCode();
        return result;
    }

    @Override
    public LiteralExpressionNode interpret(ScriptNode script) {
        LiteralExpressionNode interpretedLeftValue = this.left.interpret(script);
        LiteralExpressionNode interpretedRightValue = this.right.interpret(script);

        return switch (this.operator) {
            case PLUS, DOUBLE_PLUS -> interpretedLeftValue.add(interpretedRightValue);
            case HYPHEN, DOUBLE_HYPHEN -> interpretedLeftValue.subtract(interpretedRightValue);
            case STAR -> interpretedLeftValue.multiply(interpretedRightValue);
            case FSLASH -> interpretedLeftValue.divide(interpretedRightValue);
            case CARET -> interpretedLeftValue.exponentiate(interpretedRightValue);
            case PERCENT -> interpretedLeftValue.mod(interpretedRightValue);
            case EQUAL_TO -> interpretedLeftValue.equalTo(interpretedRightValue);
            case NOT_EQUAL_TO -> interpretedLeftValue.notEqualTo(interpretedRightValue);
            case GREATER_THAN -> interpretedLeftValue.greaterThan(interpretedRightValue);
            case GREATER_THAN_OR_EQUAL_TO -> interpretedLeftValue.greaterThanOrEqualTo(interpretedRightValue);
            case LESS_THAN -> interpretedLeftValue.lessThan(interpretedRightValue);
            case LESS_THAN_OR_EQUAL_TO -> interpretedLeftValue.lessThanOrEqualTo(interpretedRightValue);
            case DOUBLE_AMPERSAND ->
                    new BooleanExpressionNode(interpretedLeftValue.toBooleanType().value && interpretedRightValue.toBooleanType().value);
            case DOUBLE_PIPE ->
                    new BooleanExpressionNode(interpretedLeftValue.toBooleanType().value || interpretedRightValue.toBooleanType().value);
            default -> throw new InterpreterError("Unknown binary operator '" + this.operator + "'");
        };
    }
}
