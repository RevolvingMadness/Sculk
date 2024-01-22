package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public class BooleanType extends BuiltinType {
    public BooleanType() {
        super("Boolean");
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("init", 1, arguments.size());
        }

        BuiltinClass booleanClass = arguments.get(0);

        if (!booleanClass.instanceOf(new BooleanType())) {
            throw ErrorHolder.argumentRequiresType(1, "init", new BooleanType(), booleanClass.getType());
        }

        return new BooleanInstance(booleanClass.toBoolean());
    }
}
