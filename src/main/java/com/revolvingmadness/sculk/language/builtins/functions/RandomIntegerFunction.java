package com.revolvingmadness.sculk.language.builtins.functions;

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
        this.validate("randomInteger", arguments, List.of(IntegerType.TYPE, IntegerType.TYPE));

        long min = arguments.get(0).toInteger();
        long max = arguments.get(1).toInteger();

        Random random = new Random();

        long range = max - min + 1;
        return new IntegerInstance(random.nextLong(range) + min);
    }
}
