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
    public ExpressionNode interpret(ScriptNode program) {
        ExpressionNode interpretedRightValue = this.right.interpret(program);

        return switch (this.operator) {
            case PLUS -> this.left.add(program, interpretedRightValue);
            case DASH -> this.left.subtract(program, interpretedRightValue);
            case STAR -> this.left.multiply(program, interpretedRightValue);
            case FSLASH -> this.left.divide(program, interpretedRightValue);
            case CARET -> this.left.exponentiate(program, interpretedRightValue);
            case PERCENT -> this.left.mod(program, interpretedRightValue);
            default -> throw new RuntimeException("Unknown binary operator '" + this.operator + "'");
        };
    }

    @Override
    public IdentifierExpressionNode getType(ScriptNode program) {
        return this.interpret(program).getType(program);
    }

    @Override
    public ExpressionNode add(ScriptNode program, ExpressionNode other) {
        return this.interpret(program).add(program, other);
    }

    @Override
    public ExpressionNode subtract(ScriptNode program, ExpressionNode other) {
        return this.interpret(program).subtract(program, other);
    }

    @Override
    public ExpressionNode multiply(ScriptNode program, ExpressionNode other) {
        return this.interpret(program).multiply(program, other);
    }

    @Override
    public ExpressionNode divide(ScriptNode program, ExpressionNode other) {
        return this.interpret(program).divide(program, other);
    }

    @Override
    public ExpressionNode exponentiate(ScriptNode program, ExpressionNode other) {
        return this.interpret(program).exponentiate(program, other);
    }

    @Override
    public ExpressionNode mod(ScriptNode program, ExpressionNode other) {
        return this.interpret(program).mod(program, other);
    }

    @Override
    public String toString() {
        return this.left.toString() + ' ' + operator + ' ' + this.right.toString();
    }
}
