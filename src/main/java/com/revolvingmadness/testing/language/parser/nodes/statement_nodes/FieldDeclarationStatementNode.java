package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.lexer.TokenType;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;

import java.util.List;
import java.util.Objects;

public class FieldDeclarationStatementNode extends StatementNode {
    public final List<TokenType> accessModifiers;
    public final boolean isConstant;
    public final String name;
    public final ExpressionNode value;

    public FieldDeclarationStatementNode(List<TokenType> accessModifiers, boolean isConstant, String name, ExpressionNode value) {
        this.accessModifiers = accessModifiers;
        this.isConstant = isConstant;
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        FieldDeclarationStatementNode that = (FieldDeclarationStatementNode) o;
        return this.isConstant == that.isConstant && Objects.equals(this.name, that.name) && Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.isConstant, this.name, this.value);
    }
}