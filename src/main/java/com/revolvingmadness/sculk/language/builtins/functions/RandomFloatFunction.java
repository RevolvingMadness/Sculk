package com.revolvingmadness.sculk.language.builtins.functions;

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
        this.validate("randomFloat", arguments, List.of(FloatType.TYPE, FloatType.TYPE));

        double min = arguments.get(0).toFloat();
        double max = arguments.get(1).toFloat();

        Random random = new Random();

        double range = max - min + 1;
        return new FloatInstance(random.nextDouble(range) + min);
    }
}
