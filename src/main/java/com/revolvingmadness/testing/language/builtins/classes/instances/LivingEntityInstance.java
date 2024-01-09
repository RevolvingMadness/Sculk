package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.LivingEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

@SuppressWarnings("unused")
public class LivingEntityInstance extends BuiltinClass {
    public final LivingEntity value;

    public LivingEntityInstance(LivingEntity value) {
        this.value = value;
    }

    @Override
    public BuiltinType getType() {
        return new LivingEntityType();
    }

    @Override
    public Entity toEntity() {
        return this.toLivingEntity();
    }

    @Override
    public LivingEntity toLivingEntity() {
        return this.value;
    }
}
