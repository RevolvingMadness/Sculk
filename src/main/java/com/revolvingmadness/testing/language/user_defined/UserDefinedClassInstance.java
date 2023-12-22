package com.revolvingmadness.testing.language.user_defined;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.types.FunctionClass;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import org.apache.commons.lang3.SerializationUtils;

public class UserDefinedClassInstance extends BaseClassExpressionNode {
    public final UserDefinedClass clazz;

    public UserDefinedClassInstance(UserDefinedClass clazz) {
        this.clazz = SerializationUtils.clone(clazz);
    }

    @Override
    public BaseClassExpressionNode getProperty(IdentifierExpressionNode propertyName) {
        BaseClassExpressionNode property = this.clazz.getProperty(propertyName);

        if (property instanceof FunctionClass method) {
            method.bind(this.clazz, this.clazz.superClass);
        }

        return property;
    }

    @Override
    public String getType() {
        return this.clazz.getType();
    }

    @Override
    public void setProperty(IdentifierExpressionNode propertyName, BaseClassExpressionNode value) {
        this.clazz.setProperty(propertyName, value);
    }
}
