package com.revolvingmadness.sculk.language.interpreter;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.NullClassType;
import com.revolvingmadness.sculk.language.errors.NameError;
import com.revolvingmadness.sculk.language.errors.SyntaxError;
import com.revolvingmadness.sculk.language.errors.TypeError;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.io.Serializable;
import java.util.*;

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

        if (variable.isConstant()) {
            throw ErrorHolder.cannotChangeValueOfVariableBecauseItIsAConstant(variable.name);
        }

        if (variable.isNonNull() && value.equals(new NullInstance())) {
            throw new TypeError("Variable '" + name + "' is non-null and was assigned a null value");
        }

        if (!value.instanceOf(NullClassType.TYPE)) {
            if (!value.instanceOf(variable.type)) {
                throw new SyntaxError("Cannot assign a value with type '" + variable.type.name + "' to a variable that requires the type '" + value.type.name + "'");
            }
        }

        variable.value = value;
    }

    public void declare(List<TokenType> accessModifiers, BuiltinClassType type, String name, BuiltinClass value) {
        if (this.exists(name)) {
            throw new NameError("Variable '" + name + "' has already been declared");
        }

        if (!value.instanceOf(type)) {
            throw new TypeError("Cannot declare a variable with type '" + value.type.name + "' that requires type '" + type.name + "'");
        }

        value.type = type;

        this.variables.put(name, new Variable(accessModifiers, type, name, value));
    }

    public void declare(List<TokenType> accessModifiers, String name, BuiltinClass value) {
        this.declare(accessModifiers, value.type, name, value);
    }

    public void deleteOrThrow(String name) {
        if (!this.exists(name)) {
            throw ErrorHolder.variableHasNotBeenDeclared(name);
        }

        this.variables.remove(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        VariableScope that = (VariableScope) o;
        return Objects.equals(this.variables, that.variables);
    }

    public boolean exists(String name) {
        return this.variables.containsKey(name);
    }

    public Optional<Variable> getOptional(String name) {
        return Optional.ofNullable(this.variables.get(name));
    }

    public Variable getOrThrow(String name) {
        Optional<Variable> variable = this.getOptional(name);

        if (variable.isEmpty()) {
            throw ErrorHolder.variableHasNotBeenDeclared(name);
        }

        return variable.get();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.variables);
    }
}
