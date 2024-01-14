package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.WorldType;
import net.minecraft.server.world.ServerWorld;

public class WorldInstance extends BuiltinClass {
    public final ServerWorld value;

    public WorldInstance(ServerWorld value) {
        this.value = value;
    }

    @Override
    public BuiltinType getType() {
        return new WorldType();
    }

    @Override
    public ServerWorld toWorld() {
        return this.value;
    }
}
