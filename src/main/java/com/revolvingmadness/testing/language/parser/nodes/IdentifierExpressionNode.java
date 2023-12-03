package com.revolvingmadness.testing.language.parser.nodes;

public class IdentifierExpressionNode implements ExpressionNode {
    public final String value;

    public IdentifierExpressionNode(String value) {
        this.value = value;
    }

    @Override
    public ExpressionNode interpret(ScriptNode script) {
        return script.variableTable.getOrThrow(this).value;
    }

    @Override
    public IdentifierExpressionNode getType(ScriptNode script) {
        return script.variableTable.getOrThrow(this).value.getType(script);
    }

    @Override
    public ExpressionNode add(ScriptNode script, ExpressionNode other) {
        ExpressionNode value = script.variableTable.getOrThrow(this).value;

        return value.add(script, other);
    }

    @Override
    public ExpressionNode subtract(ScriptNode script, ExpressionNode other) {
        ExpressionNode value = script.variableTable.getOrThrow(this).value;

        return value.subtract(script, other);
    }

    @Override
    public ExpressionNode multiply(ScriptNode script, ExpressionNode other) {
        ExpressionNode value = script.variableTable.getOrThrow(this).value;

        return value.multiply(script, other);
    }

    @Override
    public ExpressionNode divide(ScriptNode script, ExpressionNode other) {
        ExpressionNode value = script.variableTable.getOrThrow(this).value;

        return value.divide(script, other);
    }

    @Override
    public ExpressionNode exponentiate(ScriptNode script, ExpressionNode other) {
        ExpressionNode value = script.variableTable.getOrThrow(this).value;

        return value.exponentiate(script, other);
    }

    @Override
    public ExpressionNode mod(ScriptNode script, ExpressionNode other) {
        ExpressionNode value = script.variableTable.getOrThrow(this).value;

        return value.mod(script, other);
    }

    @Override
    public String toString() {
        return value;
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
}
