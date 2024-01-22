package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public class IntegerType extends BuiltinType {
    public IntegerType() {
        super("Integer");
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("init", 1, arguments.size());
        }

        BuiltinClass integerClass = arguments.get(0);

        if (!integerClass.instanceOf(new IntegerType())) {
            throw ErrorHolder.argumentRequiresType(1, "init", new IntegerType(), integerClass.getType());
        }

        return new IntegerInstance(integerClass.toInteger());
    }
}
