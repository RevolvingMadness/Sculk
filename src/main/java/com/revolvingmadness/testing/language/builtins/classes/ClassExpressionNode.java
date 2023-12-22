package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

import java.util.List;
import java.util.Optional;

public class ClassExpressionNode extends BaseClassExpressionNode {
    public final IdentifierExpressionNode name;
    public final BaseClassExpressionNode superClass;
    public final VariableScope variableScope;

    public ClassExpressionNode(IdentifierExpressionNode name, BaseClassExpressionNode superClass, VariableScope variableScope) {
        this.name = name;
        this.superClass = superClass;
        this.variableScope = variableScope;
    }

    @Override
    public BaseClassExpressionNode call(Interpreter interpreter, List<ExpressionNode> arguments) {
        return new ClassInstanceExpressionNode(this);
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
    public String getType() {
        return this.name.value;
    }
}