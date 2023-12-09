package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

import java.util.List;

public class CallExpressionNode implements ExpressionNode {
    public final List<ExpressionNode> arguments;
    public final ExpressionNode expression;

    public CallExpressionNode(ExpressionNode expression, List<ExpressionNode> arguments) {
        this.expression = expression;
        this.arguments = arguments;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;

        CallExpressionNode that = (CallExpressionNode) otherObject;

        if (!expression.equals(that.expression))
            return false;
        return arguments.equals(that.arguments);
    }

    @Override
    public int hashCode() {
        int result = expression.hashCode();
        result = 31 * result + arguments.hashCode();
        return result;
    }

    @Override
    public LiteralExpressionNode interpret(ScriptNode script) {
        LiteralExpressionNode interpretedExpression = this.expression.interpret(script);

        return interpretedExpression.call(script, this.arguments);
    }
}
