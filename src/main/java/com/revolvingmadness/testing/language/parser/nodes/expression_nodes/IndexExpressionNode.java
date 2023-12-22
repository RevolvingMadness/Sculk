package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

public class IndexExpressionNode extends ExpressionNode {
    public final ExpressionNode expression;
    public final ExpressionNode index;

    public IndexExpressionNode(ExpressionNode expression, ExpressionNode index) {
        this.expression = expression;
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;

        IndexExpressionNode that = (IndexExpressionNode) o;

        if (!this.expression.equals(that.expression))
            return false;
        return this.index.equals(that.index);
    }

    @Override
    public int hashCode() {
        int result = this.expression.hashCode();
        result = 31 * result + this.index.hashCode();
        return result;
    }
}
