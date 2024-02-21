package com.revolvingmadness.sculk.language.builtins.classes.instances.entity;

import com.revolvingmadness.sculk.accessors.EntityAccessor;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.LivingEntityType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtElement;

import java.util.Objects;

public class LivingEntityInstance extends BuiltinClass {
    public final LivingEntity value;

    public LivingEntityInstance(LivingEntity value) {
        super(LivingEntityType.TYPE);
        this.value = value;
    }

    @Override
    public void deleteIndex(BuiltinClass index) {
        this.validateIndex(StringType.TYPE, index);

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
        LivingEntityInstance that = (LivingEntityInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public BuiltinClass getIndex(BuiltinClass index) {
        this.validateIndex(StringType.TYPE, index);

        NbtElement result = ((EntityAccessor) this.value).sculk$readCustomData(index.toString());

        return BuiltinClass.fromNbtElement(result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public void setIndex(BuiltinClass index, BuiltinClass value) {
        this.validateIndex(StringType.TYPE, index);

        ((EntityAccessor) this.value).sculk$writeCustomData(index.toString(), value.toNBT());
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
