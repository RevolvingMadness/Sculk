package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

import java.util.List;

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

        if (!this.arguments.equals(that.arguments))
            return false;
        return this.callee.equals(that.callee);
    }

    @Override
    public int hashCode() {
        int result = this.arguments.hashCode();
        result = 31 * result + this.callee.hashCode();
        return result;
    }
}
