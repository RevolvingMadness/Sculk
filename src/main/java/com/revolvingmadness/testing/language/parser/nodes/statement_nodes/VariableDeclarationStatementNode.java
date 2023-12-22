package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

public class VariableDeclarationStatementNode extends StatementNode {
    public final boolean isConstant;
    public final IdentifierExpressionNode name;
    public final ExpressionNode value;

    public VariableDeclarationStatementNode(boolean isConstant, IdentifierExpressionNode name, ExpressionNode value) {
        this.isConstant = isConstant;
        this.name = name;
        this.value = value;
    }
}
