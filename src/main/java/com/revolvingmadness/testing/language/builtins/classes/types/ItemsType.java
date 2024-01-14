package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.ItemInstance;
import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.lexer.TokenType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;

public class ItemsType extends BuiltinType {
    public ItemsType() {
        super("Items");

        Registries.ITEM.forEach(this::registerItem);
        this.variableScope.declare(List.of(TokenType.CONST), "get", new Get());
    }

    private void registerItem(Item item) {
        String itemID = Registries.ITEM.getId(item).getPath();

        this.variableScope.declare(List.of(TokenType.CONST), itemID.toUpperCase(), new ItemInstance(item));
    }

    private static class Get extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("get", 1, arguments.size());
            }

            BuiltinClass identifierClass = arguments.get(0);

            if (!identifierClass.instanceOf(new ResourceType())) {
                throw ErrorHolder.argumentRequiresType(1, "get", new ResourceType(), identifierClass.getType());
            }

            Identifier identifier = identifierClass.toResource();

            if (Objects.equals(identifier, new Identifier("air"))) {
                return new ItemInstance(Items.AIR);
            }

            Item item = Registries.ITEM.get(identifier);

            if (item == Items.AIR) {
                throw new NameError("Item '" + identifier + "' does not exist");
            }

            return new ItemInstance(item);
        }
    }
}
