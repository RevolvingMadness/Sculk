package com.revolvingmadness.testing.language.user_defined;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import org.apache.commons.lang3.SerializationUtils;

import java.util.Objects;

public class UserDefinedClassInstance extends BaseClassExpressionNode {
    public final UserDefinedClass clazz;

    public UserDefinedClassInstance(UserDefinedClass clazz) {
        this.clazz = SerializationUtils.clone(clazz);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDefinedClassInstance that = (UserDefinedClassInstance) o;
        return Objects.equals(this.clazz, that.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.clazz);
    }

    @Override
    public String getType() {
        return this.clazz.getType();
    }

    @Override
    public BaseClassExpressionNode getProperty(IdentifierExpressionNode propertyName) {
        return this.clazz.getProperty(propertyName);
    }

    @Override
    public void setProperty(IdentifierExpressionNode propertyName, BaseClassExpressionNode value) {
        this.clazz.setProperty(propertyName, value);
    }
}
