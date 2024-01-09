package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.PlayerEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

@SuppressWarnings("unused")
public class PlayerEntityInstance extends BuiltinClass {
    public final PlayerEntity value;

    public PlayerEntityInstance(PlayerEntity value) {
        this.value = value;
    }

    @Override
    public BuiltinType getType() {
        return new PlayerEntityType();
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
