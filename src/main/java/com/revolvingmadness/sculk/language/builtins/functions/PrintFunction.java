package com.revolvingmadness.sculk.language.builtins.functions;

import com.revolvingmadness.sculk.backend.Logger;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.instances.NullInstance;
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

        Logger.broadcast(Text.literal(value.toString()));

        return new NullInstance();
    }
}
