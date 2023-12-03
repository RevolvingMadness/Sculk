package com.revolvingmadness.testing.language.parser.nodes;

import com.revolvingmadness.testing.language.lexer.TokenType;

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
    public ExpressionNode interpret(ScriptNode script) {
        ExpressionNode interpretedRightValue = this.right.interpret(script);

        return switch (this.operator) {
            case PLUS -> this.left.add(script, interpretedRightValue);
            case DASH -> this.left.subtract(script, interpretedRightValue);
            case STAR -> this.left.multiply(script, interpretedRightValue);
            case FSLASH -> this.left.divide(script, interpretedRightValue);
            case CARET -> this.left.exponentiate(script, interpretedRightValue);
            case PERCENT -> this.left.mod(script, interpretedRightValue);
            default -> throw new RuntimeException("Unknown binary operator '" + this.operator + "'");
        };
    }

    @Override
    public IdentifierExpressionNode getType(ScriptNode script) {
        return this.interpret(script).getType(script);
    }

    @Override
    public ExpressionNode add(ScriptNode script, ExpressionNode other) {
        return this.interpret(script).add(script, other);
    }

    @Override
    public ExpressionNode subtract(ScriptNode script, ExpressionNode other) {
        return this.interpret(script).subtract(script, other);
    }

    @Override
    public ExpressionNode multiply(ScriptNode script, ExpressionNode other) {
        return this.interpret(script).multiply(script, other);
    }

    @Override
    public ExpressionNode divide(ScriptNode script, ExpressionNode other) {
        return this.interpret(script).divide(script, other);
    }

    @Override
    public ExpressionNode exponentiate(ScriptNode script, ExpressionNode other) {
        return this.interpret(script).exponentiate(script, other);
    }

    @Override
    public ExpressionNode mod(ScriptNode script, ExpressionNode other) {
        return this.interpret(script).mod(script, other);
    }

    @Override
    public String toString() {
        return this.left.toString() + ' ' + operator + ' ' + this.right.toString();
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;

        BinaryExpressionNode otherBinaryExpression = (BinaryExpressionNode) otherObject;

        if (!left.equals(otherBinaryExpression.left))
            return false;
        if (operator != otherBinaryExpression.operator)
            return false;
        return right.equals(otherBinaryExpression.right);
    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + operator.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }
}
