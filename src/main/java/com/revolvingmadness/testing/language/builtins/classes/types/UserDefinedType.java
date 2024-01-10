package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.UserDefinedInstance;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.VariableScope;

import java.util.List;

public class UserDefinedType extends BuiltinType {
    public UserDefinedType(String name, BuiltinType superClass, VariableScope variableScope) {
        super(name, superClass, variableScope);
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        UserDefinedInstance instance = new UserDefinedInstance(this, this.typeName, this.variableScope);

        instance.call(interpreter, "init", arguments);

        return instance;
    }
}