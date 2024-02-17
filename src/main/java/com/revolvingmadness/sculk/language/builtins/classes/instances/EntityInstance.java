package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.accessors.EntityAccessor;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.EntityType;
import com.revolvingmadness.sculk.language.builtins.classes.types.StringType;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtElement;

import java.util.Objects;

@SuppressWarnings("unused")
public class EntityInstance extends BuiltinClass {
    public final Entity value;

    public EntityInstance(Entity value) {
        this.value = value;
    }

    @Override
    public void deleteIndex(BuiltinClass index) {
        if (!index.instanceOf(StringType.TYPE)) {
            throw ErrorHolder.cannotIndexTypeByType(this.getType(), index.getType());
        }

        ((EntityAccessor) this.value).sculk$deleteCustomData(index.toString());
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
    public BuiltinClass getIndex(BuiltinClass index) {
        if (!index.instanceOf(StringType.TYPE)) {
            throw ErrorHolder.cannotIndexTypeByType(this.getType(), index.getType());
        }

        NbtElement result = ((EntityAccessor) this.value).sculk$readCustomData(index.toString());

        return BuiltinClass.fromNbtElement(result);
    }

    @Override
    public BuiltinType getType() {
        return EntityType.TYPE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public void setIndex(BuiltinClass index, BuiltinClass value) {
        if (!index.instanceOf(StringType.TYPE)) {
            throw ErrorHolder.cannotIndexTypeByType(this.getType(), index.getType());
        }

        ((EntityAccessor) this.value).sculk$writeCustomData(index.toString(), value.toNBT());
    }

    @Override
    public Entity toEntity() {
        return this.value;
    }
}
