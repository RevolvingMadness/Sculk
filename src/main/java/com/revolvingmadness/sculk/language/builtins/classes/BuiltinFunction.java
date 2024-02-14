package com.revolvingmadness.sculk.language.builtins.classes;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.types.FunctionType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;
import java.util.ListIterator;

public abstract class BuiltinFunction extends BuiltinClass {
    public abstract BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments);

    @Override
    public BuiltinType getType() {
        return FunctionType.TYPE;
    }

    @Override
    public BuiltinFunction toFunction() {
        return this;
    }

    public void validate(String name, List<BuiltinClass> arguments, List<BuiltinType> argumentTypes) {
        if (arguments.size() != argumentTypes.size()) {
            throw ErrorHolder.invalidArgumentCount(name, argumentTypes.size(), arguments.size());
        }

        ListIterator<BuiltinClass> argumentsIterator = arguments.listIterator();

        while (argumentsIterator.hasNext()) {
            BuiltinClass value = argumentsIterator.next();
            BuiltinType type = argumentTypes.get(argumentsIterator.previousIndex());

            if (!value.instanceOf(type)) {
                throw ErrorHolder.argumentRequiresType(argumentsIterator.previousIndex(), name, value.getType(), type);
            }
        }
    }
}
