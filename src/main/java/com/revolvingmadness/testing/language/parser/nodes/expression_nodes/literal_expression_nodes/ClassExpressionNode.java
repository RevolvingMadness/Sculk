package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;

import java.util.List;

public class ClassExpressionNode implements LiteralExpressionNode {
    public final IdentifierExpressionNode name;
    public final VariableScope variableScope;

    public ClassExpressionNode(IdentifierExpressionNode name, VariableScope variableScope) {
        this.name = name;
        this.variableScope = variableScope;
    }

    @Override
    public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
        return new ClassInstanceExpressionNode(this.name, this.variableScope);
    }

    @Override
    public BooleanExpressionNode equalTo(LiteralExpressionNode other) {
        if (other instanceof ClassExpressionNode functionExpression) {
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

        ClassExpressionNode that = (ClassExpressionNode) o;

        if (!name.equals(that.name))
            return false;
        return variableScope.equals(that.variableScope);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("class");
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + variableScope.hashCode();
        return result;
    }

    @Override
    public BooleanExpressionNode notEqualTo(LiteralExpressionNode other) {
        if (other instanceof ClassExpressionNode functionExpression) {
            return new BooleanExpressionNode(!this.name.equals(functionExpression.name) || !this.variableScope.equals(functionExpression.variableScope));
        }

        return new BooleanExpressionNode(true);
    }

    @Override
    public String toString() {
        return "<class " + this.name + ">";
    }

    @Override
    public StringExpressionNode toStringType() {
        return new StringExpressionNode("<class " + this.name + ">");
    }
}
