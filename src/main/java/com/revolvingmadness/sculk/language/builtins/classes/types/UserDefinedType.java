package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.UserDefinedInstance;
import com.revolvingmadness.sculk.language.errors.TypeError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.interpreter.VariableScope;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class UserDefinedType extends BuiltinType {
    public UserDefinedType(List<TokenType> accessModifiers, String name, BuiltinType superClass, VariableScope variableScope) {
        super(accessModifiers, name, superClass, variableScope);
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        UserDefinedInstance instance = new UserDefinedInstance(this, this.variableScope);

        if (this.isAbstract()) {
            throw new TypeError("Cannot instantiate abstract class '" + this.name + "'");
        }

        instance.call(interpreter, "init", arguments);

        return instance;
    }
}