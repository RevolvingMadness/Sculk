package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.gamerules.TestingGamerules;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.interpreter.errors.MaxArgumentError;
import com.revolvingmadness.testing.language.interpreter.errors.Return;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.StatementNode;

import java.util.List;

public class FunctionExpressionNode implements LiteralExpressionNode {
    public final List<IdentifierExpressionNode> arguments;
    public final List<StatementNode> body;
    public final IdentifierExpressionNode name;
    public ClassInstanceExpressionNode clazz;
    public ClassExpressionNode superClass;

    public FunctionExpressionNode(IdentifierExpressionNode name, List<IdentifierExpressionNode> arguments, List<StatementNode> body) {
        this.name = name;
        this.arguments = arguments;
        this.body = body;
    }

    public void bind(ClassInstanceExpressionNode classInstance, ClassExpressionNode superClass) {
        this.clazz = classInstance;
        this.superClass = superClass;
    }

    @Override
    public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
        script.variableTable.enterScope();

        int maxArgumentCount = Testing.server.getGameRules().getInt(TestingGamerules.MAX_ARGUMENT_COUNT);

        if (arguments.size() > maxArgumentCount) {
            throw new MaxArgumentError("Function '" + this.name + "' has more than " + maxArgumentCount + " argument(s)");
        }

        if (this.arguments.size() != arguments.size()) {
            throw new SyntaxError("Function '" + this.name + "' takes " + this.arguments.size() + " argument(s) but got " + arguments.size() + " argument(s)");
        }

        int argumentNumber = 0;

        for (IdentifierExpressionNode argumentName : this.arguments) {
            LiteralExpressionNode argumentValue = arguments.get(argumentNumber).interpret(script);
            script.variableTable.declare(true, argumentName, argumentValue);
            argumentNumber++;
        }

        if (this.clazz != null) {
            script.variableTable.declare(true, new IdentifierExpressionNode("this"), this.clazz);
        }

        if (this.superClass != null) {
            script.variableTable.declare(true, new IdentifierExpressionNode("super"), this.superClass);
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
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;

        FunctionExpressionNode that = (FunctionExpressionNode) o;

        if (!this.arguments.equals(that.arguments))
            return false;
        if (!this.name.equals(that.name))
            return false;
        return this.body.equals(that.body);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("function");
    }

    @Override
    public int hashCode() {
        int result = this.arguments.hashCode();
        result = 31 * result + this.name.hashCode();
        result = 31 * result + this.body.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "<function " + this.name + ">";
    }
}
