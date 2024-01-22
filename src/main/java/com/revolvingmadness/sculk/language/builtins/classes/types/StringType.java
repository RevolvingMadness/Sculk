package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public class StringType extends BuiltinType {
    public StringType() {
        super("String");
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("init", 1, arguments.size());
        }

        BuiltinClass stringClass = arguments.get(0);

        if (!stringClass.instanceOf(new StringType())) {
            throw ErrorHolder.argumentRequiresType(1, "init", new StringType(), stringClass.getType());
        }

        return stringClass.toStringMethod();
    }
}
