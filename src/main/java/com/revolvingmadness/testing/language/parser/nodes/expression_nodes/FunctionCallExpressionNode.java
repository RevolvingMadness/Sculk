package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.FunctionExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

import java.util.List;
import java.util.Map;

public class FunctionCallExpressionNode implements ExpressionNode {
    public final IdentifierExpressionNode name;
    public final List<ExpressionNode> arguments;

    public FunctionCallExpressionNode(IdentifierExpressionNode name, List<ExpressionNode> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public LiteralExpressionNode interpret(ScriptNode script) {
        script.variableTable.createScope();

        Variable functionToCallVariable = script.variableTable.getOrThrow(name);

        if (!(functionToCallVariable.value instanceof FunctionExpressionNode functionToCall)) {
            throw new TypeError("Type '" + functionToCallVariable.type + "' is not callable");
        }

        if (this.arguments.size() != functionToCall.arguments.size()) {
            throw new TypeError("Function '" + this.name + "' takes '" + functionToCall.arguments.size() + "' argument(s) but '" + this.arguments.size() + "' argument(s) were given");
        }

        int argumentNumber = 0;

        for (Map.Entry<IdentifierExpressionNode, IdentifierExpressionNode> entry : functionToCall.arguments.entrySet()) {
            LiteralExpressionNode argumentValue = this.arguments.get(argumentNumber++).interpret(script);
            if (entry.getKey() != argumentValue.getType()) {
                throw new TypeError("Expected type '" + entry.getKey() + "' for argument '" + argumentNumber + 1 + "' but got '" + argumentValue.getType() + "'");
            }
            script.variableTable.declareAndOrAssign(entry.getKey(), entry.getValue(), argumentValue);
        }

        functionToCall.body.forEach(statement -> statement.interpret(script));

        script.variableTable.exitScope();

        // return the return value of function
        return null;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;

        FunctionCallExpressionNode that = (FunctionCallExpressionNode) otherObject;

        if (!name.equals(that.name))
            return false;
        return arguments.equals(that.arguments);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + arguments.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.name + "()";
    }
}
