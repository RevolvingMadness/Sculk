package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.ItemClassType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtString;
import net.minecraft.registry.Registries;

import java.util.Objects;

public class ItemInstance extends BuiltinClass {
    public final Item value;

    public ItemInstance(Item value) {
        super(ItemClassType.TYPE);
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
        ItemInstance that = (ItemInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public Item toItem() {
        return this.value;
    }

    @Override
    public ItemStack toItemStack() {
        return this.value.getDefaultStack();
    }

    @Override
    public NbtElement toNBT() {
        return NbtString.of(Registries.ITEM.getId(this.value).toString());
    }
}
