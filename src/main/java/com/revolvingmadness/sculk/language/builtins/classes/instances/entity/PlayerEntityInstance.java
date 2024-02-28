package com.revolvingmadness.sculk.language.builtins.classes.instances.entity;

import com.revolvingmadness.sculk.accessors.EntityAccessor;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.entity.PlayerEntityClassType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtElement;

import java.util.Objects;

public class PlayerEntityInstance extends BuiltinClass {
    public final PlayerEntity value;

    public PlayerEntityInstance(PlayerEntity value) {
        super(PlayerEntityClassType.TYPE);
        this.value = value;
    }

    @Override
    public void deleteIndex(BuiltinClass index) {
        this.validateIndex(StringClassType.TYPE, index);

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
        PlayerEntityInstance that = (PlayerEntityInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public BuiltinClass getIndex(BuiltinClass index) {
        this.validateIndex(StringClassType.TYPE, index);

        NbtElement result = ((EntityAccessor) this.value).sculk$readCustomData(index.toString());

        return BuiltinClass.fromNbtElement(result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public void setIndex(BuiltinClass index, BuiltinClass value) {
        this.validateIndex(StringClassType.TYPE, index);

        ((EntityAccessor) this.value).sculk$writeCustomData(index.toString(), value.toNBT());
    }

    @Override
    public Entity toEntity() {
        return this.toLivingEntity();
    }

    @Override
    public LivingEntity toLivingEntity() {
        return this.toPlayerEntity();
    }

    @Override
    public PlayerEntity toPlayerEntity() {
        return this.value;
    }
}
