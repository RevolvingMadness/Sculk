package com.revolvingmadness.sculk.language.parser.nodes.expression_nodes;

public class TernaryExpressionNode extends ExpressionNode {
    public final ExpressionNode condition;
    public final ExpressionNode ifFalse;
    public final ExpressionNode ifTrue;

    public TernaryExpressionNode(ExpressionNode condition, ExpressionNode ifTrue, ExpressionNode ifFalse) {
        this.condition = condition;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }
}
