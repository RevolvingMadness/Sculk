package com.revolvingmadness.sculk.language.builtins.functions;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.instances.FloatInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.FloatType;
import com.revolvingmadness.sculk.language.builtins.classes.types.IntegerType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public class AbsFunction extends BuiltinFunction {
    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("abs", 1, arguments.size());
        }

        BuiltinClass object = arguments.get(0);

        if (object.instanceOf(new IntegerType())) {
            return new IntegerInstance(Math.abs(object.toInteger()));
        }

        if (object.instanceOf(new FloatType())) {
            return new FloatInstance(Math.abs(object.toFloat()));
        }

        throw ErrorHolder.argumentRequiresType(1, "abs", new IntegerType(), object.getType());
    }
}
