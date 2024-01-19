package com.revolvingmadness.sculk.language;

import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.sculk.language.parser.nodes.statement_nodes.StatementNode;

import java.util.List;

public class SwitchStatementCase {
    public final List<ExpressionNode> expressionNodes;
    public final List<StatementNode> statementNodes;

    public SwitchStatementCase(List<ExpressionNode> expressionNodes, List<StatementNode> statementNodes) {
        this.expressionNodes = expressionNodes;
        this.statementNodes = statementNodes;
    }
}
