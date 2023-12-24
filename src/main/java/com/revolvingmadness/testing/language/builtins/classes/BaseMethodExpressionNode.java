package com.revolvingmadness.testing.language.builtins.classes;

public abstract class BaseMethodExpressionNode extends BaseClassExpressionNode {
    public BaseClassExpressionNode boundClass;
    public BaseClassExpressionNode boundSuperClass;

    public BaseMethodExpressionNode() {
        super(new ObjectClass());
    }

    public BaseMethodExpressionNode(BaseClassExpressionNode superClass) {
        super(superClass);
    }

    public void bind(BaseClassExpressionNode clazz, BaseClassExpressionNode superClass) {
        this.boundClass = clazz;
        this.boundSuperClass = superClass;
    }

    @Override
    public String getType() {
        return "Method";
    }
}
