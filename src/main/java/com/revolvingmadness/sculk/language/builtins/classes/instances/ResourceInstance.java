package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.ResourceType;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class ResourceInstance extends BuiltinClass {
    public final Identifier value;

    public ResourceInstance(Identifier value) {
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
        ResourceInstance that = (ResourceInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public BuiltinType getType() {
        return new ResourceType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public Identifier toResource() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
