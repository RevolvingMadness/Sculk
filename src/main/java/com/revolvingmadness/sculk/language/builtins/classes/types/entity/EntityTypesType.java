package com.revolvingmadness.sculk.language.builtins.classes.types.entity;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.entity.EntityTypeInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringType;
import com.revolvingmadness.sculk.language.errors.NameError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;

public class EntityTypesType extends BuiltinType {
    public static final EntityTypesType TYPE = new EntityTypesType();

    private EntityTypesType() {
        super("EntityTypes");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "get", new Get());
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

            Optional<EntityType<?>> entityType = Registries.ENTITY_TYPE.getOrEmpty(identifier);

            if (entityType.isEmpty()) {
                throw new NameError("Entity type '" + identifier + "' does not exist");
            }

            return new EntityTypeInstance(entityType.get().isFireImmune());
        }
    }
}
