package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.ItemStackInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class InventoryType extends BuiltinType {
    public static final InventoryType TYPE = new InventoryType();

    private InventoryType() {
        super("Inventory");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "contains", new Contains());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "count", new Count());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getStack", new GetStack());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isEmpty", new IsEmpty());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "removeStack", new RemoveStack());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setStack", new SetStack());
    }

    private static class Contains extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("contains", arguments, List.of(ItemType.TYPE));

            Item item = this.boundClass.toGUI().toItem();

            return new BooleanInstance(this.boundClass.toInventory().containsAny(java.util.Set.of(item)));
        }
    }

    private static class Count extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("count", arguments, List.of(ItemType.TYPE));

            Item item = arguments.get(0).toItem();

            return new IntegerInstance(this.boundClass.toInventory().count(item));
        }
    }

    private static class GetStack extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getStack", arguments, List.of(IntegerType.TYPE));

            long slot = arguments.get(0).toInteger();

            return new ItemStackInstance(this.boundClass.toInventory().getStack((int) slot));
        }
    }

    private static class IsEmpty extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isEmpty", arguments);

            return new BooleanInstance(this.boundClass.toInventory().isEmpty());
        }
    }

    private static class RemoveStack extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("removeStack", arguments, List.of(IntegerType.TYPE));

            long slot = arguments.get(0).toInteger();

            return new ItemStackInstance(this.boundClass.toInventory().removeStack((int) slot));
        }
    }

    private static class SetStack extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setStack", arguments, List.of(IntegerType.TYPE, ItemStackType.TYPE));

            long slot = arguments.get(0).toInteger();

            ItemStack stack = arguments.get(1).toItemStack();

            this.boundClass.toInventory().setStack((int) slot, stack);

            return new NullInstance();
        }
    }
}
