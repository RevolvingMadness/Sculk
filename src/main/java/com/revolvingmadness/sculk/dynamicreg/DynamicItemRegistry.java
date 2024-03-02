package com.revolvingmadness.sculk.dynamicreg;

import com.revolvingmadness.sculk.backend.Logger;
import com.revolvingmadness.sculk.backend.SculkScriptManager;
import com.revolvingmadness.sculk.language.errors.RegistryError;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DynamicItemRegistry extends DynamicRegistry<Item> {
    public DynamicItemRegistry() {
        super(Registries.ITEM);
    }

    @Override
    public void register(Identifier id, Item value) {
        boolean alreadyRegistered = this.registry.containsId(id);

        if (alreadyRegistered) {
            Logger.scriptWarn(SculkScriptManager.currentScript, new RegistryError("Item '" + id + "' already registered"));
            return;
        }

        Registry.register(this.registry, id, value);
        this.registeredComponents.put(id, value);
    }
}
