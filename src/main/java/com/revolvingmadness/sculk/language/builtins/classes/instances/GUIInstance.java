package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.GUIType;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;

public class GUIInstance extends BuiltinClass {
    public Inventory inventory;
    public String title;

    public GUIInstance(String title) {
        this.title = title;
        this.inventory = new SimpleInventory(27);
    }

    @Override
    public BuiltinType getType() {
        return GUIType.TYPE;
    }

    @Override
    public GUIInstance toGUI() {
        return this;
    }
}
