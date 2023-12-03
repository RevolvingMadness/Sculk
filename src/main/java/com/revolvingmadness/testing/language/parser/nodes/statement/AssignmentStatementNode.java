package com.revolvingmadness.testing.language.parser.nodes.statement;

import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression.IdentifierExpressionNode;

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
        script.variableTable.declareAndOrAssign(type, name, value);
    }
}
