package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.instances.InventoryInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.item.ItemStackInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.item.ItemClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.item.ItemStackClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class InventoryClassType extends BuiltinClassType {
    public static final InventoryClassType TYPE = new InventoryClassType();

    private InventoryClassType() {
        super("Inventory");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "contains", new Contains());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "count", new Count());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getStack", new GetStack());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isEmpty", new IsEmpty());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "removeStack", new RemoveStack());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setStack", new SetStack());
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments);

        return new InventoryInstance(new SimpleInventory(27));
    }

    private static class Contains extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("contains", arguments, List.of(ItemClassType.TYPE));

            Item item = this.boundClass.toGUI().toItem();

            return new BooleanInstance(this.boundClass.toInventory().containsAny(java.util.Set.of(item)));
        }
    }

    private static class Count extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("count", arguments, List.of(ItemClassType.TYPE));

            Item item = arguments.get(0).toItem();

            return new IntegerInstance(this.boundClass.toInventory().count(item));
        }
    }

    private static class GetStack extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getStack", arguments, List.of(IntegerClassType.TYPE));

            long slot = arguments.get(0).toInteger();

            return new ItemStackInstance(this.boundClass.toInventory().getStack((int) slot));
        }
    }

    private static class IsEmpty extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isEmpty", arguments);

            return new BooleanInstance(this.boundClass.toInventory().isEmpty());
        }
    }

    private static class RemoveStack extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("removeStack", arguments, List.of(IntegerClassType.TYPE));

            long slot = arguments.get(0).toInteger();

            return new ItemStackInstance(this.boundClass.toInventory().removeStack((int) slot));
        }
    }

    private static class SetStack extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("setStack", arguments, List.of(IntegerClassType.TYPE, ItemStackClassType.TYPE));

            long slot = arguments.get(0).toInteger();

            ItemStack stack = arguments.get(1).toItemStack();

            this.boundClass.toInventory().setStack((int) slot, stack);

            return new NullInstance();
        }
    }
}
