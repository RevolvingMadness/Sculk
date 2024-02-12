package com.revolvingmadness.sculk.language.builtins.functions;

import com.revolvingmadness.sculk.backend.Logger;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.StringType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.text.Text;

import java.util.List;

public class PrintFunction extends BuiltinFunction {
    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("print", 1, arguments.size());
        }

        BuiltinClass value = arguments.get(0);

        BuiltinClass stringClass = value.call(interpreter, "toString", List.of());

        if (!stringClass.instanceOf(new StringType())) {
            throw ErrorHolder.functionRequiresReturnType("print", new StringType(), stringClass.getType());
        }

        Logger.broadcast(Text.literal(stringClass.toString()));

        return new NullInstance();
    }
}
