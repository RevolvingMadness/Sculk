package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.UserDefinedType;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.lexer.TokenType;

import java.util.List;

public class UserDefinedInstance extends BuiltinClass {
    public final UserDefinedType classType;

    public UserDefinedInstance(UserDefinedType classType, List<TokenType> accessModifiers, VariableScope variableScope) {
        super(accessModifiers, variableScope);
        this.classType = classType;
    }

    @Override
    public BuiltinType getType() {
        return this.classType;
    }
}
