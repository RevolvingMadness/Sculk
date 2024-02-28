package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.GUIClassType;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;

public class GUIInstance extends BuiltinClass {
    public Inventory inventory;
    public String title;

    public GUIInstance(String title) {
        super(GUIClassType.TYPE);
        this.title = title;
        this.inventory = new SimpleInventory(27);
    }

    @Override
    public GUIInstance toGUI() {
        return this;
    }
}
