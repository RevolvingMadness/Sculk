package com.revolvingmadness.sculk.language.builtins.functions;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.StringType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import org.apache.commons.codec.binary.Base64;

import java.util.List;

public class Base64EncodeFunction extends BuiltinFunction {
    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("base64encode", 1, arguments.size());
        }

        BuiltinClass value = arguments.get(0);

        if (!value.instanceOf(new StringType())) {
            throw ErrorHolder.argumentRequiresType(1, "base64encode", new StringType(), value.getType());
        }

        return new StringInstance(Base64.encodeBase64String(value.toString().getBytes()));
    }
}
