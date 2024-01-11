package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.UserDefinedType;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.lexer.TokenType;

import java.util.List;
import java.util.Objects;

public class UserDefinedInstance extends BuiltinClass {
    public final UserDefinedType classType;

    public UserDefinedInstance(UserDefinedType classType, List<TokenType> accessModifiers, VariableScope variableScope) {
        super(accessModifiers, variableScope);
        this.classType = classType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        UserDefinedInstance that = (UserDefinedInstance) o;
        return Objects.equals(classType, that.classType);
    }

    @Override
    public BuiltinType getType() {
        return this.classType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(classType);
    }
}
