package com.revolvingmadness.testing.language.parser.nodes;

public class IdentifierExpressionNode extends ExpressionNode {
    public final String value;

    public IdentifierExpressionNode(String value) {
        this.value = value;
    }
}
