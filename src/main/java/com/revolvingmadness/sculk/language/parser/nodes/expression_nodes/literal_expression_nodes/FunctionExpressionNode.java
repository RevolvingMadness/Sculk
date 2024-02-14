package com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.sculk.language.Argument;
import com.revolvingmadness.sculk.language.parser.nodes.statement_nodes.StatementNode;

import java.util.List;
import java.util.Objects;

public class FunctionExpressionNode extends LiteralExpressionNode {
    public final List<Argument> arguments;
    public final List<StatementNode> body;
    public final String name;
    public final String returnType;

    public FunctionExpressionNode(String name, List<Argument> arguments, String returnType, List<StatementNode> body) {
        this.name = name;
        this.arguments = arguments;
        this.returnType = returnType;
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        FunctionExpressionNode that = (FunctionExpressionNode) o;
        return Objects.equals(this.arguments, that.arguments) && Objects.equals(this.body, that.body) && Objects.equals(this.name, that.name) && Objects.equals(this.returnType, that.returnType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.arguments, this.body, this.name, this.returnType);
    }
}
