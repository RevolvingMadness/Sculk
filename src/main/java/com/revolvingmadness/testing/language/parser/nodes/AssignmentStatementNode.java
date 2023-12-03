package com.revolvingmadness.testing.language.parser.nodes;

public class AssignmentStatementNode implements StatementNode {
    public final IdentifierExpressionNode type;
    public final IdentifierExpressionNode name;
    public final ExpressionNode value;

    public AssignmentStatementNode(IdentifierExpressionNode type, IdentifierExpressionNode name, ExpressionNode value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    @Override
    public void interpret(ScriptNode script) {
        script.variableTable.declareAndAssign(type, name, value);
    }
}
