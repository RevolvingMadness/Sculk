package com.revolvingmadness.sculk.language.builtins.functions;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;
import java.util.Random;

public class RandomIntegerFunction extends BuiltinFunction {
    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("randomInteger", arguments, List.of(IntegerClassType.TYPE, IntegerClassType.TYPE));

        long min = arguments.get(0).toInteger();
        long max = arguments.get(1).toInteger();

        Random random = new Random();

        long range = max - min + 1;
        return new IntegerInstance(random.nextLong(range) + min);
    }
}
