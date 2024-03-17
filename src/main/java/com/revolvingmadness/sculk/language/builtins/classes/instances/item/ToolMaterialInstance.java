package com.revolvingmadness.sculk.language.builtins.classes.instances.item;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.item.ItemClassType;
import net.minecraft.item.ToolMaterial;

import java.util.Objects;

public class ToolMaterialInstance extends BuiltinClass {
    public final ToolMaterial material;

    public ToolMaterialInstance(ToolMaterial material) {
        super(ItemClassType.TYPE);

        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        ToolMaterialInstance that = (ToolMaterialInstance) o;
        return Objects.equals(material, that.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(material);
    }

    @Override
    public ToolMaterial toToolMaterial() {
        return this.material;
    }
}
