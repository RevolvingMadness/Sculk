package com.revolvingmadness.sculk.language.parser.nodes.expression_nodes;

import com.revolvingmadness.sculk.language.SwitchExpressionBody;

public class SwitchExpressionNode extends ExpressionNode {
    public final SwitchExpressionBody switchBody;
    public final ExpressionNode toSwitch;

    public SwitchExpressionNode(ExpressionNode toSwitch, SwitchExpressionBody switchBody) {
        this.toSwitch = toSwitch;
        this.switchBody = switchBody;
    }
}
