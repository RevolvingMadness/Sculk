package com.revolvingmadness.sculk.language.interpreter.errors;

import com.revolvingmadness.sculk.language.errors.Error;
import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.ExpressionNode;

public class Yield extends Error {
    public final ExpressionNode expression;

    public Yield(ExpressionNode expression) {
        super("Unexpected yield");

        this.expression = expression;
    }
}
