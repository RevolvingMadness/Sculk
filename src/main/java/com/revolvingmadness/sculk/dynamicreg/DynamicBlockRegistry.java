package com.revolvingmadness.sculk.dynamicreg;

import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.accessors.SimpleRegistryAccessor;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class DynamicBlockRegistry {
    public static final Map<Identifier, Block> REGISTERED_BLOCKS = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static void register(Identifier id, Block value) {
        Sculk.LOGGER.info("Registering block '" + id + "'");
        boolean alreadyRegistered = Registries.BLOCK.containsId(id);

        if (alreadyRegistered) {
            ((SimpleRegistryAccessor<Block>) Registries.BLOCK).sculk$set(id, value);
            return;
        }

        Registry.register(Registries.BLOCK, id, value);
        REGISTERED_BLOCKS.put(id, value);
    }
}
