package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes;

import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

public class IdentifierExpressionNode implements LValueExpressionNode {
    public final String value;

    public IdentifierExpressionNode(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || this.getClass() != otherObject.getClass())
            return false;

        IdentifierExpressionNode otherIdentifierExpression = (IdentifierExpressionNode) otherObject;

        return this.value.equals(otherIdentifierExpression.value);
    }

    @Override
    public Variable getVariable(ScriptNode script) {
        return script.variableTable.getOrThrow(this);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public LiteralExpressionNode interpret(ScriptNode script) {
        return script.variableTable.getOrThrow(this).value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
