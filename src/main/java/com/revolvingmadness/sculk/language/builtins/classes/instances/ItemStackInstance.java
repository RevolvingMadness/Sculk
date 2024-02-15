package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.ItemStackType;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Objects;

public class ItemStackInstance extends BuiltinClass {
    public final ItemStack value;

    public ItemStackInstance(ItemStack value) {
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
    public BuiltinType getType() {
        return ItemStackType.TYPE;
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
