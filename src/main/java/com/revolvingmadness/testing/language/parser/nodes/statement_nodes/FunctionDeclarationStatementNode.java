package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

import java.util.List;
import java.util.Objects;

public class FunctionDeclarationStatementNode extends StatementNode {
    public final List<IdentifierExpressionNode> arguments;
    public final List<StatementNode> body;
    public final boolean isConstant;
    public final IdentifierExpressionNode name;

    public FunctionDeclarationStatementNode(boolean isConstant, IdentifierExpressionNode name, List<IdentifierExpressionNode> arguments, List<StatementNode> body) {
        this.isConstant = isConstant;
        this.name = name;
        this.arguments = arguments;
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        FunctionDeclarationStatementNode that = (FunctionDeclarationStatementNode) o;
        return this.isConstant == that.isConstant && Objects.equals(this.arguments, that.arguments) && Objects.equals(this.body, that.body) && Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.arguments, this.body, this.isConstant, this.name);
    }
}
