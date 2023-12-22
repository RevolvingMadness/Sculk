package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

public class GetExpressionNode extends ExpressionNode {
    public final ExpressionNode expression;
    public final IdentifierExpressionNode propertyName;

    public GetExpressionNode(ExpressionNode expression, IdentifierExpressionNode propertyName) {
        this.expression = expression;
        this.propertyName = propertyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;

        GetExpressionNode that = (GetExpressionNode) o;

        if (!this.expression.equals(that.expression))
            return false;
        return this.propertyName.equals(that.propertyName);
    }

    @Override
    public int hashCode() {
        int result = this.expression.hashCode();
        result = 31 * result + this.propertyName.hashCode();
        return result;
    }
}
