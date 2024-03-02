package com.revolvingmadness.sculk.language.builtins.classes.types.item;

import com.revolvingmadness.sculk.dynamicreg.DynamicRegistries;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.ScriptTag;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.instances.item.ItemInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.errors.NameError;
import com.revolvingmadness.sculk.language.errors.SyntaxError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;

public class ItemsClassType extends BuiltinClassType {
    public static final ItemsClassType TYPE = new ItemsClassType();

    private ItemsClassType() {
        super("Items");

        this.variableScope.declare(List.of(TokenType.CONST), "get", new Get());
        this.variableScope.declare(List.of(TokenType.CONST), "register", new Register());
    }

    private static class Get extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("get", arguments, List.of(StringClassType.TYPE));

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

    private static class Register extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("register", arguments, List.of(StringClassType.TYPE, ItemClassType.TYPE));

            if (interpreter.scriptTag != ScriptTag.START) {
                throw new SyntaxError("Items can only be registered in the 'start' script tag");
            }

            ItemInstance item = arguments.get(1).toItemInstance();

            DynamicRegistries.ITEM.register(item.id, item.value);

            return item;
        }
    }
}
