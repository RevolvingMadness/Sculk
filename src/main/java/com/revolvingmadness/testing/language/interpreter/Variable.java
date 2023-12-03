package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.parser.nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.IdentifierExpressionNode;

public class Variable {
    public final IdentifierExpressionNode type;
    public final IdentifierExpressionNode name;
    public ExpressionNode value;

    public Variable(IdentifierExpressionNode type, IdentifierExpressionNode name, ExpressionNode value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }
}
