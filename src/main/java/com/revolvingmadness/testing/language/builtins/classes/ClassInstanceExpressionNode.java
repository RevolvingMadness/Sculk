package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

public class ClassInstanceExpressionNode extends BaseClassExpressionNode {
    public final ClassExpressionNode clazz;

    public ClassInstanceExpressionNode(ClassExpressionNode clazz) {
        this.clazz = clazz;
    }

    @Override
    public BaseClassExpressionNode getProperty(IdentifierExpressionNode propertyName) {
        return this.clazz.getProperty(propertyName);
    }

    @Override
    public String getType() {
        return this.clazz.getType();
    }
}
