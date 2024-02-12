package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.LivingEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import java.util.Objects;

@SuppressWarnings("unused")
public class LivingEntityInstance extends EntityInstance {
    public final LivingEntity value;

    public LivingEntityInstance(LivingEntity value) {
        super(value);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        LivingEntityInstance that = (LivingEntityInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public BuiltinType getType() {
        return LivingEntityType.TYPE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
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
