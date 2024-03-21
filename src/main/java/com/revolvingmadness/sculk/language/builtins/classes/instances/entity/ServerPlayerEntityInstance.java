package com.revolvingmadness.sculk.language.builtins.classes.instances.entity;

import com.revolvingmadness.sculk.accessors.EntityAccessor;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.entity.ServerPlayerEntityClassType;
import com.revolvingmadness.sculk.language.errors.NBTSerializationError;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Objects;

public class ServerPlayerEntityInstance extends BuiltinClass {
    public final ServerPlayerEntity value;

    public ServerPlayerEntityInstance(ServerPlayerEntity value) {
        super(ServerPlayerEntityClassType.TYPE);
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
        ServerPlayerEntityInstance that = (ServerPlayerEntityInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public BuiltinClass getIndex(BuiltinClass index) {
        this.validateIndex(StringClassType.TYPE, index);

        NbtElement result = ((EntityAccessor) this.value).sculk$readCustomData(index.toString());

        return NBTBuiltinClass.fromNbtElement(result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public void setIndex(BuiltinClass index, BuiltinClass value) {
        this.validateIndex(StringClassType.TYPE, index);

        if (!(value instanceof NBTBuiltinClass nbtBuiltinClass)) {
            throw new NBTSerializationError(value.type);
        }

        ((EntityAccessor) this.value).sculk$writeCustomData(index.toString(), nbtBuiltinClass.toNBTElement());
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

    @Override
    public ServerPlayerEntity toServerPlayerEntity() {
        return this.value;
    }
}
