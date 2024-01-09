package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.MinecraftServerType;
import net.minecraft.server.MinecraftServer;

public class MinecraftServerInstance extends BuiltinClass {
    public final MinecraftServer value;

    public MinecraftServerInstance(MinecraftServer value) {
        this.value = value;
    }

    @Override
    public BuiltinType getType() {
        return new MinecraftServerType();
    }

    @Override
    public MinecraftServer toMinecraftServer() {
        return this.value;
    }
}
