package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.CommandResultInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public class CommandResultType extends BuiltinType {
    public CommandResultType() {
        super("CommandResult");
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 3) {
            throw ErrorHolder.invalidArgumentCount("init", 3, arguments.size());
        }

        BuiltinClass result = arguments.get(0);
        BuiltinClass succeeded = arguments.get(1);
        BuiltinClass errorMessage = arguments.get(2);

        if (!result.instanceOf(new IntegerType())) {
            throw ErrorHolder.argumentRequiresType(1, "init", new IntegerType(), result.getType());
        }

        if (!succeeded.instanceOf(new BooleanType())) {
            throw ErrorHolder.argumentRequiresType(2, "init", new BooleanType(), succeeded.getType());
        }

        if (!errorMessage.instanceOf(new StringType())) {
            throw ErrorHolder.argumentRequiresType(3, "init", new StringType(), errorMessage.getType());
        }

        return new CommandResultInstance(result, new BooleanInstance(succeeded.toBoolean()), errorMessage);
    }
}
