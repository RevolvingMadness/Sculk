package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

import java.util.List;

public class FunctionCallExpressionNode implements ExpressionNode {
    public final List<ExpressionNode> arguments;
    public final IdentifierExpressionNode name;

    public FunctionCallExpressionNode(IdentifierExpressionNode name, List<ExpressionNode> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;

        FunctionCallExpressionNode that = (FunctionCallExpressionNode) otherObject;

        if (!name.equals(that.name))
            return false;
        return arguments.equals(that.arguments);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + arguments.hashCode();
        return result;
    }

    @Override
    public LiteralExpressionNode interpret(ScriptNode script) {
        ExpressionNode functionVariable = script.variableTable.getOrThrow(this.name).value;
        LiteralExpressionNode interpretedFunctionVariable = functionVariable.interpret(script);

        return interpretedFunctionVariable.call(script, this.arguments);
    }
}
