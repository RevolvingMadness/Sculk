package com.revolvingmadness.testing.language.parser.nodes;

import com.revolvingmadness.testing.language.lexer.TokenType;

public class BinaryExpressionNode extends ExpressionNode {
    public final ExpressionNode left;
    public final TokenType operator;
    public final ExpressionNode right;

    public BinaryExpressionNode(ExpressionNode left, TokenType operator, ExpressionNode right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
}
