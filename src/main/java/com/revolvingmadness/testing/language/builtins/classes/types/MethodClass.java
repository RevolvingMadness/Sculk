package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.gamerules.TestingGamerules;
import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.BaseMethodExpressionNode;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.errors.MaxArgumentError;
import com.revolvingmadness.testing.language.interpreter.errors.Return;
import com.revolvingmadness.testing.language.lexer.TokenType;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.StatementNode;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.Objects;

public class MethodClass extends BaseMethodExpressionNode {
    public final List<TokenType> accessModifiers;
    public final List<String> arguments;
    public final List<StatementNode> body;
    public final boolean isConstant;
    public final String name;

    public MethodClass(List<TokenType> accessModifiers, boolean isConstant, String name, List<String> arguments, List<StatementNode> body) {
        this.accessModifiers = accessModifiers;
        this.isConstant = isConstant;
        this.name = name;
        this.arguments = arguments;
        this.body = body;
    }

    @Override
    public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
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
            BaseClassExpressionNode argumentValue = arguments.get(argumentNumber);
            interpreter.variableTable.declare(true, argumentName, argumentValue);
            argumentNumber++;
        }

        if (this.boundClass != null) {
            interpreter.variableTable.declare(true, "this", this.boundClass);
        }

        if (this.boundSuperClass != null) {
            interpreter.variableTable.declare(true, "super", this.boundSuperClass);
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        MethodClass that = (MethodClass) o;
        return Objects.equals(this.arguments, that.arguments) && Objects.equals(this.body, that.body) && Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.arguments, this.body, this.name);
    }

    @Override
    public String toString() {
        throw new NotImplementedException();
    }
}
