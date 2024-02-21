package com.revolvingmadness.sculk.language.builtins.functions;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.ObjectType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public class TypeFunction extends BuiltinFunction {
    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("type", arguments, List.of(ObjectType.TYPE));

        BuiltinClass object = arguments.get(0);
        BuiltinType type = object.getType();

        return new StringInstance(type.name);
    }
}
