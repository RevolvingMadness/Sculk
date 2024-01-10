package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.lexer.TokenType;

import java.util.List;
import java.util.Objects;

public class ClassDeclarationStatementNode extends StatementNode {
    public final List<StatementNode> body;
    public final List<TokenType> accessModifiers;
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
        if (o == null || getClass() != o.getClass())
            return false;
        ClassDeclarationStatementNode that = (ClassDeclarationStatementNode) o;
        return Objects.equals(body, that.body) && Objects.equals(accessModifiers, that.accessModifiers) && Objects.equals(name, that.name) && Objects.equals(superClassName, that.superClassName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, accessModifiers, name, superClassName);
    }
}
