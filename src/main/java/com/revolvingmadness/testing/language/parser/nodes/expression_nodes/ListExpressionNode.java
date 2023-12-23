package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

import java.util.List;
import java.util.Objects;

public class ListExpressionNode extends ExpressionNode {
    public final List<ExpressionNode> value;

    public ListExpressionNode(List<ExpressionNode> value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        ListExpressionNode that = (ListExpressionNode) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }
}
