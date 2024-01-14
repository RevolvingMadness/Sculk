package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class BooleanType extends BuiltinType {
    public BooleanType() {
        super("Boolean");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "toString", new ToString());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "logicalNot", new LogicalNot());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "booleanAnd", new BooleanAnd());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "booleanOr", new BooleanOr());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "equalTo", new EqualTo());
    }

    private static class EqualTo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("equalTo", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new BooleanType())) {
                return new BooleanInstance(other.toBoolean() == this.boundClass.toBoolean());
            }

            return new BooleanInstance(false);
        }
    }

    private static class BooleanAnd extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("booleanAnd", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (!other.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "booleanAnd", new BooleanType(), other.getType());
            }

            return new BooleanInstance(this.boundClass.toBoolean() && other.toBoolean());
        }
    }

    private static class BooleanOr extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("booleanOr", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (!other.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "booleanOr", new BooleanType(), other.getType());
            }

            return new BooleanInstance(this.boundClass.toBoolean() || other.toBoolean());
        }
    }

    private static class LogicalNot extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("logicalNot", 0, arguments.size());
            }

            return new BooleanInstance(!this.boundClass.toBoolean());
        }
    }

    private static class ToString extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("toString", 0, arguments.size());
            }

            return new StringInstance(String.valueOf(this.boundClass.toBoolean()));
        }
    }
}
