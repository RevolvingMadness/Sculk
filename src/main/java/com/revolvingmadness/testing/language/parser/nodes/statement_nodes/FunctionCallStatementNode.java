package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

import java.util.List;

public class FunctionCallStatementNode implements StatementNode {
    public final IdentifierExpressionNode name;
    public final List<ExpressionNode> arguments;

    public FunctionCallStatementNode(IdentifierExpressionNode name, List<ExpressionNode> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public void interpret(ScriptNode script) {
        script.variableTable.call(this.name, this.arguments);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + arguments.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;

        FunctionCallStatementNode that = (FunctionCallStatementNode) otherObject;

        if (!name.equals(that.name))
            return false;
        return arguments.equals(that.arguments);
    }

    @Override
    public String toString() {
        return this.name + "()";
    }
}
