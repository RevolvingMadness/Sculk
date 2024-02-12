package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.CommandResultInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public class CommandResultType extends BuiltinType {
    public static final CommandResultType TYPE = new CommandResultType();

    private CommandResultType() {
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

        if (!result.instanceOf(IntegerType.TYPE)) {
            throw ErrorHolder.argumentRequiresType(1, "init", IntegerType.TYPE, result.getType());
        }

        if (!succeeded.instanceOf(BooleanType.TYPE)) {
            throw ErrorHolder.argumentRequiresType(2, "init", BooleanType.TYPE, succeeded.getType());
        }

        if (!errorMessage.instanceOf(StringType.TYPE)) {
            throw ErrorHolder.argumentRequiresType(3, "init", StringType.TYPE, errorMessage.getType());
        }

        return new CommandResultInstance(result, new BooleanInstance(succeeded.toBoolean()), errorMessage);
    }
}
