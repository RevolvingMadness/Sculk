package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;

import java.util.List;

public class WhileStatementNode extends StatementNode {
    public final List<StatementNode> body;
    public final ExpressionNode condition;

    public WhileStatementNode(ExpressionNode condition, List<StatementNode> body) {
        this.condition = condition;
        this.body = body;
    }
}
