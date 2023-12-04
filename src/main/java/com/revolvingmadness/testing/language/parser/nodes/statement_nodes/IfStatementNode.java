package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

import java.util.List;

public class IfStatementNode implements StatementNode {
    public final ExpressionNode condition;
    public final List<StatementNode> body;

    public IfStatementNode(ExpressionNode condition, List<StatementNode> body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void interpret(ScriptNode script) {
        LiteralExpressionNode interpretedCondition = condition.interpret(script);

        if (interpretedCondition.isTruthy()) {
            body.forEach(statement -> statement.interpret(script));
        }
    }
}
