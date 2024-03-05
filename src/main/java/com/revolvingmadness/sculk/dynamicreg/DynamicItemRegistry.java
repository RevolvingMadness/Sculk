package com.revolvingmadness.sculk.dynamicreg;

import com.revolvingmadness.sculk.Sculk;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class DynamicItemRegistry {
    public static final Map<Identifier, Item> REGISTERED_ITEMS = new HashMap<>();

    public static void register(Identifier id, Item value) {
        Sculk.LOGGER.info("Registering item '" + id + "'");
        boolean alreadyRegistered = Registries.ITEM.containsId(id);

        if (alreadyRegistered) {
            Sculk.LOGGER.info("Item '" + id + "' already registered");
            return;
        }

        Registry.register(Registries.ITEM, id, value);
        REGISTERED_ITEMS.put(id, value);
        if (MinecraftClient.getInstance() != null) {
            MinecraftClient.getInstance().getItemRenderer().getModels().putModel(value, new ModelIdentifier(id, "inventory"));
        }
    }
}
