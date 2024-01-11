package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.PlayerManagerType;
import net.minecraft.server.PlayerManager;

import java.util.Objects;

public class PlayerManagerInstance extends BuiltinClass {
    public final PlayerManager value;

    public PlayerManagerInstance(PlayerManager value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        PlayerManagerInstance that = (PlayerManagerInstance) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public BuiltinType getType() {
        return new PlayerManagerType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public PlayerManager toPlayerManager() {
        return this.value;
    }
}
