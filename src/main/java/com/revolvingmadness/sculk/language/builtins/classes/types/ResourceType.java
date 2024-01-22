package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.ResourceInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public class ResourceType extends BuiltinType {
    public ResourceType() {
        super("Resource");
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("init", 1, arguments.size());
        }

        BuiltinClass resourceClass = arguments.get(0);

        if (!resourceClass.instanceOf(new ResourceType())) {
            throw ErrorHolder.argumentRequiresType(1, "init", new ResourceType(), resourceClass.getType());
        }

        return new ResourceInstance(resourceClass.toResource());
    }
}
