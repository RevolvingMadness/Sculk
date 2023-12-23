package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

import java.util.Objects;

public class GetExpressionNode extends ExpressionNode {
    public final ExpressionNode expression;
    public final IdentifierExpressionNode propertyName;

    public GetExpressionNode(ExpressionNode expression, IdentifierExpressionNode propertyName) {
        this.expression = expression;
        this.propertyName = propertyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        GetExpressionNode that = (GetExpressionNode) o;
        return Objects.equals(this.expression, that.expression) && Objects.equals(this.propertyName, that.propertyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.expression, this.propertyName);
    }
}
