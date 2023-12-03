package com.revolvingmadness.testing.language.parser.nodes.expression;

import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;

public class IdentifierExpressionNode implements ExpressionNode {
    public final String value;

    public IdentifierExpressionNode(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;

        IdentifierExpressionNode otherIdentifierExpression = (IdentifierExpressionNode) otherObject;

        return value.equals(otherIdentifierExpression.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public LiteralExpressionNode interpret(ScriptNode script) {
        return script.variableTable.getOrThrow(this).value;
    }

    @Override
    public String toString() {
        return value;
    }
}
