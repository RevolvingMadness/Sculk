package com.revolvingmadness.sculk.language.builtins.classes.instances.item;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.item.ItemClassType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtString;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class SwordItemInstance extends BuiltinClass {
    public final Identifier id;
    public final SwordItem value;

    public SwordItemInstance(Identifier id, SwordItem value) {
        super(ItemClassType.TYPE);
        this.id = id;
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
        SwordItemInstance that = (SwordItemInstance) o;
        return Objects.equals(this.id, that.id) && Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.value);
    }

    @Override
    public Item toItem() {
        return this.value;
    }

    @Override
    public ItemInstance toItemInstance() {
        return new ItemInstance(this.id, this.value);
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
