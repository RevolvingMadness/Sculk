package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
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

    public void assign(String name, BuiltinClass value) {
        Optional<Variable> optionalVariable = this.getOptional(name);

        if (optionalVariable.isEmpty()) {
            throw ErrorHolder.variableHasNotBeenDeclared(name);
        }

        Variable variable = optionalVariable.get();

        if (variable.isAbstract()) {
            throw ErrorHolder.cannotAssignValueToVariableBecauseItIsAConstant(variable.name);
        }

        variable.value = value;
    }

    public void declare(List<TokenType> accessModifiers, String name, BuiltinClass value) {
        Optional<Variable> optionalVariable = this.getOptional(name);

        if (optionalVariable.isPresent()) {
            throw ErrorHolder.variableHasAlreadyBeenDeclared(name);
        }

        this.variables.add(new Variable(accessModifiers, name, value));
    }

    public void deleteOrThrow(String name) {
        for (int i = 0; i < this.variables.size(); i++) {
            Variable variable = this.variables.get(i);

            if (variable.name.equals(name)) {
                this.variables.remove(i);
                return;
            }
        }

        throw ErrorHolder.variableHasNotBeenDeclared(name);
    }

    public boolean exists(String name) {
        for (Variable variable : this.variables) {
            if (variable.name.equals(name)) {
                return true;
            }
        }

        return false;
    }

    public Optional<Variable> getOptional(String name) {
        for (Variable variable : this.variables) {
            if (variable.name.equals(name)) {
                return Optional.of(variable);
            }
        }

        return Optional.empty();
    }
}
