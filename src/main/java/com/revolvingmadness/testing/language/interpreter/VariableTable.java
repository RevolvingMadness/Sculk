package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

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

    private void assign(IdentifierExpressionNode name, ExpressionNode value) {
        Objects.requireNonNull(name);

        LiteralExpressionNode interpretedValue = value.interpret(this.script);

        Logger.info("Assigning '" + name + "' to the value '" + interpretedValue + "'");

        Variable existingVariable = this.getOrThrow(name);

        IdentifierExpressionNode interpretedValueType = interpretedValue.getType();

        if (!existingVariable.type.equals(interpretedValueType)) {
            throw new TypeError("Expected type '" + existingVariable.type + "', but got type '" + interpretedValueType + "'");
        }

        existingVariable.value = interpretedValue;
    }

    private void declare(IdentifierExpressionNode type, IdentifierExpressionNode name) {
        Objects.requireNonNull(name);

        Logger.info("Declaring '" + name + "'");

        Optional<Variable> existingVariable = this.get(name);

        if (existingVariable.isPresent()) {
            throw new NameError("Variable '" + name + "' is already declared");
        }

        this.variables.add(new Variable(type, name, null));
    }

    public void declareAndOrAssign(IdentifierExpressionNode type, IdentifierExpressionNode name, ExpressionNode value) {
        Objects.requireNonNull(name);

        if (Testing.keywords.containsKey(name.value)) {
            throw new SyntaxError("Cannot assign variable with the name '" + name + "'");
        }

        if (type != null) {
            this.declare(type, name);
        }

        if (value != null) {
            this.assign(name, value);
        }
    }

    public Optional<Variable> get(IdentifierExpressionNode name) {
        for (Variable variable : this.variables) {
            if (variable.name.equals(name)) {
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

        throw new NameError("Variable '" + name + "' has not been declared");
    }

    public void reset() {
        this.variables.clear();
    }
}
