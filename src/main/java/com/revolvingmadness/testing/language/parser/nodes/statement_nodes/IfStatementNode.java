package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;

import java.util.List;

public class IfStatementNode extends StatementNode {
    public final List<StatementNode> body;
    public final ExpressionNode condition;

    public IfStatementNode(ExpressionNode condition, List<StatementNode> body) {
        this.condition = condition;
        this.body = body;
    }
}
