package com.revolvingmadness.sculk.language.parser.nodes.statement_nodes;

import com.revolvingmadness.sculk.language.SwitchStatementBody;
import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.ExpressionNode;

public class SwitchStatementNode extends StatementNode {
    public final SwitchStatementBody switchBody;
    public final ExpressionNode toSwitch;

    public SwitchStatementNode(ExpressionNode toSwitch, SwitchStatementBody switchBody) {
        this.toSwitch = toSwitch;
        this.switchBody = switchBody;
    }
}
