package com.revolvingmadness.testing.language.interpreter.errors;


import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;

public class Return extends RuntimeException {
    public final BaseClassExpressionNode value;

    public Return(BaseClassExpressionNode value) {
        super("Unexpected 'return' statement");
        this.value = value;
    }
}
