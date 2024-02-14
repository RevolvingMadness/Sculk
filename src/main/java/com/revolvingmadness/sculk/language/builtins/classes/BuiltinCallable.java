package com.revolvingmadness.sculk.language.builtins.classes;

import com.revolvingmadness.sculk.language.ErrorHolder;

import java.util.List;

public abstract class BuiltinCallable extends BuiltinClass {
    public void validate(String callableName, List<BuiltinClass> arguments, List<BuiltinType> argumentTypes) {
        if (arguments.size() != argumentTypes.size()) {
            throw ErrorHolder.invalidArgumentCount(callableName, argumentTypes.size(), arguments.size());
        }

        int argumentNumber = 0;

        for (BuiltinClass argument : arguments) {
            BuiltinType argumentType = argumentTypes.get(argumentNumber);

            if (!argument.instanceOf(argumentType)) {
                throw ErrorHolder.argumentRequiresType(argumentNumber + 1, callableName, argument.getType(), argumentType);
            }
        }
    }

    public void validate(String callableName, List<BuiltinClass> arguments) {
        this.validate(callableName, arguments, List.of());
    }
}
