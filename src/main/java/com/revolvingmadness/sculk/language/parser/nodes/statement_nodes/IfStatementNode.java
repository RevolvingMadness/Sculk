package com.revolvingmadness.sculk.language.parser.nodes.statement_nodes;

import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.ExpressionNode;
import net.minecraft.util.Pair;

import java.util.List;
import java.util.Objects;

public class IfStatementNode extends StatementNode {
    public final List<StatementNode> elseBody;
    public final List<Pair<ExpressionNode, List<StatementNode>>> elseIfConditionPairs;
    public final Pair<ExpressionNode, List<StatementNode>> ifConditionPair;

    public IfStatementNode(Pair<ExpressionNode, List<StatementNode>> ifConditionPair, List<Pair<ExpressionNode, List<StatementNode>>> elseIfConditionPairs, List<StatementNode> elseBody) {
        this.ifConditionPair = ifConditionPair;
        this.elseIfConditionPairs = elseIfConditionPairs;
        this.elseBody = elseBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        IfStatementNode that = (IfStatementNode) o;
        return Objects.equals(this.ifConditionPair, that.ifConditionPair) && Objects.equals(this.elseIfConditionPairs, that.elseIfConditionPairs) && Objects.equals(this.elseBody, that.elseBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ifConditionPair, this.elseIfConditionPairs, this.elseBody);
    }
}
