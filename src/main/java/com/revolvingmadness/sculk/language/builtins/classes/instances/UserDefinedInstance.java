package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.UserDefinedClassType;
import com.revolvingmadness.sculk.language.interpreter.VariableScope;

import java.util.Objects;

public class UserDefinedInstance extends BuiltinClass {
    public final UserDefinedClassType classType;

    public UserDefinedInstance(UserDefinedClassType classType, VariableScope variableScope) {
        super(classType, variableScope);
        this.classType = classType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        UserDefinedInstance that = (UserDefinedInstance) o;
        return Objects.equals(this.classType, that.classType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.classType);
    }
}
