package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.TypeType;
import com.revolvingmadness.sculk.language.interpreter.VariableScope;

import java.util.Objects;

@SuppressWarnings("unused")
public class TypeInstance extends BuiltinClass {
    public final String typeName;
    public final VariableScope typeVariableScope;

    public TypeInstance(String name, VariableScope variableScope) {
        this.typeName = name;
        this.typeVariableScope = variableScope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        TypeInstance that = (TypeInstance) o;
        return Objects.equals(this.typeName, that.typeName) && Objects.equals(this.typeVariableScope, that.typeVariableScope);
    }

    @Override
    public BuiltinType getType() {
        return TypeType.TYPE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.typeName, this.typeVariableScope);
    }
}
