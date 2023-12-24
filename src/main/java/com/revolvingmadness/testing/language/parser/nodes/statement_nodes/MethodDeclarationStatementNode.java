package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.lexer.TokenType;

import java.util.List;
import java.util.Objects;

public class MethodDeclarationStatementNode extends StatementNode {
    public final List<TokenType> accessModifiers;
    public final List<String> arguments;
    public final List<StatementNode> body;
    public final String name;

    public MethodDeclarationStatementNode(List<TokenType> accessModifiers, String name, List<String> arguments, List<StatementNode> body) {
        this.accessModifiers = accessModifiers;
        this.name = name;
        this.arguments = arguments;
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        MethodDeclarationStatementNode that = (MethodDeclarationStatementNode) o;
        return Objects.equals(this.accessModifiers, that.accessModifiers) && Objects.equals(this.arguments, that.arguments) && Objects.equals(this.body, that.body) && Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.accessModifiers, this.arguments, this.body, this.name);
    }
}
