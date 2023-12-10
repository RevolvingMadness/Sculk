package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.LValueExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

public class VariableAssignmentExpressionNode implements ExpressionNode {
    public final LValueExpressionNode expression;
    public final ExpressionNode value;

    public VariableAssignmentExpressionNode(LValueExpressionNode expression, ExpressionNode value) {
        this.expression = expression;
        this.value = value;
    }

    @Override
    public LiteralExpressionNode interpret(ScriptNode script) {
        LiteralExpressionNode interpretedValue = this.value.interpret(script);
        Variable variable = this.expression.getVariable(script);

        script.variableTable.assign(variable, interpretedValue);

        return interpretedValue;
    }
}
