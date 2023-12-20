package com.revolvingmadness.testing.language.builtins.classes;

import java.util.Optional;

import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.ClassExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

public abstract class BuiltinClass implements LiteralExpressionNode {
    public final BuiltinClass superClass;
    public final VariableScope variableScope;

    public BuiltinClass() {
        this(new ObjectClass());
    }

    public BuiltinClass(BuiltinClass superClass) {
        this.superClass = superClass;
        this.variableScope = new VariableScope();
    }

    @Override
    public Variable getProperty(IdentifierExpressionNode propertyName) {
        Optional<Variable> optionalVariable = this.variableScope.getOptional(propertyName);

        if (optionalVariable.isPresent()) {
            return optionalVariable.get();
        }

        if (this.superClass == null) {
            throw new NameError("Type '" + this.getType() + "' has no property '" + propertyName + "'");
        }

        return this.superClass.getProperty(propertyName);
    }
}