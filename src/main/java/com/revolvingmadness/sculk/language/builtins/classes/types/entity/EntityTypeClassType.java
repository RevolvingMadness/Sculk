package com.revolvingmadness.sculk.language.builtins.classes.types.entity;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class EntityTypeClassType extends BuiltinClassType {
    public static final EntityTypeClassType TYPE = new EntityTypeClassType();

    private EntityTypeClassType() {
        super("EntityType");
    }
}
