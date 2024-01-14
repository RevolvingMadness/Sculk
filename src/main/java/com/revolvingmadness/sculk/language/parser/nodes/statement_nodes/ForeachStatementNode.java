package com.revolvingmadness.sculk.language.parser.nodes.statement_nodes;

import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.ExpressionNode;

import java.util.List;
import java.util.Objects;

public class ForeachStatementNode extends StatementNode {
    public final String variableName;
    public final ExpressionNode variableToIterate;
    public final List<StatementNode> body;

    public ForeachStatementNode(String variableName, ExpressionNode variableToIterate, List<StatementNode> body) {
        this.variableName = variableName;
        this.variableToIterate = variableToIterate;
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        ForeachStatementNode that = (ForeachStatementNode) o;
        return Objects.equals(this.variableName, that.variableName) && Objects.equals(this.variableToIterate, that.variableToIterate) && Objects.equals(this.body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.variableName, this.variableToIterate, this.body);
    }
}
