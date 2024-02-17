package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class ObjectType extends BuiltinType {
    public static final ObjectType TYPE = new ObjectType();

    private ObjectType() {
        super("Object", null);

        this.typeVariableScope.declare(List.of(TokenType.CONST), "init", new Init());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "toString", new ToString());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "equals", new Equals());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "fromNBT", new FromNBT());
    }

    private static class Equals extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("equals", arguments, List.of(ObjectType.TYPE));

            BuiltinClass object = arguments.get(0);

            return new BooleanInstance(this.boundClass.equals(object));
        }
    }

    private static class FromNBT extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("fromNBT", arguments, List.of(ObjectType.TYPE));

            return this.boundClass.fromNBT(BuiltinClass.fromNbtElement(arguments.get(0).toNBT()));
        }
    }

    private static class Init extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("init", arguments);

            return new NullInstance();
        }
    }

    private static class ToString extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("toString", arguments);

            return new StringInstance(this.boundClass.toString());
        }
    }
}