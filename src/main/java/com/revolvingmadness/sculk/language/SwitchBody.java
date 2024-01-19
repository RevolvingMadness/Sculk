package com.revolvingmadness.sculk.language;

import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.sculk.language.parser.nodes.statement_nodes.StatementNode;

import java.util.List;
import java.util.Map;

public class SwitchBody {
    public final Map<List<ExpressionNode>, List<StatementNode>> body;
    public final List<StatementNode> defaultCase;

    public SwitchBody(Map<List<ExpressionNode>, List<StatementNode>> body, List<StatementNode> defaultCase) {
        this.body = body;
        this.defaultCase = defaultCase;
    }
}
