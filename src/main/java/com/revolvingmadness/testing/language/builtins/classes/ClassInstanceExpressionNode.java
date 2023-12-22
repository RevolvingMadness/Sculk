package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.FunctionClass;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

public class ClassInstanceExpressionNode extends BaseClassExpressionNode {
    public final ClassExpressionNode clazz;

    public ClassInstanceExpressionNode(ClassExpressionNode clazz) {
        super(clazz.superClass, clazz.variableScope);
        this.clazz = clazz;
    }

    @Override
    public BaseClassExpressionNode getProperty(IdentifierExpressionNode propertyName) {
        BaseClassExpressionNode property = this.clazz.getProperty(propertyName);

        if (property instanceof FunctionClass method) {
            method.bind(this, this.superClass);
        }

        return property;
    }

    @Override
    public String getType() {
        return this.clazz.getType();
    }
}
