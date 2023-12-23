package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

import java.util.Objects;

public class VariableAssignmentExpressionNode extends ExpressionNode {
    public final ExpressionNode expression;
    public final ExpressionNode value;

    public VariableAssignmentExpressionNode(ExpressionNode expression, ExpressionNode value) {
        this.expression = expression;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        VariableAssignmentExpressionNode that = (VariableAssignmentExpressionNode) o;
        return Objects.equals(this.expression, that.expression) && Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.expression, this.value);
    }
}
