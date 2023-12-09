package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.language.builtins.functions.io.PrintFunctionExpressionNode;
import com.revolvingmadness.testing.language.builtins.functions.math.AbsFunctionExpressionNode;
import com.revolvingmadness.testing.language.builtins.functions.types.BoolFunctionExpressionNode;
import com.revolvingmadness.testing.language.builtins.functions.types.FloatFunctionExpressionNode;
import com.revolvingmadness.testing.language.builtins.functions.types.IntFunctionExpressionNode;
import com.revolvingmadness.testing.language.builtins.functions.types.StrFunctionExpressionNode;
import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.interpreter.errors.ValueError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.FloatExpressionNode;
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

    public void reset() {
        this.variableScopes.clear();
        this.variableScopes.add(new VariableScope());

        this.assign(true, new IdentifierExpressionNode("print"), new PrintFunctionExpressionNode());
        this.assign(true, new IdentifierExpressionNode("abs"), new AbsFunctionExpressionNode());

        this.assign(true, new IdentifierExpressionNode("bool"), new BoolFunctionExpressionNode());
        this.assign(true, new IdentifierExpressionNode("float"), new FloatFunctionExpressionNode());
        this.assign(true, new IdentifierExpressionNode("int"), new IntFunctionExpressionNode());
        this.assign(true, new IdentifierExpressionNode("str"), new StrFunctionExpressionNode());

        this.assign(true, new IdentifierExpressionNode("PI"), new FloatExpressionNode(Math.PI));
    }


    public void assign(boolean isConstant, IdentifierExpressionNode name, LiteralExpressionNode value) {
        Logger.info("Assigning variable '" + name + "' to the value '" + value + "'");

        VariableScope variableScope = this.variableScopes.peek();

        Optional<Variable> optionalVariable = variableScope.getOptional(name);

        if (optionalVariable.isPresent()) {
            Variable variable = optionalVariable.get();

            if (variable.isConstant) {
                throw new ValueError("Cannot assign value to variable '" + name + "' because it is a constant");
            }

            variable.value = value;
        } else {
            variableScope.assign(isConstant, name, value);
        }
    }

    public void enterScope() {
        this.variableScopes.add(new VariableScope());
    }

    public void exitScope() {
        this.variableScopes.pop();
    }

    public Variable getOrThrow(IdentifierExpressionNode name) {
        Optional<Variable> variable = this.getOptional(name);

        if (variable.isEmpty()) {
            throw new NameError("Variable '" + name + "' has not been declared");
        }

        return variable.get();
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
}
