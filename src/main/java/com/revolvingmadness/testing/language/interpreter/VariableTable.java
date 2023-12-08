package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.language.builtins.functions.io.PrintFunctionExpression;
import com.revolvingmadness.testing.language.builtins.functions.math.AbsFunctionExpression;
import com.revolvingmadness.testing.language.builtins.functions.types.BooleanFunctionExpression;
import com.revolvingmadness.testing.language.builtins.functions.types.FloatFunctionExpression;
import com.revolvingmadness.testing.language.builtins.functions.types.IntFunctionExpression;
import com.revolvingmadness.testing.language.builtins.functions.types.StrFunctionExpression;
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

        this.assign(true, new IdentifierExpressionNode("print"), new PrintFunctionExpression());
        this.assign(true, new IdentifierExpressionNode("abs"), new AbsFunctionExpression());

        this.assign(true, new IdentifierExpressionNode("boolean"), new BooleanFunctionExpression());
        this.assign(true, new IdentifierExpressionNode("float"), new FloatFunctionExpression());
        this.assign(true, new IdentifierExpressionNode("int"), new IntFunctionExpression());
        this.assign(true, new IdentifierExpressionNode("str"), new StrFunctionExpression());

        this.assign(true, new IdentifierExpressionNode("PI"), new FloatExpressionNode(Math.PI));
    }

    public void assign(boolean isConstant, IdentifierExpressionNode name, LiteralExpressionNode value) {
        Logger.info("Assigning variable '" + name + "' to the value '" + value + "'");

        for (VariableScope variableScope : this.variableScopes) {
            Optional<Variable> optionalVariable = variableScope.getOptional(name);

            if (optionalVariable.isPresent()) {
                Variable variable = optionalVariable.get();

                if (variable.isConstant) {
                    throw new ValueError("Cannot assign value to variable '" + name + "' because it is a constant");
                }

                variable.value = value;
                return;
            }
        }

        this.variableScopes.peek().variables.add(new Variable(isConstant, name, value));
    }

    public void assign(IdentifierExpressionNode name, LiteralExpressionNode value) {
        this.assign(false, name, value);
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
