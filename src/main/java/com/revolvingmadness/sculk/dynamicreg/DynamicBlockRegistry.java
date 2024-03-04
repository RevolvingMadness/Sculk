package com.revolvingmadness.sculk.dynamicreg;

import com.revolvingmadness.sculk.Sculk;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class DynamicBlockRegistry {
    public static final Map<Identifier, Block> REGISTERED_BLOCKS = new HashMap<>();

    public static void register(Identifier id, Block value) {
        Sculk.LOGGER.info("Registering block '" + id + "'");
        boolean alreadyRegistered = Registries.BLOCK.containsId(id);

        if (alreadyRegistered) {
            Sculk.LOGGER.info("Block '" + id + "' already registered");
            return;
        }

        Registry.register(Registries.BLOCK, id, value);
        REGISTERED_BLOCKS.put(id, value);
    }
}
