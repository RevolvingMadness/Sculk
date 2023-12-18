package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;

public class NullExpressionNode implements LiteralExpressionNode {
    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("null");
    }

    @Override
    public String toString() {
        return "null";
    }
}
