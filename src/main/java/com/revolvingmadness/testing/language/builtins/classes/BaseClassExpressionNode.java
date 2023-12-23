package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.FunctionClass;
import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class BaseClassExpressionNode extends ExpressionNode {
    public final BaseClassExpressionNode superClass;
    public final VariableScope variableScope;

    public BaseClassExpressionNode() {
        this(new ObjectClass());
    }

    public BaseClassExpressionNode(BaseClassExpressionNode superClass) {
        this(superClass, new VariableScope());
    }

    public BaseClassExpressionNode(BaseClassExpressionNode superClass, VariableScope variableScope) {
        this.superClass = superClass;
        this.variableScope = variableScope;
    }

    public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
        throw new TypeError("Type '" + this.getType() + "' is not callable");
    }

    public BaseClassExpressionNode call(Interpreter interpreter, IdentifierExpressionNode methodName, List<BaseClassExpressionNode> arguments) {
        BaseClassExpressionNode method = this.getProperty(methodName);

        return method.call(interpreter, arguments);
    }

    public BaseClassExpressionNode getProperty(IdentifierExpressionNode propertyName) {
        Optional<Variable> optionalVariable = this.variableScope.getOptional(propertyName);

        if (optionalVariable.isPresent()) {
            BaseClassExpressionNode property = optionalVariable.get().value;

            if (property instanceof FunctionClass method) {
                method.bind(this, this.superClass);
            }

            return property;
        }

        if (this.superClass == null) {
            throw new NameError("Type '" + this.getType() + "' has no property '" + propertyName + "'");
        }

        return this.superClass.getProperty(propertyName);
    }

    public abstract String getType();

    public BaseClassExpressionNode index(BaseClassExpressionNode index) {
        throw new TypeError("Type '" + this.getType() + "' is not indexable");
    }

    public void setProperty(IdentifierExpressionNode propertyName, BaseClassExpressionNode value) {
        Optional<Variable> optionalVariable = this.variableScope.getOptional(propertyName);

        if (optionalVariable.isPresent()) {
            this.variableScope.assign(propertyName, value);

            return;
        }

        if (this.superClass == null) {
            throw new NameError("Type '" + this.getType() + "' has no property '" + propertyName + "'");
        }

        this.superClass.setProperty(propertyName, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        BaseClassExpressionNode that = (BaseClassExpressionNode) o;
        return Objects.equals(this.superClass, that.superClass) && Objects.equals(this.variableScope, that.variableScope);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.superClass, this.variableScope);
    }
}