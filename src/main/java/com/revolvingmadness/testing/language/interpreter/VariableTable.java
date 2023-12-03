package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.language.interpreter.error.NameError;
import com.revolvingmadness.testing.language.parser.nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class VariableTable {
    public final ScriptNode script;
    public final List<Variable> variables;

    public VariableTable(ScriptNode script) {
        this.script = script;
        this.variables = new ArrayList<>();
    }

    public Optional<Variable> get(IdentifierExpressionNode name) {
        for (Variable variable : this.variables) {
            if (variable.name == name) {
                return Optional.of(variable);
            }
        }

        return Optional.empty();
    }

    public Variable getOrThrow(IdentifierExpressionNode name) {
        for (Variable variable : this.variables) {
            if (variable.name.equals(name)) {
                return variable;
            }
        }

        throw new NameError("Variable '" + name + "' is not defined");
    }

    public void declareAndAssign(IdentifierExpressionNode type, IdentifierExpressionNode name, ExpressionNode value) {
        Objects.requireNonNull(name);

        if (type != null) {
            this.declare(type, name);
        }

        if (value != null) {
            this.assign(name, value);
        }
    }

    private void assign(IdentifierExpressionNode name, ExpressionNode value) {
        ExpressionNode interpretedValue = value.interpret(this.script);

        Logger.info("Assigning '" + name + "' to the value '" + interpretedValue + "'");

        Optional<Variable> existingVariable = this.get(name);

        if (existingVariable.isEmpty()) {
            throw new NameError("Variable '" + name + "' has not been declared");
        }

        for (Variable variable : this.variables) {
            if (variable.name == name) {
                variable.value = value;
                break;
            }
        }
    }

    private void declare(IdentifierExpressionNode type, IdentifierExpressionNode name) {
        Logger.info("Declaring '" + name + "'");

        Optional<Variable> existingVariable = this.get(name);

        if (existingVariable.isPresent()) {
            throw new NameError("Variable '" + name + "' is already declared");
        }

        this.variables.add(new Variable(type, name, null));
    }
}
