package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.lexer.TokenType;

import java.util.List;

public class ItemType extends BuiltinType {
    public ItemType() {
        super("Item");
        this.typeVariableScope.declare(List.of(TokenType.CONST), "equalTo", new EqualTo());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getMaxCount", new GetMaxCount());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getMaxDamage", new GetMaxDamage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getName", new GetName());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isDamageable", new IsDamageable());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isFireproof", new IsFireproof());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isFood", new IsFood());
    }

    private static class EqualTo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("equalTo", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new ItemType())) {
                return new BooleanInstance(other.toItem().equals(this.boundClass.toItem()));
            }

            return new BooleanInstance(false);
        }
    }

    private static class GetMaxCount extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getMaxCount", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toItem().getMaxCount());
        }
    }

    private static class GetMaxDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getMaxDamage", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toItem().getMaxDamage());
        }
    }

    private static class GetName extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getName", 0, arguments.size());
            }

            return new StringInstance(this.boundClass.toItem().getName().getString());
        }
    }

    private static class IsDamageable extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isDamageable", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toItem().isDamageable());
        }
    }

    private static class IsFireproof extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isFireproof", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toItem().isFireproof());
        }
    }

    private static class IsFood extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isFood", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toItem().isFood());
        }
    }
}
