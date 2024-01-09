package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.TypeType;
import com.revolvingmadness.testing.language.interpreter.VariableScope;

@SuppressWarnings("unused")
public class TypeInstance extends BuiltinClass {
    public final String typeName;
    public final VariableScope typeVariableScope;

    public TypeInstance(String name, VariableScope variableScope) {
        this.typeName = name;
        this.typeVariableScope = variableScope;
    }

    @Override
    public BuiltinType getType() {
        return new TypeType();
    }
}
