package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;

public class ClassInstanceExpressionNode implements LiteralExpressionNode {
    public final ClassExpressionNode clazz;

    public ClassInstanceExpressionNode(ClassExpressionNode clazz) {
        this.clazz = clazz;
    }

    @Override
    public BooleanExpressionNode equalTo(LiteralExpressionNode other) {
        if (other instanceof ClassInstanceExpressionNode classInstance) {
            return new BooleanExpressionNode(this.clazz.equals(classInstance.clazz));
        }

        return new BooleanExpressionNode(false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;

        ClassInstanceExpressionNode that = (ClassInstanceExpressionNode) o;

        return this.clazz.equals(that.clazz);
    }

    @Override
    public Variable getProperty(IdentifierExpressionNode propertyName) {
        Variable variable = this.clazz.getProperty(propertyName);

        if (variable.value instanceof FunctionExpressionNode method) {
            method.bind(this, this.clazz.superClass);
        }

        return variable;
    }

    @Override
    public IdentifierExpressionNode getType() {
        return this.clazz.name;
    }

    @Override
    public int hashCode() {
        return this.clazz.hashCode();
    }

    @Override
    public BooleanExpressionNode notEqualTo(LiteralExpressionNode other) {
        if (other instanceof ClassInstanceExpressionNode classInstance) {
            return new BooleanExpressionNode(!this.clazz.equals(classInstance.clazz));
        }

        return new BooleanExpressionNode(true);
    }

    @Override
    public String toString() {
        return "<instance of " + this.clazz.name + ">";
    }

    @Override
    public StringExpressionNode toStringType() {
        return new StringExpressionNode("<instance of " + this.clazz.name + ">");
    }
}
