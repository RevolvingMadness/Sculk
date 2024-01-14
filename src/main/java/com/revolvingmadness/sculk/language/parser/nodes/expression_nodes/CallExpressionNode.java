package com.revolvingmadness.sculk.language.parser.nodes.expression_nodes;

import java.util.List;
import java.util.Objects;

public class CallExpressionNode extends ExpressionNode {
    public final List<ExpressionNode> arguments;
    public final ExpressionNode callee;

    public CallExpressionNode(ExpressionNode callee, List<ExpressionNode> arguments) {
        this.callee = callee;
        this.arguments = arguments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        CallExpressionNode that = (CallExpressionNode) o;
        return Objects.equals(this.arguments, that.arguments) && Objects.equals(this.callee, that.callee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.arguments, this.callee);
    }
}
