package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.MinecraftServerClassType;
import net.minecraft.server.MinecraftServer;

import java.util.Objects;

public class MinecraftServerInstance extends BuiltinClass {
    public final MinecraftServer value;

    public MinecraftServerInstance(MinecraftServer value) {
        super(MinecraftServerClassType.TYPE);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        MinecraftServerInstance that = (MinecraftServerInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

}
