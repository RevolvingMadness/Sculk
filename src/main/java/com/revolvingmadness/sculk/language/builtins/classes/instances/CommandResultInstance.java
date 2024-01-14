package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.CommandResultType;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class CommandResultInstance extends BuiltinClass {
    public final BuiltinClass result;
    public final BooleanInstance succeeded;
    public final BuiltinClass errorMessage;

    public CommandResultInstance(BuiltinClass result, BooleanInstance succeeded, BuiltinClass errorMessage) {
        this.result = result;
        this.succeeded = succeeded;
        this.errorMessage = errorMessage;

        this.variableScope.declare(List.of(TokenType.CONST), "result", this.result);
        this.variableScope.declare(List.of(TokenType.CONST), "succeeded", this.succeeded);
        this.variableScope.declare(List.of(TokenType.CONST), "errorMessage", this.errorMessage);
    }

    @Override
    public BuiltinType getType() {
        return new CommandResultType();
    }
}
