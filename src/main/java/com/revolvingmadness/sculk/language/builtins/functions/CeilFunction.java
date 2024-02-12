package com.revolvingmadness.sculk.language.builtins.functions;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.instances.FloatInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.FloatType;
import com.revolvingmadness.sculk.language.builtins.classes.types.IntegerType;
import com.revolvingmadness.sculk.language.errors.TypeError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public class CeilFunction extends BuiltinFunction {
    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("ceil", 1, arguments.size());
        }

        BuiltinClass object = arguments.get(0);

        if (object.instanceOf(IntegerType.TYPE)) {
            return new FloatInstance(Math.ceil(object.toInteger()));
        }

        if (object.instanceOf(FloatType.TYPE)) {
            return new FloatInstance(Math.ceil(object.toFloat()));
        }

        throw new TypeError("Function 'ceil' requires integer or float");
    }
}
