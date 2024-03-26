package com.revolvingmadness.sculk.language.builtins.classes.types.entity;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.entity.EntityTypeInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.errors.NameError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public class EntityTypesClassType extends BuiltinClassType {
    public static final EntityTypesClassType TYPE = new EntityTypesClassType();

    private EntityTypesClassType() {
        super("EntityTypes");

        try {
            this.addMethod("get", List.of(StringClassType.TYPE));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public BuiltinClass get(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        String identifierClass = arguments[0].toString();

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
