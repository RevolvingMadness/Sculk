package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.lexer.TokenType;

import java.util.List;
import java.util.Objects;

public class FunctionDeclarationStatementNode extends StatementNode {
    public final List<TokenType> accessModifiers;
    public final List<String> arguments;
    public final List<StatementNode> body;
    public final String name;

    public FunctionDeclarationStatementNode(List<TokenType> accessModifiers, String name, List<String> arguments, List<StatementNode> body) {
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
        FunctionDeclarationStatementNode that = (FunctionDeclarationStatementNode) o;
        return Objects.equals(this.arguments, that.arguments) && Objects.equals(this.body, that.body) && Objects.equals(this.accessModifiers, that.accessModifiers) && Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.arguments, this.body, this.accessModifiers, this.name);
    }
}
