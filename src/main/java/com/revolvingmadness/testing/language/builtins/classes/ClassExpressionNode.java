package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.FunctionClass;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

import java.util.List;

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
        BaseClassExpressionNode property = super.getProperty(propertyName);

        if (property instanceof FunctionClass method) {
            method.bind(this, this.superClass);
        }

        return property;
    }

    @Override
    public String getType() {
        return this.name.value;
    }
}