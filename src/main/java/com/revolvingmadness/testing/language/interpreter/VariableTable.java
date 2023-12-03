package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.language.interpreter.error.NameError;
import com.revolvingmadness.testing.language.parser.error.TypeError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression.LiteralExpressionNode;

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

    public void declareAndOrAssign(IdentifierExpressionNode type, IdentifierExpressionNode name, ExpressionNode value) {
        Objects.requireNonNull(name);

        if (type != null) {
            this.declare(type, name);
        }

        if (value != null) {
            this.assign(name, value);
        }
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
}
