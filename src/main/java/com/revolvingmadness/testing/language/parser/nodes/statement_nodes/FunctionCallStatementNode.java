package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.CallableExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

import java.util.List;

public class FunctionCallStatementNode implements StatementNode {
    public final List<ExpressionNode> arguments;
    public final IdentifierExpressionNode name;

    public FunctionCallStatementNode(IdentifierExpressionNode name, List<ExpressionNode> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;

        FunctionCallStatementNode functionCallStatement = (FunctionCallStatementNode) otherObject;

        if (!name.equals(functionCallStatement.name))
            return false;
        return arguments.equals(functionCallStatement.arguments);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + arguments.hashCode();
        return result;
    }

    @Override
    public void interpret(ScriptNode script) {
        ExpressionNode functionVariable = script.variableTable.getOrThrow(this.name).value;
        LiteralExpressionNode interpretedFunctionVariable = functionVariable.interpret(script);

        if (!(interpretedFunctionVariable instanceof CallableExpressionNode callableExpression)) {
            throw new TypeError("Variable '" + this.name + "' is not callable");
        }

        callableExpression.call(script, this.arguments);
    }

    @Override
    public String toString() {
        return this.name + "()";
    }
}
