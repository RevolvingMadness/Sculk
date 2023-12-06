package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.FunctionExpressionNode;

import java.util.List;
import java.util.Map;

public class FunctionDeclarationStatement implements StatementNode {
    public final Map<IdentifierExpressionNode, IdentifierExpressionNode> arguments;
    public final List<StatementNode> body;
    public final IdentifierExpressionNode name;
    public final ExpressionNode returnType;

    public FunctionDeclarationStatement(IdentifierExpressionNode name, Map<IdentifierExpressionNode, IdentifierExpressionNode> arguments, ExpressionNode returnType, List<StatementNode> body) {
        this.name = name;
        this.arguments = arguments;
        this.returnType = returnType;
        this.body = body;
    }

    @Override
    public void interpret(ScriptNode script) {
        script.variableTable.declareAndOrAssign(new IdentifierExpressionNode("function"), this.name, new FunctionExpressionNode(this.name, this.arguments, this.returnType, this.body));
    }
}
