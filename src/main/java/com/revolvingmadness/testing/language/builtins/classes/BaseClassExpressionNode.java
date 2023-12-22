package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

import java.util.List;
import java.util.Optional;

public abstract class BaseClassExpressionNode implements ExpressionNode {
    public final BaseClassExpressionNode superClass;
    public final VariableScope variableScope;

    public BaseClassExpressionNode() {
        this(new ObjectClass());
    }

    public BaseClassExpressionNode(BaseClassExpressionNode superClass) {
        this.superClass = superClass;
        this.variableScope = new VariableScope();
    }

    public BaseClassExpressionNode call(Interpreter interpreter, List<ExpressionNode> arguments) {
        throw new TypeError("Type '" + this.getType() + "' is not callable");
    }

    public BaseClassExpressionNode call(Interpreter interpreter, IdentifierExpressionNode methodName, List<ExpressionNode> arguments) {
        BaseClassExpressionNode method = this.getProperty(methodName);

        return method.call(interpreter, arguments);
    }

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

    public abstract String getType();
}