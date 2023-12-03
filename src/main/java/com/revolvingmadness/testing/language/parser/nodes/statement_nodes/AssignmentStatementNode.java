package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

public class AssignmentStatementNode implements StatementNode {
    public final IdentifierExpressionNode name;
    public final IdentifierExpressionNode type;
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
