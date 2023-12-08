package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.interpreter.errors.Return;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.StatementNode;

import java.util.List;

public class FunctionExpressionNode implements LiteralExpressionNode {
    public final List<IdentifierExpressionNode> arguments;
    public final IdentifierExpressionNode name;
    public final List<StatementNode> body;

    public FunctionExpressionNode(IdentifierExpressionNode name, List<IdentifierExpressionNode> arguments, List<StatementNode> body) {
        this.name = name;
        this.arguments = arguments;
        this.body = body;
    }

    @Override
    public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
        script.variableTable.enterScope();

        try {
            this.body.forEach(statement -> statement.interpret(script));
        } catch (Return returnException) {
            return returnException.value;
        }

        script.variableTable.exitScope();

        return new NullExpressionNode();
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("function");
    }

    @Override
    public String toString() {
        return "<function " + this.name + ">";
    }

    @Override
    public StringExpressionNode toStringType() {
        return new StringExpressionNode("<function " + this.name + ">");
    }
}
