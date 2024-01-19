package com.revolvingmadness.sculk.language;

import com.revolvingmadness.sculk.language.parser.nodes.statement_nodes.StatementNode;

import java.util.List;

public class SwitchStatementBody {
    public final List<SwitchStatementCase> body;
    public final List<StatementNode> defaultCase;

    public SwitchStatementBody(List<SwitchStatementCase> body, List<StatementNode> defaultCase) {
        this.body = body;
        this.defaultCase = defaultCase;
    }
}
