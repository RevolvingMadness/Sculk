package com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.sculk.language.parser.nodes.statement_nodes.StatementNode;

import java.util.List;

public class FunctionExpressionNode extends LiteralExpressionNode {
    public final List<String> arguments;
    public final List<StatementNode> body;
    public final String name;
    public final String returnType;

    public FunctionExpressionNode(String name, List<String> arguments, String returnType, List<StatementNode> body) {
        this.name = name;
        this.arguments = arguments;
        this.returnType = returnType;
        this.body = body;
    }
}
