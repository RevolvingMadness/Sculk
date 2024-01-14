package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.gamerules.SculkGamerules;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.interpreter.errors.MaxArgumentError;
import com.revolvingmadness.sculk.language.interpreter.errors.Return;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import com.revolvingmadness.sculk.language.parser.nodes.statement_nodes.StatementNode;

import java.util.List;
import java.util.Objects;

public class MethodInstance extends BuiltinMethod {
    public final List<TokenType> accessModifiers;
    public final List<String> arguments;
    public final List<StatementNode> body;
    public final String name;

    public MethodInstance(List<TokenType> accessModifiers, String name, List<String> arguments, List<StatementNode> body) {
        this.accessModifiers = accessModifiers;
        this.name = name;
        this.arguments = arguments;
        this.body = body;
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        interpreter.variableTable.enterScope();

        int maxArguments = Sculk.server.getGameRules().getInt(SculkGamerules.MAX_ARGUMENTS);

        if (arguments.size() > maxArguments) {
            throw new MaxArgumentError("Function '" + this.name + "' has more than " + maxArguments + " argument(s)");
        }

        if (this.arguments.size() != arguments.size()) {
            throw ErrorHolder.invalidArgumentCount(this.name, this.arguments.size(), arguments.size());
        }

        int argumentNumber = 0;

        for (String argumentName : this.arguments) {
            BuiltinClass argumentValue = arguments.get(argumentNumber);
            interpreter.variableTable.declare(List.of(TokenType.CONST), argumentName, argumentValue);
            argumentNumber++;
        }

        if (this.boundClass != null) {
            interpreter.variableTable.declare(List.of(TokenType.CONST), "this", this.boundClass);
        }

        if (this.boundSuperClass != null) {
            interpreter.variableTable.declare(List.of(TokenType.CONST), "super", this.boundSuperClass);
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
        return Objects.equals(this.accessModifiers, that.accessModifiers) && Objects.equals(this.arguments, that.arguments) && Objects.equals(this.body, that.body) && Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.accessModifiers, this.arguments, this.body, this.name);
    }
}
