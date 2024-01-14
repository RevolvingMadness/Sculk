package com.revolvingmadness.sculk.language.parser.nodes.expression_nodes;

import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.Objects;

public class BinaryExpressionNode extends ExpressionNode {
    public final ExpressionNode left;
    public final TokenType operator;
    public final ExpressionNode right;

    public BinaryExpressionNode(ExpressionNode left, TokenType operator, ExpressionNode right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        BinaryExpressionNode that = (BinaryExpressionNode) o;
        return Objects.equals(this.left, that.left) && this.operator == that.operator && Objects.equals(this.right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.left, this.operator, this.right);
    }
}
