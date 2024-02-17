package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class ItemType extends BuiltinType {
    public static final ItemType TYPE = new ItemType();

    private ItemType() {
        super("Item", ItemStackType.TYPE);
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getMaxCount", new GetMaxCount());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getMaxDamage", new GetMaxDamage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getName", new GetName());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isDamageable", new IsDamageable());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isFireproof", new IsFireproof());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isFood", new IsFood());
    }

    private static class GetMaxCount extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getMaxCount", arguments);

            return new IntegerInstance(this.boundClass.toItem().getMaxCount());
        }
    }

    private static class GetMaxDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getMaxDamage", arguments);

            return new IntegerInstance(this.boundClass.toItem().getMaxDamage());
        }
    }

    private static class GetName extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getName", arguments);

            return new StringInstance(this.boundClass.toItem().getName().getString());
        }
    }

    private static class IsDamageable extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isDamageable", arguments);

            return new BooleanInstance(this.boundClass.toItem().isDamageable());
        }
    }

    private static class IsFireproof extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isFireproof", arguments);

            return new BooleanInstance(this.boundClass.toItem().isFireproof());
        }
    }

    private static class IsFood extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isFood", arguments);

            return new BooleanInstance(this.boundClass.toItem().isFood());
        }
    }
}
