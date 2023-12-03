package com.revolvingmadness.testing.language.parser.nodes;

public class FloatExpressionNode extends ExpressionNode {
    public Double value;

    public FloatExpressionNode(Double value) {
        this.value = value;
    }
}
