package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;

import java.util.List;

public class ForStatementNode implements StatementNode {
    public final List<StatementNode> body;
    public final ExpressionNode condition;
    public final StatementNode initialization;
    public final ExpressionNode update;

    public ForStatementNode(StatementNode initialization, ExpressionNode condition, ExpressionNode update, List<StatementNode> body) {
        this.initialization = initialization;
        this.condition = condition;
        this.update = update;
        this.body = body;
    }
}
