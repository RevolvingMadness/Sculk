package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.CommandResultInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.BooleanType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public class CommandResultType extends BuiltinType {
    public static final CommandResultType TYPE = new CommandResultType();

    private CommandResultType() {
        super("CommandResult");
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments, List.of(IntegerType.TYPE, BooleanType.TYPE, StringType.TYPE));

        BuiltinClass result = arguments.get(0);
        boolean succeeded = arguments.get(1).toBoolean();
        BuiltinClass errorMessage = arguments.get(2);

        return new CommandResultInstance(result, new BooleanInstance(succeeded), errorMessage);
    }
}
