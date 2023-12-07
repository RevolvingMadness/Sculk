package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.interpreter.FunctionSignature;
import com.revolvingmadness.testing.language.interpreter.errors.Return;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.StatementNode;

import java.util.List;

public class FunctionExpressionNode implements LiteralExpressionNode {
    public final List<StatementNode> body;
    public final FunctionSignature signature;

    public FunctionExpressionNode(IdentifierExpressionNode name, List<IdentifierExpressionNode> arguments, List<StatementNode> body) {
        this.signature = new FunctionSignature(name, arguments);
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        FunctionExpressionNode that = (FunctionExpressionNode) o;

        if (!signature.equals(that.signature))
            return false;
        return body.equals(that.body);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("function");
    }

    @Override
    public int hashCode() {
        int result = signature.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "<function " + this.signature.name + ">";
    }

    @Override
    public StringExpressionNode toStringType() {
        return new StringExpressionNode("<function " + this.signature.name + ">");
    }
}
