package com.revolvingmadness.testing.language.user_defined;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.VariableScope;

import java.util.List;
import java.util.Objects;

public class UserDefinedClass extends BaseClassExpressionNode {
    public final String name;

    public UserDefinedClass(String name, BaseClassExpressionNode superClass, VariableScope variableScope) {
        super(superClass, variableScope);
        this.name = name;
    }

    @Override
    public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
        return new UserDefinedClassInstance(this, interpreter, arguments);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        UserDefinedClass that = (UserDefinedClass) o;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public String getType() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.name);
    }
}