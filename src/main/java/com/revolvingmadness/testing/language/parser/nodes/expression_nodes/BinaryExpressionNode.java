package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;

        BinaryExpressionNode that = (BinaryExpressionNode) o;

        if (!this.left.equals(that.left))
            return false;
        if (this.operator != that.operator)
            return false;
        return this.right.equals(that.right);
    }

    @Override
    public int hashCode() {
        int result = this.left.hashCode();
        result = 31 * result + this.operator.hashCode();
        result = 31 * result + this.right.hashCode();
        return result;
    }
}
