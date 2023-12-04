package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.FunctionExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.NullExpressionNode;

import java.util.*;

public class VariableTable {
    public final ScriptNode script;
    public final Stack<List<Variable>> variableScopes;

    public VariableTable(ScriptNode script) {
        this.script = script;
        this.variableScopes = new Stack<>();
        this.variableScopes.add(new ArrayList<>());
    }

    private void assign(IdentifierExpressionNode name, ExpressionNode value) {
        Objects.requireNonNull(name);

        LiteralExpressionNode interpretedValue = value.interpret(this.script);

        Logger.info("Assigning '" + name + "' to the value '" + interpretedValue + "'");

        Variable existingVariable = this.getOrThrow(name);

        IdentifierExpressionNode interpretedValueType = interpretedValue.getType();

        if (!existingVariable.type.equals(interpretedValueType) && !interpretedValueType.value.equals("null")) {
            throw new TypeError("Expected type '" + existingVariable.type + "', but got type '" + interpretedValueType + "'");
        }

        existingVariable.value = interpretedValue;
    }

    public void createScope() {
        this.variableScopes.add(new ArrayList<>());
    }

    public void exitScope() {
        this.variableScopes.pop();
    }

    private void declare(IdentifierExpressionNode type, IdentifierExpressionNode name) {
        Objects.requireNonNull(name);

        Logger.info("Declaring '" + name + "'");

        Optional<Variable> existingVariable = this.get(name);

        if (existingVariable.isPresent()) {
            throw new NameError("Variable '" + name + "' is already declared");
        }

        this.variableScopes.peek().add(new Variable(type, name, new NullExpressionNode()));
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
        ListIterator<List<Variable>> iterator = this.variableScopes.listIterator();

        while (iterator.hasNext()) {
            iterator.next();
        }

        while (iterator.hasPrevious()) {
            List<Variable> variableScope = iterator.previous();

            for (Variable variable : variableScope) {
                if (variable.name.equals(name)) {
                    return Optional.of(variable);
                }
            }
        }

        return Optional.empty();
    }

    public LiteralExpressionNode call(IdentifierExpressionNode functionToCallName, List<ExpressionNode> functionToCallArguments) {
        script.variableTable.createScope();

        Variable functionToCallVariable = script.variableTable.getOrThrow(functionToCallName);

        if (!(functionToCallVariable.value instanceof FunctionExpressionNode functionDefinition)) {
            throw new TypeError("Type '" + functionToCallVariable.type + "' is not callable");
        }

        if (functionToCallArguments.size() != functionDefinition.arguments.size()) {
            throw new TypeError("Function '" + functionToCallName + "' takes '" + functionDefinition.arguments.size() + "' argument(s) but '" + functionToCallArguments.size() + "' argument(s) were given");
        }

        int argumentNumber = 0;

        for (Map.Entry<IdentifierExpressionNode, IdentifierExpressionNode> entry : functionDefinition.arguments.entrySet()) {
            IdentifierExpressionNode argumentType = entry.getKey();
            IdentifierExpressionNode argumentName = entry.getValue();

            LiteralExpressionNode argumentValue = functionToCallArguments.get(argumentNumber++).interpret(script);

            if (argumentType != argumentValue.getType()) {
                throw new TypeError("Expected type '" + argumentType + "' for argument '" + argumentName + "' but got '" + argumentValue.getType() + "'");
            }
            script.variableTable.declareAndOrAssign(entry.getKey(), entry.getValue(), argumentValue);
        }

        functionDefinition.body.forEach(statement -> statement.interpret(script));

        script.variableTable.exitScope();

        // Return function return value
        return null;
    }

    public Variable getOrThrow(IdentifierExpressionNode name) {
        Optional<Variable> variable = this.get(name);

        if (variable.isEmpty()) {
            throw new NameError("Variable '" + name + "' has not been declared");
        }

        return variable.get();
    }

    public void reset() {
        this.variableScopes.clear();
    }
}
