package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

import java.util.Objects;

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
        return Objects.equals(this.expression, that.expression) && Objects.equals(this.index, that.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.expression, this.index);
    }
}
