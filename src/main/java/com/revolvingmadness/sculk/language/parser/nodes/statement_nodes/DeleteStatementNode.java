package com.revolvingmadness.sculk.language.parser.nodes.statement_nodes;

import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.ExpressionNode;

import java.util.Objects;

public class DeleteStatementNode extends StatementNode {
    public final ExpressionNode expression;

    public DeleteStatementNode(ExpressionNode expression) {
        this.expression = expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        DeleteStatementNode that = (DeleteStatementNode) o;
        return Objects.equals(this.expression, that.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.expression);
    }
}
