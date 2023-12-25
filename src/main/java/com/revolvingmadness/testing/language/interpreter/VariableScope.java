package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.interpreter.errors.ValueError;
import com.revolvingmadness.testing.language.lexer.TokenType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VariableScope implements Serializable {
    public final List<Variable> variables;

    public VariableScope() {
        this.variables = new ArrayList<>();
    }

    public void assign(String name, BaseClassExpressionNode value) {
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

    // TODO to be removed and make access modifiers required
    public void declare(boolean isConstant, String name, BaseClassExpressionNode value) {
        this.declare(List.of(), isConstant, name, value);
    }

    public void declare(List<TokenType> accessModifiers, boolean isConstant, String name, BaseClassExpressionNode value) {
        Optional<Variable> optionalVariable = this.getOptional(name);

        if (optionalVariable.isPresent()) {
            throw new ValueError("Variable '" + name + "' has already been declared");
        }

        this.variables.add(new Variable(accessModifiers, isConstant, name, value));
    }

    public Optional<Variable> getOptional(String name) {
        for (Variable variable : this.variables) {
            if (variable.name.equals(name)) {
                return Optional.of(variable);
            }
        }

        return Optional.empty();
    }

    public void deleteOrThrow(String name) {
        for (int i = 0; i < this.variables.size(); i++) {
            Variable variable = this.variables.get(i);

            if (variable.name.equals(name)) {
                this.variables.remove(i);
                return;
            }
        }

        throw new NameError("Variable '" + name + "' has not been declared");
    }

    public boolean exists(String name) {
        for (Variable variable : this.variables) {
            if (variable.name.equals(name)) {
                return true;
            }
        }

        return false;
    }
}
