package com.revolvingmadness.sculk.dynamicreg;

import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.accessors.SimpleRegistryAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class DynamicItemRegistry {
    public static final Map<Identifier, Item> REGISTERED_ITEMS = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static void register(Identifier id, Item value) {
        Sculk.LOGGER.info("Registering item '" + id + "'");
        boolean alreadyRegistered = Registries.ITEM.containsId(id);


        if (alreadyRegistered) {
            ((SimpleRegistryAccessor<Item>) Registries.ITEM).sculk$set(id, value);
            return;
        }

        Registry.register(Registries.ITEM, id, value);
        REGISTERED_ITEMS.put(id, value);
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            MinecraftClient.getInstance().getItemRenderer().getModels().putModel(value, new ModelIdentifier(id, "inventory"));
        }
    }

    public static void register(Identifier id, Block value) {
        DynamicItemRegistry.register(id, new BlockItem(value, new FabricItemSettings()));
    }
}
