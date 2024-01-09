package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.UserDefinedType;
import com.revolvingmadness.testing.language.interpreter.VariableScope;

public class UserDefinedInstance extends BuiltinClass {
    public final String className;
    public final UserDefinedType classType;

    public UserDefinedInstance(UserDefinedType classType, String name, VariableScope variableScope) {
        super(variableScope);
        this.classType = classType;
        this.className = name;
    }

    @Override
    public BuiltinType getType() {
        return this.classType;
    }
}
