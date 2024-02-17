package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.InventoryType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

public class InventoryInstance extends BuiltinClass {
    public final Inventory inventory;

    public InventoryInstance(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public BuiltinType getType() {
        return InventoryType.TYPE;
    }

    @Override
    public Inventory toInventory() {
        return this.inventory;
    }

    @Override
    public NbtElement toNBT() {
        NbtList list = new NbtList();

        for (int i = 0; i < this.inventory.size(); i++) {
            ItemStack stack = this.inventory.getStack(i);

            NbtCompound nbt = new NbtCompound();

            nbt.putInt("slot", i);
            nbt.put("stack", new ItemStackInstance(stack).toNBT());

            list.add(nbt);
        }

        return list;
    }
}
