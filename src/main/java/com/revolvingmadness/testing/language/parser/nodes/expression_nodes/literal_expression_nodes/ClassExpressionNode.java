package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;

import java.util.List;
import java.util.Optional;

public class ClassExpressionNode implements LiteralExpressionNode {
    public final IdentifierExpressionNode name;
    public final ClassExpressionNode superClass;
    public final VariableScope variableScope;

    public ClassExpressionNode(IdentifierExpressionNode name, ClassExpressionNode superClass, VariableScope variableScope) {
        this.name = name;
        this.superClass = superClass;
        this.variableScope = variableScope;
    }

    @Override
    public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
        return new ClassInstanceExpressionNode(this);
    }

    @Override
    public BooleanExpressionNode equalTo(LiteralExpressionNode other) {
        if (other instanceof ClassExpressionNode classExpression) {
            return new BooleanExpressionNode(this.name.equals(classExpression.name) && this.superClass.equals(classExpression.superClass) && this.variableScope.equals(classExpression.variableScope));
        }

        return new BooleanExpressionNode(false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;

        ClassExpressionNode that = (ClassExpressionNode) o;

        if (!this.name.equals(that.name))
            return false;
        if (!this.superClass.equals(that.superClass))
            return false;
        return this.variableScope.equals(that.variableScope);
    }

    @Override
    public Variable getProperty(IdentifierExpressionNode propertyName) {
        Optional<Variable> variable = this.variableScope.getOptional(propertyName);

        if (variable.isEmpty()) {
            if (this.superClass == null) {
                throw new NameError("Class '" + this.name + "' has no property '" + propertyName + "'");
            }

            return this.superClass.getProperty(propertyName);
        }

        return variable.get();
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("class");
    }

    @Override
    public int hashCode() {
        int result = this.name.hashCode();
        result = 31 * result + this.superClass.hashCode();
        result = 31 * result + this.variableScope.hashCode();
        return result;
    }

    @Override
    public BooleanExpressionNode notEqualTo(LiteralExpressionNode other) {
        if (other instanceof ClassExpressionNode classExpression) {
            return new BooleanExpressionNode(!this.name.equals(classExpression.name) || !this.superClass.equals(classExpression.superClass) || !this.variableScope.equals(classExpression.variableScope));
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
