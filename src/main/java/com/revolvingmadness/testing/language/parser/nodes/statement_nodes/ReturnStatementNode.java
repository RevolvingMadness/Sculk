package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;

public class ReturnStatementNode extends StatementNode {
    public final ExpressionNode value;

    public ReturnStatementNode(ExpressionNode value) {
        this.value = value;
    }
}
