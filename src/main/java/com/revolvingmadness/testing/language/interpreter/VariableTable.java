package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.language.parser.nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;

import java.util.HashMap;
import java.util.Map;

public class VariableTable {
    public final ScriptNode program;
    public final Map<IdentifierExpressionNode, ExpressionNode> variables;

    public VariableTable(ScriptNode program) {
        this.program = program;
        this.variables = new HashMap<>();
    }

    public ExpressionNode get(IdentifierExpressionNode name) {
        return this.variables.get(name);
    }

    public void declareAndAssign(IdentifierExpressionNode type, IdentifierExpressionNode name, ExpressionNode value) {
        this.declare(type, name);

        this.assign(name, value);
    }

    private void assign(IdentifierExpressionNode name, ExpressionNode value) {
        ExpressionNode interpretedValue = value.interpret(program);

        Testing.LOGGER.info("Assigning '" + name + "' to the value '" + interpretedValue + "'");
        this.variables.put(name, interpretedValue);
    }

    private void declare(IdentifierExpressionNode type, IdentifierExpressionNode name) {
        Testing.LOGGER.info("Declaring '" + name + "'");
        this.variables.put(name, null);
    }
}
