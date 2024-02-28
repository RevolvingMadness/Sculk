package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.ItemStackClassType;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.List;
import java.util.Objects;

public class ItemStackInstance extends BuiltinClass {
    public final ItemStack value;

    public ItemStackInstance(ItemStack value) {
        super(ItemStackClassType.TYPE);
        this.value = value;

        this.variableScope.declare(List.of(TokenType.CONST), "item", new ItemInstance(this.value.getItem()));
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
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public ItemStack toItemStack() {
        return this.value;
    }

    @Override
    public NbtElement toNBT() {
        NbtCompound nbt = new NbtCompound();

        nbt.put("id", new ItemInstance(this.value.getItem()).toNBT());
        nbt.putInt("count", this.value.getCount());

        return nbt;
    }
}
