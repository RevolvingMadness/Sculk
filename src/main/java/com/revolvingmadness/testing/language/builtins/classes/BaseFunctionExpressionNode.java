package com.revolvingmadness.testing.language.builtins.classes;

public abstract class BaseFunctionExpressionNode extends BaseClassExpressionNode {
    public BaseFunctionExpressionNode() {
        super(new ObjectClass());
    }

    public BaseFunctionExpressionNode(BaseClassExpressionNode superClass) {
        super(superClass);
    }

    @Override
    public String getType() {
        return "Function";
    }
}
