package com.revolvingmadness.sculk.language.parser.nodes.expression_nodes;

import java.util.Objects;

public class IdentifierExpressionNode extends ExpressionNode {
    public final String value;

    public IdentifierExpressionNode(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        IdentifierExpressionNode that = (IdentifierExpressionNode) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public String toString() {
        return this.value;
    }
}
