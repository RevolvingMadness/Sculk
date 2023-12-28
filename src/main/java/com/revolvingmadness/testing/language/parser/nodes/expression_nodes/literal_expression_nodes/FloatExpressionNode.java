package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import java.util.Objects;

public class FloatExpressionNode extends LiteralExpressionNode {
    public final Double value;

    public FloatExpressionNode(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        FloatExpressionNode that = (FloatExpressionNode) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }
}
