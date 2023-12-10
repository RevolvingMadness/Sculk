package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.ClassExpressionNode;

import java.util.List;

public class ClassDeclarationStatementNode implements StatementNode {
    public final boolean isConstant;
    public final IdentifierExpressionNode name;
    public final List<StatementNode> body;

    public ClassDeclarationStatementNode(boolean isConstant, IdentifierExpressionNode name, List<StatementNode> body) {
        this.isConstant = isConstant;
        this.name = name;
        this.body = body;
    }

    @Override
    public void interpret(ScriptNode script) {
        script.variableTable.enterScope();

        this.body.forEach(statement -> statement.interpret(script));

        VariableScope variableScope = script.variableTable.exitScope();

        script.variableTable.declare(this.isConstant, this.name, new ClassExpressionNode(this.name, variableScope));
    }
}
