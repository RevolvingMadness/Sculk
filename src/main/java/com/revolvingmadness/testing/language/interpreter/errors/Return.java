package com.revolvingmadness.testing.language.interpreter.errors;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

public class Return extends RuntimeException {
    public final LiteralExpressionNode value;

    public Return(LiteralExpressionNode value) {
        super("Unexpected 'return' statement");
        this.value = value;
    }
}
