package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.builtins.classes.MinecraftServerClass;
import com.revolvingmadness.testing.language.builtins.classes.PlayerManagerClass;
import com.revolvingmadness.testing.language.builtins.functions.io.PrintFunctionExpressionNode;
import com.revolvingmadness.testing.language.builtins.functions.math.AbsFunctionExpressionNode;
import com.revolvingmadness.testing.language.builtins.functions.types.BoolFunctionExpressionNode;
import com.revolvingmadness.testing.language.builtins.functions.types.FloatFunctionExpressionNode;
import com.revolvingmadness.testing.language.builtins.functions.types.IntFunctionExpressionNode;
import com.revolvingmadness.testing.language.builtins.functions.types.StrFunctionExpressionNode;
import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.interpreter.errors.ValueError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
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

    public void assign(Variable variable, LiteralExpressionNode value) {
        if (variable.isConstant) {
            throw new ValueError("Cannot assign value to variable '" + variable.name + "' because it is a constant");
        }

        variable.value = value;
    }

    public void declare(boolean isConstant, IdentifierExpressionNode name, LiteralExpressionNode value) {
        this.variableScopes.peek().declare(isConstant, name, value);
    }

    private void declareClasses() {
        this.declare(true, new IdentifierExpressionNode("server"), new MinecraftServerClass());
        this.declare(true, new IdentifierExpressionNode("playerManager"), new PlayerManagerClass());
    }

    private void declareFunctions() {
        this.declare(true, new IdentifierExpressionNode("print"), new PrintFunctionExpressionNode());
        this.declare(true, new IdentifierExpressionNode("abs"), new AbsFunctionExpressionNode());
        this.declare(true, new IdentifierExpressionNode("bool"), new BoolFunctionExpressionNode());
        this.declare(true, new IdentifierExpressionNode("float"), new FloatFunctionExpressionNode());
        this.declare(true, new IdentifierExpressionNode("int"), new IntFunctionExpressionNode());
        this.declare(true, new IdentifierExpressionNode("str"), new StrFunctionExpressionNode());
    }

    private void declareVariables() {
        this.declare(true, new IdentifierExpressionNode("PI"), new FloatExpressionNode(Math.PI));
    }

    public void enterScope() {
        this.variableScopes.add(new VariableScope());
    }

    public VariableScope exitScope() {
        return this.variableScopes.pop();
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

        this.declareClasses();
        this.declareFunctions();
        this.declareVariables();
    }
}
