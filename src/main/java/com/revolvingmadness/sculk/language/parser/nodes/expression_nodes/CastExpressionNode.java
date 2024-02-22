package com.revolvingmadness.sculk.language.parser.nodes.expression_nodes;

import java.util.Objects;

public class CastExpressionNode extends ExpressionNode {
    public final String type;
    public final String variable;

    public CastExpressionNode(String type, String variable) {
        this.type = type;
        this.variable = variable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        CastExpressionNode that = (CastExpressionNode) o;
        return Objects.equals(this.type, that.type) && Objects.equals(this.variable, that.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type, this.variable);
    }
}
