package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import java.util.Objects;

public class IntegerExpressionNode extends LiteralExpressionNode {
    public final long value;

    public IntegerExpressionNode(long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        IntegerExpressionNode that = (IntegerExpressionNode) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }
}
