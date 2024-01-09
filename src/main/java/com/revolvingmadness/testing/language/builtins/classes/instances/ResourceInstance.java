package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.ResourceType;
import net.minecraft.util.Identifier;

public class ResourceInstance extends BuiltinClass {
    public final Identifier value;

    public ResourceInstance(Identifier value) {
        this.value = value;
    }

    @Override
    public BuiltinType getType() {
        return new ResourceType();
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
