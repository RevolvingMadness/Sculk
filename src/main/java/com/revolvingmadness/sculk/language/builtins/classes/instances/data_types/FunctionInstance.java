package com.revolvingmadness.sculk.language.builtins.classes.instances.data_types;

import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.language.Argument;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.errors.TypeError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.interpreter.errors.MaxArgumentError;
import com.revolvingmadness.sculk.language.interpreter.errors.Return;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import com.revolvingmadness.sculk.language.parser.nodes.statement_nodes.StatementNode;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class FunctionInstance extends BuiltinFunction {
    public final List<Argument> arguments;
    public final List<StatementNode> body;
    public final String name;
    public final BuiltinClassType returnType;

    public FunctionInstance(String name, List<Argument> arguments, BuiltinClassType returnType, List<StatementNode> body) {
        this.name = name;
        this.arguments = arguments;
        this.returnType = returnType;
        this.body = body;
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        interpreter.variableTable.enterScope();

        int maxArguments = Sculk.getMaxArgumentCount();

        if (arguments.size() > maxArguments) {
            throw new MaxArgumentError("Function '" + this.name + "' has more than " + maxArguments + " argument(s)");
        }

        if (this.arguments.size() != arguments.size()) {
            throw ErrorHolder.invalidArgumentCount(this.name, arguments.size(), this.arguments.size());
        }

        ListIterator<Argument> argumentIterator = this.arguments.listIterator();

        while (argumentIterator.hasNext()) {
            Argument argument = argumentIterator.next();
            BuiltinClass typeClass = interpreter.variableTable.getOrThrow(argument.type).value;
            BuiltinClass value = arguments.get(argumentIterator.previousIndex());

            if (!(typeClass instanceof BuiltinClassType type_)) {
                throw new TypeError("The type of an argument cannot be an instance");
            }

            interpreter.variableTable.declare(List.of(TokenType.CONST), type_, argument.name, value);
        }

        try {
            this.body.forEach(interpreter::visitStatement);
        } catch (Return returnException) {
            BuiltinClass value = returnException.value;

            if (!value.instanceOf(this.returnType)) {
                throw ErrorHolder.functionRequiresReturnType(this.name, value.type, this.returnType);
            }

            interpreter.variableTable.exitScope();
            return value;
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
        FunctionInstance that = (FunctionInstance) o;
        return Objects.equals(this.arguments, that.arguments) && Objects.equals(this.body, that.body) && Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.arguments, this.body, this.name);
    }
}
