package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

import java.util.List;

public class ClassDeclarationStatementNode implements StatementNode {
    public final List<StatementNode> body;
    public final boolean isConstant;
    public final IdentifierExpressionNode name;
    public final IdentifierExpressionNode superClassName;

    public ClassDeclarationStatementNode(boolean isConstant, IdentifierExpressionNode name, IdentifierExpressionNode superClassName, List<StatementNode> body) {
        this.isConstant = isConstant;
        this.name = name;
        this.superClassName = superClassName;
        this.body = body;
    }
}
