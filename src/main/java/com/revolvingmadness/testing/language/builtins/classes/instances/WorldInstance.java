package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.WorldType;
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
