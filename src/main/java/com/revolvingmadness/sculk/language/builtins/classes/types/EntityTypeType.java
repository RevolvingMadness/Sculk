package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class EntityTypeType extends BuiltinType {
    public static final EntityTypeType TYPE = new EntityTypeType();

    private EntityTypeType() {
        super("EntityType");
    }
}
