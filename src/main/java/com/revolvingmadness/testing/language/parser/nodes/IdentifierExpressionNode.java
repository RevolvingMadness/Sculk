package com.revolvingmadness.testing.language.parser.nodes;

public class IdentifierExpressionNode implements ExpressionNode {
    public final String value;

    public IdentifierExpressionNode(String value) {
        this.value = value;
    }

    @Override
    public ExpressionNode interpret(ScriptNode program) {
        return this;
    }

    @Override
    public IdentifierExpressionNode getType(ScriptNode program) {
        return program.variableTable.get(this).getType(program);
    }

    @Override
    public ExpressionNode add(ScriptNode program, ExpressionNode other) {
        ExpressionNode value = program.variableTable.get(this);

        return value.add(program, other);
    }

    @Override
    public ExpressionNode subtract(ScriptNode program, ExpressionNode other) {
        ExpressionNode value = program.variableTable.get(this);

        return value.subtract(program, other);
    }

    @Override
    public ExpressionNode multiply(ScriptNode program, ExpressionNode other) {
        ExpressionNode value = program.variableTable.get(this);

        return value.multiply(program, other);
    }

    @Override
    public ExpressionNode divide(ScriptNode program, ExpressionNode other) {
        ExpressionNode value = program.variableTable.get(this);

        return value.divide(program, other);
    }

    @Override
    public ExpressionNode exponentiate(ScriptNode program, ExpressionNode other) {
        ExpressionNode value = program.variableTable.get(this);

        return value.exponentiate(program, other);
    }

    @Override
    public ExpressionNode mod(ScriptNode program, ExpressionNode other) {
        ExpressionNode value = program.variableTable.get(this);

        return value.mod(program, other);
    }

    @Override
    public String toString() {
        return value;
    }
}
