package com.revolvingmadness.sculk.language.builtins.functions;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.IntegerType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;
import java.util.Random;

public class RandomIntegerFunction extends BuiltinFunction {
    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 2) {
            throw ErrorHolder.invalidArgumentCount("randomInteger", 2, arguments.size());
        }

        BuiltinClass min = arguments.get(0);
        BuiltinClass max = arguments.get(1);

        if (!min.instanceOf(new IntegerType())) {
            throw ErrorHolder.argumentRequiresType(1, "randomInteger", new IntegerType(), min.getType());
        }

        if (!max.instanceOf(new IntegerType())) {
            throw ErrorHolder.argumentRequiresType(1, "randomInteger", new IntegerType(), max.getType());
        }

        Random random = new Random();

        long range = max.toInteger() - min.toInteger() + 1;
        return new IntegerInstance(random.nextLong(range) + min.toInteger());
    }
}
