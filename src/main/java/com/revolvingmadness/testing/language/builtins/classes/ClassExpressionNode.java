package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

import java.util.List;

public class ClassExpressionNode extends BaseClassExpressionNode {
    public final IdentifierExpressionNode name;

    public ClassExpressionNode(IdentifierExpressionNode name, BaseClassExpressionNode superClass, VariableScope variableScope) {
        super(superClass, variableScope);
        this.name = name;
    }

    @Override
    public BaseClassExpressionNode call(Interpreter interpreter, List<ExpressionNode> arguments) {
        return new ClassInstanceExpressionNode(this);
    }

    @Override
    public String getType() {
        return this.name.value;
    }
}