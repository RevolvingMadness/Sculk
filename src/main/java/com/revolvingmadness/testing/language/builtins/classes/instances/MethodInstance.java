package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.gamerules.TestingGamerules;
import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.errors.MaxArgumentError;
import com.revolvingmadness.testing.language.interpreter.errors.Return;
import com.revolvingmadness.testing.language.lexer.TokenType;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.StatementNode;

import java.util.List;
import java.util.Objects;

public class MethodInstance extends BuiltinMethod {
    public final List<TokenType> accessModifiers;
    public final List<String> arguments;
    public final List<StatementNode> body;
    public final boolean isConstant;
    public final String name;

    public MethodInstance(List<TokenType> accessModifiers, boolean isConstant, String name, List<String> arguments, List<StatementNode> body) {
        this.isConstant = isConstant;
        this.accessModifiers = accessModifiers;
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

        return new NullInstance();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        MethodInstance that = (MethodInstance) o;
        return this.isConstant == that.isConstant && Objects.equals(this.accessModifiers, that.accessModifiers) && Objects.equals(this.arguments, that.arguments) && Objects.equals(this.body, that.body) && Objects.equals(this.name, that.name) && Objects.equals(this.boundClass, that.boundClass) && Objects.equals(this.boundSuperClass, that.boundSuperClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.accessModifiers, this.arguments, this.body, this.isConstant, this.name, this.boundClass, this.boundSuperClass);
    }
}
