package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.interpreter.errors.Return;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
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

        if (this.arguments.size() != arguments.size()) {
            throw new SyntaxError("Function '" + this.name + "' takes " + this.arguments.size() + " argument(s) but got " + arguments.size() + " argument(s)");
        }

        int argumentNumber = 0;

        for (IdentifierExpressionNode argumentName : this.arguments) {
            LiteralExpressionNode argumentValue = arguments.get(argumentNumber).interpret(script);
            script.variableTable.declare(true, argumentName, argumentValue);
        }

        try {
            this.body.forEach(statement -> statement.interpret(script));
        } catch (Return returnException) {
            script.variableTable.exitScope();
            return returnException.value;
        }

        script.variableTable.exitScope();

        return new NullExpressionNode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FunctionExpressionNode that = (FunctionExpressionNode) o;

        if (!arguments.equals(that.arguments)) return false;
        if (!name.equals(that.name)) return false;
        return body.equals(that.body);
    }

    @Override
    public int hashCode() {
        int result = arguments.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }

    @Override
    public BooleanExpressionNode equalTo(LiteralExpressionNode other) {
        if (other instanceof FunctionExpressionNode functionExpression) {
            return new BooleanExpressionNode(this.name.equals(functionExpression.name) && this.arguments.equals(functionExpression.arguments) && this.body.equals(functionExpression.body));
        }

        return new BooleanExpressionNode(false);
    }

    @Override
    public BooleanExpressionNode notEqualTo(LiteralExpressionNode other) {
        if (other instanceof FunctionExpressionNode functionExpression) {
            return new BooleanExpressionNode(!this.name.equals(functionExpression.name) || !this.arguments.equals(functionExpression.arguments) || !this.body.equals(functionExpression.body));
        }

        return new BooleanExpressionNode(true);
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
