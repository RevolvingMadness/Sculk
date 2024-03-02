package com.revolvingmadness.sculk.dynamicreg;

import com.revolvingmadness.sculk.backend.Logger;
import com.revolvingmadness.sculk.backend.SculkScriptManager;
import com.revolvingmadness.sculk.language.errors.RegistryError;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DynamicBlockRegistry extends DynamicRegistry<Block> {
    public DynamicBlockRegistry() {
        super(Registries.BLOCK);
    }

    @Override
    public void register(Identifier id, Block value) {
        boolean alreadyRegistered = this.registry.containsId(id);

        if (alreadyRegistered) {
            Logger.scriptError(SculkScriptManager.currentScript, new RegistryError("Block '" + id + "' already registered"));
            return;
        }

        Registry.register(this.registry, id, value);
        this.registeredComponents.put(id, value);
    }
}
