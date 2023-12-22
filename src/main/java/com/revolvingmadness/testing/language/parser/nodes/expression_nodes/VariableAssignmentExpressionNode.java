package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

public class VariableAssignmentExpressionNode extends ExpressionNode {
    public final ExpressionNode expression;
    public final ExpressionNode value;

    public VariableAssignmentExpressionNode(ExpressionNode expression, ExpressionNode value) {
        this.expression = expression;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;

        VariableAssignmentExpressionNode that = (VariableAssignmentExpressionNode) o;

        if (!this.expression.equals(that.expression))
            return false;
        return this.value.equals(that.value);
    }

    @Override
    public int hashCode() {
        int result = this.expression.hashCode();
        result = 31 * result + this.value.hashCode();
        return result;
    }
}
