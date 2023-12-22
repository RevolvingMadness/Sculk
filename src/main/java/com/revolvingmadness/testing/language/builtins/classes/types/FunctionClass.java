package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.gamerules.TestingGamerules;
import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.errors.MaxArgumentError;
import com.revolvingmadness.testing.language.interpreter.errors.Return;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.StatementNode;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public class FunctionClass extends BaseClassExpressionNode {
    public final List<IdentifierExpressionNode> arguments;
    public final List<StatementNode> body;
    public final IdentifierExpressionNode name;
    public BaseClassExpressionNode clazz;
    public BaseClassExpressionNode superClass;

    public FunctionClass(IdentifierExpressionNode name, List<IdentifierExpressionNode> arguments, List<StatementNode> body) {
        this.name = name;
        this.arguments = arguments;
        this.body = body;
    }

    public void bind(BaseClassExpressionNode clazz, BaseClassExpressionNode superClass) {
        this.clazz = clazz;
        this.superClass = superClass;
    }

    @Override
    public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
        interpreter.variableTable.enterScope();

        int maxArguments = Testing.server.getGameRules().getInt(TestingGamerules.MAX_ARGUMENTS);

        if (arguments.size() > maxArguments) {
            throw new MaxArgumentError("Function '" + this.name + "' has more than " + maxArguments + " argument(s)");
        }

        if (this.arguments.size() != arguments.size()) {
            throw new SyntaxError("Function '" + this.name + "' takes " + this.arguments.size() + " argument(s) but got " + arguments.size() + " argument(s)");
        }

        int argumentNumber = 0;

        for (IdentifierExpressionNode argumentName : this.arguments) {
            BaseClassExpressionNode argumentValue = arguments.get(argumentNumber);
            interpreter.variableTable.declare(true, argumentName, argumentValue);
            argumentNumber++;
        }

        if (this.clazz != null) {
            interpreter.variableTable.declare(true, new IdentifierExpressionNode("this"), this.clazz);
        }

        if (this.superClass != null) {
            interpreter.variableTable.declare(true, new IdentifierExpressionNode("super"), this.superClass);
        }

        try {
            this.body.forEach(interpreter::visitStatement);
        } catch (Return returnException) {
            interpreter.variableTable.exitScope();
            return returnException.value;
        }

        interpreter.variableTable.exitScope();

        return new NullClass();
    }

    @Override
    public String getType() {
        return "Function";
    }

    @Override
    public String toString() {
        throw new NotImplementedException();
    }
}
