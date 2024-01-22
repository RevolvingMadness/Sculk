package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.FloatInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public class FloatType extends BuiltinType {
    public FloatType() {
        super("Float", new IntegerType());
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("init", 1, arguments.size());
        }

        BuiltinClass floatClass = arguments.get(0);

        if (!floatClass.instanceOf(new FloatType())) {
            throw ErrorHolder.argumentRequiresType(1, "init", new FloatType(), floatClass.getType());
        }

        return new FloatInstance(floatClass.toFloat());
    }
}
