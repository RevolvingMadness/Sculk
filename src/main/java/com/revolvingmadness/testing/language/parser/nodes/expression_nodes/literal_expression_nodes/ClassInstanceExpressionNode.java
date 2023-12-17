package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;

import java.util.Optional;

public class ClassInstanceExpressionNode implements LiteralExpressionNode {
    public final IdentifierExpressionNode name;
    public final ClassExpressionNode superClass;
    public final VariableScope variableScope;

    public ClassInstanceExpressionNode(IdentifierExpressionNode name, ClassExpressionNode superClass, VariableScope variableScope) {
        this.name = name;
        this.superClass = superClass;
        this.variableScope = variableScope;
    }

    @Override
    public BooleanExpressionNode equalTo(LiteralExpressionNode other) {
        if (other instanceof ClassInstanceExpressionNode classInstanceExpression) {
            return new BooleanExpressionNode(this.name.equals(classInstanceExpression.name) && this.superClass.equals(classInstanceExpression.superClass) && this.variableScope.equals(classInstanceExpression.variableScope));
        }

        return new BooleanExpressionNode(false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ClassInstanceExpressionNode that = (ClassInstanceExpressionNode) o;

        if (!name.equals(that.name))
            return false;
        if (!superClass.equals(that.superClass))
            return false;
        return variableScope.equals(that.variableScope);
    }

    @Override
    public Variable getProperty(IdentifierExpressionNode propertyName) {
        Optional<Variable> optionalVariable = this.variableScope.getOptional(propertyName);

        if (optionalVariable.isEmpty()) {
            if (this.superClass == null) {
                throw new NameError("Class '" + this.name + "' has no property '" + propertyName + "'");
            }

            Variable variable = this.superClass.getProperty(propertyName);

            if (variable.value instanceof FunctionExpressionNode method) {
                method.bind(this, this.superClass);
            }

            return variable;
        }

        Variable variable = optionalVariable.get();

        if (variable.value instanceof FunctionExpressionNode method) {
            method.bind(this, this.superClass);
        }

        return variable;
    }

    @Override
    public IdentifierExpressionNode getType() {
        return this.name;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + superClass.hashCode();
        result = 31 * result + variableScope.hashCode();
        return result;
    }

    @Override
    public BooleanExpressionNode notEqualTo(LiteralExpressionNode other) {
        if (other instanceof ClassInstanceExpressionNode classInstanceExpression) {
            return new BooleanExpressionNode(!this.name.equals(classInstanceExpression.name) || !this.superClass.equals(classInstanceExpression.superClass) || !this.variableScope.equals(classInstanceExpression.variableScope));
        }

        return new BooleanExpressionNode(true);
    }

    @Override
    public String toString() {
        return "<instance of " + this.name + ">";
    }

    @Override
    public StringExpressionNode toStringType() {
        return new StringExpressionNode("<instance of " + this.name + ">");
    }
}
