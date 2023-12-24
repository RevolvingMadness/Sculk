package com.revolvingmadness.testing.language.builtins.classes;

public abstract class BaseFunctionExpressionNode extends BaseClassExpressionNode {
    public BaseClassExpressionNode boundClass;
    public BaseClassExpressionNode boundSuperClass;

    public BaseFunctionExpressionNode() {
        super(new ObjectClass());
    }

    public BaseFunctionExpressionNode(BaseClassExpressionNode superClass) {
        super(superClass);
    }

    public void bind(BaseClassExpressionNode clazz, BaseClassExpressionNode superClass) {
        this.boundClass = clazz;
        this.boundSuperClass = superClass;
    }

    @Override
    public String getType() {
        return "Function";
    }
}
