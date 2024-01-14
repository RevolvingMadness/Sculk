package com.revolvingmadness.sculk.language.parser.nodes.statement_nodes;

import com.revolvingmadness.sculk.language.lexer.TokenType;
import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.ExpressionNode;

import java.util.List;
import java.util.Objects;

public class VariableDeclarationStatementNode extends StatementNode {
    public final List<TokenType> accessModifiers;
    public final String name;
    public final ExpressionNode value;

    public VariableDeclarationStatementNode(List<TokenType> accessModifiers, String name, ExpressionNode value) {
        this.accessModifiers = accessModifiers;
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        VariableDeclarationStatementNode that = (VariableDeclarationStatementNode) o;
        return Objects.equals(this.accessModifiers, that.accessModifiers) && Objects.equals(this.name, that.name) && Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.accessModifiers, this.name, this.value);
    }
}
