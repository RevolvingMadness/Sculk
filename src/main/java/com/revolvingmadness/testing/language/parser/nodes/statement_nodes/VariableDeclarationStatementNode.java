package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

public class VariableDeclarationStatementNode implements StatementNode {
    public final boolean isConstant;
    public final IdentifierExpressionNode name;
    public final ExpressionNode value;

    public VariableDeclarationStatementNode(boolean isConstant, IdentifierExpressionNode name, ExpressionNode value) {
        this.isConstant = isConstant;
        this.name = name;
        this.value = value;
    }

    @Override
    public void interpret(ScriptNode script) {
        LiteralExpressionNode interpretedValue = this.value.interpret(script);
        script.variableTable.declare(this.isConstant, this.name, interpretedValue);
    }
}
