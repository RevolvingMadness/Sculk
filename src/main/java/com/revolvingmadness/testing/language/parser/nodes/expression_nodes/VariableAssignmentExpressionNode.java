package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

public class VariableAssignmentExpressionNode implements ExpressionNode {
    public final ExpressionNode expression;
    public final ExpressionNode value;

    public VariableAssignmentExpressionNode(ExpressionNode expression, ExpressionNode value) {
        this.expression = expression;
        this.value = value;
    }

    @Override
    public LiteralExpressionNode interpret(ScriptNode script) {
        LiteralExpressionNode interpretedExpression = this.expression.interpret(script);
        LiteralExpressionNode interpretedValue = this.value.interpret(script);

        script.variableTable.assign(interpretedExpression, interpretedValue);

        return interpretedValue;
    }
}
