package com.revolvingmadness.testing.language.parser.nodes;

public class IdentifierExpressionNode implements ExpressionNode {
    public final String value;

    public IdentifierExpressionNode(String value) {
        this.value = value;
    }

    @Override
    public ExpressionNode interpret(ScriptNode program) {
        return program.variableTable.getOrThrow(this).value;
    }

    @Override
    public IdentifierExpressionNode getType(ScriptNode program) {
        return program.variableTable.getOrThrow(this).value.getType(program);
    }

    @Override
    public ExpressionNode add(ScriptNode program, ExpressionNode other) {
        ExpressionNode value = program.variableTable.getOrThrow(this).value;

        return value.add(program, other);
    }

    @Override
    public ExpressionNode subtract(ScriptNode program, ExpressionNode other) {
        ExpressionNode value = program.variableTable.getOrThrow(this).value;

        return value.subtract(program, other);
    }

    @Override
    public ExpressionNode multiply(ScriptNode program, ExpressionNode other) {
        ExpressionNode value = program.variableTable.getOrThrow(this).value;

        return value.multiply(program, other);
    }

    @Override
    public ExpressionNode divide(ScriptNode program, ExpressionNode other) {
        ExpressionNode value = program.variableTable.getOrThrow(this).value;

        return value.divide(program, other);
    }

    @Override
    public ExpressionNode exponentiate(ScriptNode program, ExpressionNode other) {
        ExpressionNode value = program.variableTable.getOrThrow(this).value;

        return value.exponentiate(program, other);
    }

    @Override
    public ExpressionNode mod(ScriptNode program, ExpressionNode other) {
        ExpressionNode value = program.variableTable.getOrThrow(this).value;

        return value.mod(program, other);
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
