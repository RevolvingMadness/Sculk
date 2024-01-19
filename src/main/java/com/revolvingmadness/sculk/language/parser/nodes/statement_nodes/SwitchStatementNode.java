package com.revolvingmadness.sculk.language.parser.nodes.statement_nodes;

import com.revolvingmadness.sculk.language.SwitchBody;
import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.ExpressionNode;

public class SwitchStatementNode extends StatementNode {
    public final SwitchBody switchBody;
    public final ExpressionNode toSwitch;

    public SwitchStatementNode(ExpressionNode toSwitch, SwitchBody switchBody) {
        this.toSwitch = toSwitch;
        this.switchBody = switchBody;
    }
}
