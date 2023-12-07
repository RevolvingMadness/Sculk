package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.language.builtins.functions.PrintFunctionExpression;
import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

import java.util.ListIterator;
import java.util.Optional;
import java.util.Stack;

public class VariableTable {
    public final ScriptNode script;
    public final Stack<VariableScope> variableScopes;

    public VariableTable(ScriptNode script) {
        this.script = script;
        this.variableScopes = new Stack<>();
        this.reset();
    }

    public void assign(IdentifierExpressionNode name, LiteralExpressionNode value) {
        Logger.info("Assigning variable '" + name + "' to the value '" + value + "'");

        for (VariableScope variableScope : this.variableScopes) {
            Optional<Variable> variable = variableScope.getOptional(name);

            if (variable.isPresent()) {
                variable.get().value = value;
                return;
            }
        }

        this.variableScopes.peek().variables.add(new Variable(name, value));
    }

    public void enterScope() {
        this.variableScopes.add(new VariableScope());
    }

    public void exitScope() {
        this.variableScopes.pop();
    }

    private Optional<Variable> getOptional(IdentifierExpressionNode name) {
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

    public Variable getOrThrow(IdentifierExpressionNode name) {
        Optional<Variable> variable = this.getOptional(name);

        if (variable.isEmpty()) {
            throw new NameError("Variable '" + name + "' has not been declared");
        }

        return variable.get();
    }

    public void reset() {
        this.variableScopes.clear();
        this.variableScopes.add(new VariableScope());
        this.assign(new IdentifierExpressionNode("print"), new PrintFunctionExpression());
    }
}
