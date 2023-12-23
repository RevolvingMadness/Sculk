package com.revolvingmadness.testing.language.user_defined;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserDefinedClass extends BaseClassExpressionNode {
    public final IdentifierExpressionNode name;

    public UserDefinedClass(IdentifierExpressionNode name, BaseClassExpressionNode superClass, VariableScope variableScope) {
        super(superClass, variableScope);
        this.name = name;
    }

    @Override
    public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
        return new UserDefinedClassInstance(this);
    }

    @Override
    public BaseClassExpressionNode getProperty(IdentifierExpressionNode propertyName) {
        Optional<Variable> optionalVariable = this.variableScope.getOptional(propertyName);

        if (optionalVariable.isPresent()) {
            return optionalVariable.get().value;
        }

        if (this.superClass == null) {
            throw new NameError("Type '" + this.getType() + "' has no property '" + propertyName + "'");
        }

        return this.superClass.getProperty(propertyName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDefinedClass that = (UserDefinedClass) o;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.name);
    }

    @Override
    public String getType() {
        return this.name.value;
    }
}