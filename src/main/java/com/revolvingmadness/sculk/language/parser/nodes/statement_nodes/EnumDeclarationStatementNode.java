package com.revolvingmadness.sculk.language.parser.nodes.statement_nodes;

import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;
import java.util.Objects;

public class EnumDeclarationStatementNode extends StatementNode {
    public final List<TokenType> accessModifiers;
    public final String name;
    public final List<String> values;

    public EnumDeclarationStatementNode(List<TokenType> accessModifiers, String name, List<String> values) {
        this.accessModifiers = accessModifiers;
        this.name = name;
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        EnumDeclarationStatementNode that = (EnumDeclarationStatementNode) o;
        return Objects.equals(this.accessModifiers, that.accessModifiers) && Objects.equals(this.values, that.values) && Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.accessModifiers, this.values, this.name);
    }
}
