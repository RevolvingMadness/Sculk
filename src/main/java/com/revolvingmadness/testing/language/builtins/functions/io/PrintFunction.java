package com.revolvingmadness.testing.language.builtins.functions.io;

import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.testing.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.interpreter.Interpreter;

import java.util.List;

public class PrintFunction extends BuiltinFunction {
    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("print", 1, arguments.size());
        }

        BuiltinClass value = arguments.get(0);
        BuiltinClass toStringResult = value.call(interpreter, "toString", List.of());

        Logger.broadcast(toStringResult.toStringType(), true);

        return new NullInstance();
    }

}
