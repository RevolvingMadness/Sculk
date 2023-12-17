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
        if (otherObject == null || this.getClass() != otherObject.getClass())
            return false;

        CallExpressionNode that = (CallExpressionNode) otherObject;

        if (!this.expression.equals(that.expression))
            return false;
        return this.arguments.equals(that.arguments);
    }

    @Override
    public int hashCode() {
        int result = this.expression.hashCode();
        result = 31 * result + this.arguments.hashCode();
        return result;
    }

    @Override
    public LiteralExpressionNode interpret(ScriptNode script) {
        LiteralExpressionNode interpretedExpression = this.expression.interpret(script);

        return interpretedExpression.call(script, this.arguments);
    }
}
