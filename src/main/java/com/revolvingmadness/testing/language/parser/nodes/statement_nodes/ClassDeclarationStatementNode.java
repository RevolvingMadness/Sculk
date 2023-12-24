package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import java.util.List;
import java.util.Objects;

public class ClassDeclarationStatementNode extends StatementNode {
    public final List<StatementNode> body;
    public final boolean isConstant;
    public final String name;
    public final String superClassName;

    public ClassDeclarationStatementNode(boolean isConstant, String name, String superClassName, List<StatementNode> body) {
        this.isConstant = isConstant;
        this.name = name;
        this.superClassName = superClassName;
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        ClassDeclarationStatementNode that = (ClassDeclarationStatementNode) o;
        return this.isConstant == that.isConstant && Objects.equals(this.body, that.body) && Objects.equals(this.name, that.name) && Objects.equals(this.superClassName, that.superClassName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.body, this.isConstant, this.name, this.superClassName);
    }
}
