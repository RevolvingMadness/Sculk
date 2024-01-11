package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.LivingEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import java.util.Objects;

@SuppressWarnings("unused")
public class LivingEntityInstance extends BuiltinClass {
    public final LivingEntity value;

    public LivingEntityInstance(LivingEntity value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        LivingEntityInstance that = (LivingEntityInstance) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public BuiltinType getType() {
        return new LivingEntityType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
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
