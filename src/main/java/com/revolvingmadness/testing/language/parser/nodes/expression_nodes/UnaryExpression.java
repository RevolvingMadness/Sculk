package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

import com.revolvingmadness.testing.language.parser.UnaryOperatorType;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

public class UnaryExpression implements ExpressionNode {
    public final UnaryOperatorType type;
    public final ExpressionNode value;

    public UnaryExpression(UnaryOperatorType type, ExpressionNode value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public LiteralExpressionNode interpret(ScriptNode script) {
        LiteralExpressionNode interpretedValue = this.value.interpret(script);

        return switch (this.type) {
            case NOT -> interpretedValue.logicalNot();
            case NEGATION -> interpretedValue.negate();
        };
    }
}
