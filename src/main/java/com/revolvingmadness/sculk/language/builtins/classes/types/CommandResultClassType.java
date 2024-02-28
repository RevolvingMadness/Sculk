package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.CommandResultInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.BooleanClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public class CommandResultClassType extends BuiltinClassType {
    public static final CommandResultClassType TYPE = new CommandResultClassType();

    private CommandResultClassType() {
        super("CommandResult");
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments, List.of(IntegerClassType.TYPE, BooleanClassType.TYPE, StringClassType.TYPE));

        BuiltinClass result = arguments.get(0);
        boolean succeeded = arguments.get(1).toBoolean();
        BuiltinClass errorMessage = arguments.get(2);

        return new CommandResultInstance(result, new BooleanInstance(succeeded), errorMessage);
    }
}
