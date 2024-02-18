package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.*;
import com.revolvingmadness.sculk.language.errors.TypeError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

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

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments);

        return new InventoryInstance(new SimpleInventory(27));
    }

    @Override
    public BuiltinClass fromNBT(BuiltinClass nbtElement) {
        Inventory inventory = new SimpleInventory(27);

        if (!nbtElement.instanceOf(ListType.TYPE)) {
            throw new TypeError("Cannot de-serialize type '" + nbtElement.getType().name + "' to type '" + this.name + "'");
        }

        List<BuiltinClass> list = nbtElement.toList();

        list.forEach(slotClass -> {
            Map<BuiltinClass, BuiltinClass> slot = slotClass.toDictionary();

            long slotNumber = slot.get(new StringInstance("slot")).toInteger();

            Map<BuiltinClass, BuiltinClass> stack = slot.get(new StringInstance("stack")).toDictionary();

            long count = stack.get(new StringInstance("count")).toInteger();
            String id = stack.get(new StringInstance("id")).toString();

            Item item = Registries.ITEM.get(Identifier.tryParse(id));

            inventory.setStack((int) slotNumber, new ItemStack(item, (int) count));
        });

        return new InventoryInstance(inventory);
    }

    private static class Contains extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("contains", arguments, List.of(ItemType.TYPE));

            Item item = this.boundClass.toGUI().toItem();

            return new BooleanInstance(this.boundClass.toInventory().containsAny(java.util.Set.of(item)));
        }
    }

    private static class Count extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("count", arguments, List.of(ItemType.TYPE));

            Item item = arguments.get(0).toItem();

            return new IntegerInstance(this.boundClass.toInventory().count(item));
        }
    }

    private static class GetStack extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getStack", arguments, List.of(IntegerType.TYPE));

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
            this.validateCall("removeStack", arguments, List.of(IntegerType.TYPE));

            long slot = arguments.get(0).toInteger();

            return new ItemStackInstance(this.boundClass.toInventory().removeStack((int) slot));
        }
    }

    private static class SetStack extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("setStack", arguments, List.of(IntegerType.TYPE, ItemStackType.TYPE));

            long slot = arguments.get(0).toInteger();

            ItemStack stack = arguments.get(1).toItemStack();

            this.boundClass.toInventory().setStack((int) slot, stack);

            return new NullInstance();
        }
    }
}
