package com.revolvingmadness.sculk.language.builtins.functions;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.StringType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.Base64;
import java.util.List;

public class Base64DecodeFunction extends BuiltinFunction {
    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("base64decode", 1, arguments.size());
        }

        BuiltinClass value = arguments.get(0);

        if (!value.instanceOf(StringType.TYPE)) {
            throw ErrorHolder.argumentRequiresType(1, "base64decode", StringType.TYPE, value.getType());
        }

        return new StringInstance(new String(Base64.getDecoder().decode(value.toString())));
    }
}
