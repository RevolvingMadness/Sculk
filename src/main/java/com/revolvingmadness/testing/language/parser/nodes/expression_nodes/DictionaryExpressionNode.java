package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

import java.util.Map;
import java.util.Objects;

public class DictionaryExpressionNode extends ExpressionNode {
    public final Map<ExpressionNode, ExpressionNode> value;

    public DictionaryExpressionNode(Map<ExpressionNode, ExpressionNode> value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        DictionaryExpressionNode that = (DictionaryExpressionNode) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }
}
