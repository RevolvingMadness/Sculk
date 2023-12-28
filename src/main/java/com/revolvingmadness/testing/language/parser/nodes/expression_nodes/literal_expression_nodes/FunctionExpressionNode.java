package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.StatementNode;

import java.util.List;

public class FunctionExpressionNode extends LiteralExpressionNode {
    public final List<String> arguments;
    public final List<StatementNode> body;
    public final String name;

    public FunctionExpressionNode(String name, List<String> arguments, List<StatementNode> body) {
        this.name = name;
        this.arguments = arguments;
        this.body = body;
    }
}
