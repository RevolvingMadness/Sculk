package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.parser.nodes.expression.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression.LiteralExpressionNode;

public class Variable {
    public final IdentifierExpressionNode type;
    public final IdentifierExpressionNode name;
    public LiteralExpressionNode value;

    public Variable(IdentifierExpressionNode type, IdentifierExpressionNode name, LiteralExpressionNode value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }
}
