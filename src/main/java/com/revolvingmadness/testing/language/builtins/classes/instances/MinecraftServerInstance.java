package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.MinecraftServerType;
import net.minecraft.server.MinecraftServer;

import java.util.Objects;

public class MinecraftServerInstance extends BuiltinClass {
    public final MinecraftServer value;

    public MinecraftServerInstance(MinecraftServer value) {
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
        MinecraftServerInstance that = (MinecraftServerInstance) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public BuiltinType getType() {
        return new MinecraftServerType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public MinecraftServer toMinecraftServer() {
        return this.value;
    }
}
