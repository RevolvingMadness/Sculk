package com.revolvingmadness.sculk.language.parser.nodes.statement_nodes;

import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.ExpressionNode;

import java.util.List;
import java.util.Objects;

public class ForStatementNode extends StatementNode {
    public final List<StatementNode> body;
    public final ExpressionNode condition;
    public final StatementNode initialization;
    public final ExpressionNode update;

    public ForStatementNode(StatementNode initialization, ExpressionNode condition, ExpressionNode update, List<StatementNode> body) {
        this.initialization = initialization;
        this.condition = condition;
        this.update = update;
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        ForStatementNode that = (ForStatementNode) o;
        return Objects.equals(this.body, that.body) && Objects.equals(this.condition, that.condition) && Objects.equals(this.initialization, that.initialization) && Objects.equals(this.update, that.update);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.body, this.condition, this.initialization, this.update);
    }
}
