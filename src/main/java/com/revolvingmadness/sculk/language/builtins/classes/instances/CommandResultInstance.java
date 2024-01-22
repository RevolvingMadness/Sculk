package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.CommandResultType;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;
import java.util.Objects;

public class CommandResultInstance extends BuiltinClass {
    public final BuiltinClass errorMessage;
    public final BuiltinClass result;
    public final BooleanInstance succeeded;

    public CommandResultInstance(BuiltinClass result, BooleanInstance succeeded, BuiltinClass errorMessage) {
        this.result = result;
        this.succeeded = succeeded;
        this.errorMessage = errorMessage;

        this.variableScope.declare(List.of(TokenType.CONST), "result", this.result);
        this.variableScope.declare(List.of(TokenType.CONST), "succeeded", this.succeeded);
        this.variableScope.declare(List.of(TokenType.CONST), "errorMessage", this.errorMessage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        CommandResultInstance that = (CommandResultInstance) o;
        return Objects.equals(this.result, that.result) && Objects.equals(this.succeeded, that.succeeded) && Objects.equals(this.errorMessage, that.errorMessage);
    }

    @Override
    public BuiltinType getType() {
        return new CommandResultType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.result, this.succeeded, this.errorMessage);
    }
}
