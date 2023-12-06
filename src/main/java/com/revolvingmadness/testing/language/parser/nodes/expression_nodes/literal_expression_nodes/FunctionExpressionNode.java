package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.errors.Return;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.StatementNode;

import java.util.List;
import java.util.Map;

public class FunctionExpressionNode extends CallableExpressionNode {
    public final List<StatementNode> body;

    public FunctionExpressionNode(IdentifierExpressionNode name, Map<IdentifierExpressionNode, IdentifierExpressionNode> arguments, IdentifierExpressionNode returnType, List<StatementNode> body) {
        super(name, returnType, arguments);
        this.body = body;
    }

    @Override
    public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
        script.variableTable.createScope();

        this.processArguments(script, arguments);

        try {
            this.body.forEach(statement -> statement.interpret(script));
        } catch (Return returnException) {
            LiteralExpressionNode returnValue = returnException.value;
            IdentifierExpressionNode returnValueType = returnValue.getType();

            if (!returnValueType.equals(this.returnType) && !returnValueType.equals(new IdentifierExpressionNode("null"))) {
                throw new TypeError("Expected return type '" + this.returnType + "' for function '" + this.name + "' but got '" + returnValueType + "'");
            }

            return returnValue;
        }

        script.variableTable.exitScope();

        return new NullExpressionNode();
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;

        FunctionExpressionNode that = (FunctionExpressionNode) otherObject;

        if (!name.equals(that.name))
            return false;
        if (!arguments.equals(that.arguments))
            return false;
        if (!returnType.equals(that.returnType))
            return false;
        return body.equals(that.body);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("function");
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + arguments.hashCode();
        result = 31 * result + returnType.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.name + "()";
    }
}
