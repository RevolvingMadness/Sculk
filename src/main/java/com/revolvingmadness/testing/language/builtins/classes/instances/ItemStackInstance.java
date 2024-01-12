package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.ItemStackType;
import net.minecraft.item.ItemStack;

import java.util.Objects;

public class ItemStackInstance extends BuiltinClass {
    public final ItemStack value;

    public ItemStackInstance(ItemStack value) {
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
        ItemStackInstance that = (ItemStackInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public BuiltinType getType() {
        return new ItemStackType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public ItemStack toItemStack() {
        return this.value;
    }
}
