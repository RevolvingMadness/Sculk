package com.revolvingmadness.testing.language.builtins.classes;

public class ClassInstanceExpressionNode extends BaseClassExpressionNode {
    public final ClassExpressionNode clazz;

    public ClassInstanceExpressionNode(ClassExpressionNode clazz) {
        this.clazz = clazz;
    }

    @Override
    public String getType() {
        return this.clazz.getType();
    }
}
