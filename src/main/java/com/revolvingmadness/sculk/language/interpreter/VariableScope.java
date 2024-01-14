package com.revolvingmadness.sculk.language.interpreter;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class VariableScope implements Serializable {
    public final Map<String, Variable> variables;

    public VariableScope() {
        this.variables = new HashMap<>();
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
        if (this.exists(name)) {
            throw ErrorHolder.variableHasAlreadyBeenDeclared(name);
        }

        this.variables.put(name, new Variable(accessModifiers, name, value));
    }

    public void deleteOrThrow(String name) {
        if (!this.exists(name)) {
            throw ErrorHolder.variableHasNotBeenDeclared(name);
        }

        this.variables.remove(name);
    }

    public boolean exists(String name) {
        return this.variables.containsKey(name);
    }

    public Optional<Variable> getOptional(String name) {
        return Optional.ofNullable(this.variables.get(name));
    }
}
