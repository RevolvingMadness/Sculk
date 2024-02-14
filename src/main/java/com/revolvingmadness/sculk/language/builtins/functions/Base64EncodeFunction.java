package com.revolvingmadness.sculk.language.builtins.functions;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.StringType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.Base64;
import java.util.List;

public class Base64EncodeFunction extends BuiltinFunction {
    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validate("base64encode", arguments, List.of(StringType.TYPE));

        String value = arguments.get(0).toString();

        return new StringInstance(Base64.getEncoder().encodeToString(value.getBytes()));
    }
}
