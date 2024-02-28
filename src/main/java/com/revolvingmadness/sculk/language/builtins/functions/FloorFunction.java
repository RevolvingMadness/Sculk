package com.revolvingmadness.sculk.language.builtins.functions;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.FloatInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.FloatClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.NumberClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public class FloorFunction extends BuiltinFunction {
    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("floor", arguments, List.of(NumberClassType.TYPE));

        BuiltinClass number = arguments.get(0);

        if (number.instanceOf(IntegerClassType.TYPE)) {
            return new FloatInstance(Math.floor(number.toInteger()));
        }

        if (number.instanceOf(FloatClassType.TYPE)) {
            return new FloatInstance(Math.floor(number.toFloat()));
        }

        throw ErrorHolder.argumentRequiresType(1, "floor", NumberClassType.TYPE, number.type);
    }
}
