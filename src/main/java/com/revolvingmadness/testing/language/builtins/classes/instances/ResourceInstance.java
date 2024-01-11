package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.ResourceType;
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
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        ResourceInstance that = (ResourceInstance) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public BuiltinType getType() {
        return new ResourceType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public Identifier toResource() {
        return this.value;
    }

    @Override
    public String toStringType() {
        return this.value.toString();
    }
}
