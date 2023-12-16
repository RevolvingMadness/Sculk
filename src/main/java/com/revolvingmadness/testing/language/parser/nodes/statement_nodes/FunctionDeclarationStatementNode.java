package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.FunctionExpressionNode;

import java.util.List;

public class FunctionDeclarationStatementNode implements StatementNode {
    public final List<IdentifierExpressionNode> arguments;
    public final List<StatementNode> body;
    public final boolean isConstant;
    public final IdentifierExpressionNode name;

    public FunctionDeclarationStatementNode(boolean isConstant, IdentifierExpressionNode name, List<IdentifierExpressionNode> arguments, List<StatementNode> body) {
        this.isConstant = isConstant;
        this.name = name;
        this.arguments = arguments;
        this.body = body;
    }

    @Override
    public void interpret(ScriptNode script) {
        script.variableTable.declare(this.isConstant, this.name, new FunctionExpressionNode(this.name, this.arguments, this.body));
    }
}
