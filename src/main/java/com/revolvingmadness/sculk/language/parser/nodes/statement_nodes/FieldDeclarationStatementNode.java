package com.revolvingmadness.sculk.language.parser.nodes.statement_nodes;

import com.revolvingmadness.sculk.language.lexer.TokenType;
import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.ExpressionNode;

import java.util.List;
import java.util.Objects;

public class FieldDeclarationStatementNode extends StatementNode {
    public final List<TokenType> accessModifiers;
    public final String name;
    public final String type;
    public final ExpressionNode value;

    public FieldDeclarationStatementNode(List<TokenType> accessModifiers, String type, String name, ExpressionNode value) {
        this.accessModifiers = accessModifiers;
        this.type = type;
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
        return Objects.equals(this.accessModifiers, that.accessModifiers) && Objects.equals(this.name, that.name) && Objects.equals(this.type, that.type) && Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.accessModifiers, this.name, this.type, this.value);
    }
}
