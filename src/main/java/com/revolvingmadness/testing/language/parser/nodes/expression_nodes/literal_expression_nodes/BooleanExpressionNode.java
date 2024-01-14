package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import java.util.Objects;

public class BooleanExpressionNode extends LiteralExpressionNode {
    public final boolean value;

    public BooleanExpressionNode(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        BooleanExpressionNode that = (BooleanExpressionNode) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }
}
