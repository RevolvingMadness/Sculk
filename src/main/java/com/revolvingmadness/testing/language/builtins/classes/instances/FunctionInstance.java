package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.gamerules.TestingGamerules;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.errors.MaxArgumentError;
import com.revolvingmadness.testing.language.interpreter.errors.Return;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.StatementNode;

import java.util.List;

public class FunctionInstance extends BuiltinFunction {
    public final List<String> arguments;
    public final List<StatementNode> body;
    public final String name;

    public FunctionInstance(String name, List<String> arguments, List<StatementNode> body) {
        this.name = name;
        this.arguments = arguments;
        this.body = body;
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        interpreter.variableTable.enterScope();

        int maxArguments = Testing.server.getGameRules().getInt(TestingGamerules.MAX_ARGUMENTS);

        if (arguments.size() > maxArguments) {
            throw new MaxArgumentError("Function '" + this.name + "' has more than " + maxArguments + " argument(s)");
        }

        if (this.arguments.size() != arguments.size()) {
            throw ErrorHolder.invalidArgumentCount(this.name, this.arguments.size(), arguments.size());
        }

        int argumentNumber = 0;

        for (String argumentName : this.arguments) {
            BuiltinClass argumentValue = arguments.get(argumentNumber);
            interpreter.variableTable.declare(true, argumentName, argumentValue);
            argumentNumber++;
        }

        try {
            this.body.forEach(interpreter::visitStatement);
        } catch (Return returnException) {
            interpreter.variableTable.exitScope();
            return returnException.value;
        }

        interpreter.variableTable.exitScope();

        return new NullInstance();
    }
}
