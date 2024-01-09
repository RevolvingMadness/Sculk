package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.instances.FloatInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.GameRulesInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.MinecraftServerInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.PlayerManagerInstance;
import com.revolvingmadness.testing.language.builtins.functions.io.PrintFunction;
import com.revolvingmadness.testing.language.builtins.functions.types.TypeFunction;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.lexer.TokenType;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Stack;

public class VariableTable {
    public final Stack<VariableScope> variableScopes;

    public VariableTable() {
        this.variableScopes = new Stack<>();
        this.reset();
    }

    public void assign(String name, BuiltinClass value) {
        Optional<Variable> optionalVariable = this.getOptional(name);

        if (optionalVariable.isEmpty()) {
            throw ErrorHolder.variableHasNotBeenDeclared(name);
        }

        Variable variable = optionalVariable.get();

        if (variable.isConstant) {
            throw ErrorHolder.cannotAssignValueToVariableBecauseItIsAConstant(variable.name);
        }

        variable.value = value;
    }

    public void declare(boolean isConstant, String name, BuiltinClass value) {
        this.declare(List.of(), isConstant, name, value);
    }

    public void declare(List<TokenType> accessModifiers, boolean isConstant, String name, BuiltinClass value) {
        this.variableScopes.peek().declare(accessModifiers, isConstant, name, value);
    }

    private void declareClasses() {
        this.declare(true, "server", new MinecraftServerInstance(Testing.server));
        this.declare(true, "playerManager", new PlayerManagerInstance(Testing.server.getPlayerManager()));
        this.declare(true, "gameRules", new GameRulesInstance(Testing.server.getGameRules()));
    }

    private void declareFunctions() {
        this.declare(true, "print", new PrintFunction());
        this.declare(true, "type", new TypeFunction());
    }

    private void declareVariables() {
        this.declare(true, "PI", new FloatInstance(Math.PI));
    }

    public void deleteOrThrow(String name) {
        ListIterator<VariableScope> variableScopeIterator = this.variableScopes.listIterator();

        while (variableScopeIterator.hasNext()) {
            variableScopeIterator.next();
        }

        while (variableScopeIterator.hasPrevious()) {
            VariableScope variableScope = variableScopeIterator.previous();

            if (variableScope.exists(name)) {
                variableScope.deleteOrThrow(name);
            }
        }

        throw ErrorHolder.variableHasNotBeenDeclared(name);
    }

    public void enterScope() {
        this.variableScopes.add(new VariableScope());
    }

    public VariableScope exitScope() {
        return this.variableScopes.pop();
    }

    private Optional<Variable> getOptional(String name) {
        ListIterator<VariableScope> variableScopeIterator = this.variableScopes.listIterator();

        while (variableScopeIterator.hasNext()) {
            variableScopeIterator.next();
        }

        while (variableScopeIterator.hasPrevious()) {
            VariableScope variableScope = variableScopeIterator.previous();

            Optional<Variable> variable = variableScope.getOptional(name);

            if (variable.isPresent()) {
                return variable;
            }
        }

        return Optional.empty();
    }

    public Variable getOrThrow(String name) {
        Optional<Variable> variable = this.getOptional(name);

        if (variable.isEmpty()) {
            throw ErrorHolder.variableHasNotBeenDeclared(name);
        }

        return variable.get();
    }

    public void reset() {
        this.variableScopes.clear();
        this.variableScopes.add(new VariableScope());

        this.declareClasses();
        this.declareFunctions();
        this.declareVariables();
    }
}
