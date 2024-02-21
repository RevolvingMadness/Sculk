package com.revolvingmadness.sculk.language.builtins.functions;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.FloatInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.FloatType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.NumberType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public class AbsFunction extends BuiltinFunction {
    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("abs", arguments, List.of(NumberType.TYPE));

        BuiltinClass number = arguments.get(0);

        if (number.instanceOf(IntegerType.TYPE)) {
            return new IntegerInstance(Math.abs(number.toInteger()));
        }

        if (number.instanceOf(FloatType.TYPE)) {
            return new FloatInstance(Math.abs(number.toFloat()));
        }

        throw ErrorHolder.argumentRequiresType(1, "abs", NumberType.TYPE, number.type);
    }
}
