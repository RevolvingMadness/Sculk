package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.PlayerManagerType;
import net.minecraft.server.PlayerManager;

public class PlayerManagerInstance extends BuiltinClass {
    public final PlayerManager value;

    public PlayerManagerInstance(PlayerManager value) {
        this.value = value;
    }

    @Override
    public BuiltinType getType() {
        return new PlayerManagerType();
    }

    @Override
    public PlayerManager toPlayerManager() {
        return this.value;
    }
}
