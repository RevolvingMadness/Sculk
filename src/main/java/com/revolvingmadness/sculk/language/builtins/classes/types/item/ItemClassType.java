package com.revolvingmadness.sculk.language.builtins.classes.types.item;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.item.ItemInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ItemClassType extends BuiltinClassType {
    public static final ItemClassType TYPE = new ItemClassType();

    private ItemClassType() {
        super("Item", ItemStackClassType.TYPE);
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getMaxCount", new GetMaxCount());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getMaxDamage", new GetMaxDamage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getName", new GetName());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isDamageable", new IsDamageable());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isFireproof", new IsFireproof());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isFood", new IsFood());
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments, List.of(StringClassType.TYPE, ItemSettingsClassType.TYPE));

        BuiltinClass settings = arguments.get(1);

        return new ItemInstance(new Identifier(interpreter.identifier.getNamespace(), arguments.get(0).toString()), new Item(settings.toItemSettings()) {
            @Override
            public Text getName() {
                return Text.literal(settings.variableScope.getOrThrow("name").value.toString());
            }
        });
    }

    private static class GetMaxCount extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getMaxCount", arguments);

            return new IntegerInstance(this.boundClass.toItem().getMaxCount());
        }
    }

    private static class GetMaxDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getMaxDamage", arguments);

            return new IntegerInstance(this.boundClass.toItem().getMaxDamage());
        }
    }

    private static class GetName extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getName", arguments);

            return new StringInstance(this.boundClass.toItem().getName().getString());
        }
    }

    private static class IsDamageable extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isDamageable", arguments);

            return new BooleanInstance(this.boundClass.toItem().isDamageable());
        }
    }

    private static class IsFireproof extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isFireproof", arguments);

            return new BooleanInstance(this.boundClass.toItem().isFireproof());
        }
    }

    private static class IsFood extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isFood", arguments);

            return new BooleanInstance(this.boundClass.toItem().isFood());
        }
    }
}
