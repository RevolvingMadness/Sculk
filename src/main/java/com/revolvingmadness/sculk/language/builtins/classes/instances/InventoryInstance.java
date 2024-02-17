package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.InventoryType;
import net.minecraft.inventory.Inventory;

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
}
