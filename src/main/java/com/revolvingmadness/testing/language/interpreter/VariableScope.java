package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.interpreter.errors.ValueError;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VariableScope {
    public final List<Variable> variables;

    public VariableScope() {
        this.variables = new ArrayList<>();
    }

    public void assign(IdentifierExpressionNode name, BaseClassExpressionNode value) {
        Optional<Variable> optionalVariable = this.getOptional(name);

        if (optionalVariable.isEmpty()) {
            throw new NameError("Variable '" + name + "' has not been declared");
        }

        Variable variable = optionalVariable.get();

        if (variable.isConstant) {
            throw new ValueError("Cannot assign value to variable '" + variable.name + "' because it is constant");
        }

        variable.value = value;
    }

    public void declare(boolean isConstant, IdentifierExpressionNode name, BaseClassExpressionNode value) {
        Optional<Variable> optionalVariable = this.getOptional(name);

        if (optionalVariable.isPresent()) {
            throw new ValueError("Variable '" + name + "' has already been declared");
        }

        this.variables.add(new Variable(isConstant, name, value));
    }

    public Optional<Variable> getOptional(IdentifierExpressionNode name) {
        for (Variable variable : this.variables) {
            if (variable.name.equals(name)) {
                return Optional.of(variable);
            }
        }

        return Optional.empty();
    }
}
