package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

public class Variable {
    public final IdentifierExpressionNode name;
    public final IdentifierExpressionNode type;
    public LiteralExpressionNode value;

    public Variable(IdentifierExpressionNode type, IdentifierExpressionNode name, LiteralExpressionNode value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }
}
