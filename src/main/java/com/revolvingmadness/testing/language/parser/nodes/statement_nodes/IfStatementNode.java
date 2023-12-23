package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;

import java.util.List;
import java.util.Objects;

public class IfStatementNode extends StatementNode {
    public final List<StatementNode> body;
    public final ExpressionNode condition;

    public IfStatementNode(ExpressionNode condition, List<StatementNode> body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        IfStatementNode that = (IfStatementNode) o;
        return Objects.equals(this.body, that.body) && Objects.equals(this.condition, that.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.body, this.condition);
    }
}
