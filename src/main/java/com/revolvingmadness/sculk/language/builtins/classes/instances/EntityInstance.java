package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.EntityType;
import net.minecraft.entity.Entity;

import java.util.Objects;

@SuppressWarnings("unused")
public class EntityInstance extends BuiltinClass {
    public final Entity value;

    public EntityInstance(Entity value) {
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
        EntityInstance that = (EntityInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public BuiltinType getType() {
        return new EntityType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.value);
    }

    @Override
    public Entity toEntity() {
        return this.value;
    }
}
