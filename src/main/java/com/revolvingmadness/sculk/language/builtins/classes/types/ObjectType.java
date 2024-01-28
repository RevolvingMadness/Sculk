package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class ObjectType extends BuiltinType {
    public ObjectType() {
        super("Object", null);

        this.typeVariableScope.declare(List.of(TokenType.CONST), "init", new Init());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "toString", new ToString());
    }

    private static class Init extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            return new NullInstance();
        }
    }

    private static class ToString extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("toString", 0, arguments.size());
            }

            return this.boundClass.toStringMethod();
        }
    }
}