package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.ItemInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringType;
import com.revolvingmadness.sculk.language.errors.NameError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;

public class ItemsType extends BuiltinType {
    public static final ItemsType TYPE = new ItemsType();

    private ItemsType() {
        super("Items");

        this.variableScope.declare(List.of(TokenType.CONST), "get", new Get());
    }

    private static class Get extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("get", arguments, List.of(StringType.TYPE));

            String identifierClass = arguments.get(0).toString();

            Identifier identifier = Identifier.tryParse(identifierClass);

            if (identifier == null) {
                throw ErrorHolder.invalidIdentifier(identifierClass);
            }

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
