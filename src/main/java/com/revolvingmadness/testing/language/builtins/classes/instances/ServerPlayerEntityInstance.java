package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.ServerPlayerEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class ServerPlayerEntityInstance extends BuiltinClass {
    public final ServerPlayerEntity value;

    public ServerPlayerEntityInstance(ServerPlayerEntity value) {
        this.value = value;
    }

    @Override
    public BuiltinType getType() {
        return new ServerPlayerEntityType();
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
        return this.toServerPlayerEntity();
    }

    @Override
    public ServerPlayerEntity toServerPlayerEntity() {
        return this.value;
    }
}
