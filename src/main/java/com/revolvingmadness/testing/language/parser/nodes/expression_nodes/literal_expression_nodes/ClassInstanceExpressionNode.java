package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;

public class ClassInstanceExpressionNode implements LiteralExpressionNode {
    public final IdentifierExpressionNode name;
    public final VariableScope variableScope;

    public ClassInstanceExpressionNode(IdentifierExpressionNode name, VariableScope variableScope) {
        this.name = name;
        this.variableScope = variableScope;
    }

    @Override
    public BooleanExpressionNode equalTo(LiteralExpressionNode other) {
        if (other instanceof ClassInstanceExpressionNode functionExpression) {
            return new BooleanExpressionNode(this.name.equals(functionExpression.name) && this.variableScope.equals(functionExpression.variableScope));
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
        return variableScope.equals(that.variableScope);
    }

    @Override
    public Variable getProperty(IdentifierExpressionNode propertyName) {
        Variable variable = this.variableScope.getOrThrow(propertyName);

        if (variable.value instanceof FunctionExpressionNode method) {
            method.bind(this);
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
        result = 31 * result + variableScope.hashCode();
        return result;
    }

    @Override
    public BooleanExpressionNode notEqualTo(LiteralExpressionNode other) {
        if (other instanceof ClassInstanceExpressionNode functionExpression) {
            return new BooleanExpressionNode(!this.name.equals(functionExpression.name) || !this.variableScope.equals(functionExpression.variableScope));
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
