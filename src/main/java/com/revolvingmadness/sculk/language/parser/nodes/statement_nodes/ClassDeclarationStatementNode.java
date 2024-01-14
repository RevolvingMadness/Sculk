package com.revolvingmadness.sculk.language.parser.nodes.statement_nodes;

import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;
import java.util.Objects;

public class ClassDeclarationStatementNode extends StatementNode {
    public final List<TokenType> accessModifiers;
    public final List<StatementNode> body;
    public final String name;
    public final String superClassName;

    public ClassDeclarationStatementNode(List<TokenType> accessModifiers, String name, String superClassName, List<StatementNode> body) {
        this.accessModifiers = accessModifiers;
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
        return Objects.equals(this.body, that.body) && Objects.equals(this.accessModifiers, that.accessModifiers) && Objects.equals(this.name, that.name) && Objects.equals(this.superClassName, that.superClassName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.body, this.accessModifiers, this.name, this.superClassName);
    }
}
