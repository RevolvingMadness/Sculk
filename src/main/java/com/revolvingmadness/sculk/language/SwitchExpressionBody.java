package com.revolvingmadness.sculk.language;

import com.revolvingmadness.sculk.language.parser.nodes.statement_nodes.StatementNode;

import java.util.List;

public class SwitchExpressionBody {
    public final List<SwitchExpressionCase> body;
    public final List<StatementNode> defaultCase;

    public SwitchExpressionBody(List<SwitchExpressionCase> body, List<StatementNode> defaultCase) {
        this.body = body;
        this.defaultCase = defaultCase;
    }
}
