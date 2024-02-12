package com.revolvingmadness.sculk.language.builtins.functions;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.instances.FloatInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.FloatType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;
import java.util.Random;

public class RandomFloatFunction extends BuiltinFunction {
    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 2) {
            throw ErrorHolder.invalidArgumentCount("randomFloat", 2, arguments.size());
        }

        BuiltinClass min = arguments.get(0);
        BuiltinClass max = arguments.get(1);

        if (!min.instanceOf(FloatType.TYPE)) {
            throw ErrorHolder.argumentRequiresType(1, "randomFloat", FloatType.TYPE, min.getType());
        }

        if (!max.instanceOf(FloatType.TYPE)) {
            throw ErrorHolder.argumentRequiresType(1, "randomFloat", FloatType.TYPE, max.getType());
        }

        Random random = new Random();

        double range = max.toFloat() - min.toFloat() + 1;
        return new FloatInstance(random.nextDouble(range) + min.toFloat());
    }
}
