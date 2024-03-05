package com.revolvingmadness.sculk;

import com.revolvingmadness.sculk.dynamicreg.DynamicBlockRegistry;
import com.revolvingmadness.sculk.dynamicreg.DynamicItemRegistry;
import com.revolvingmadness.sculk.network.PacketByteBufSerialization;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.Item;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Map;

public class SculkClient implements ClientModInitializer {
    public static void reloadResources(MinecraftClient client) {
        for (Item item : Registries.ITEM) {
            client.getItemRenderer().getModels().putModel(item, new ModelIdentifier(Registries.ITEM.getId(item), "inventory"));
        }

        client.reloadResources();
    }

    @Override
    public void onInitializeClient() {
        ClientLoginConnectionEvents.INIT.register((handler, client) -> SculkClient.reloadResources(client));

        ClientPlayNetworking.registerGlobalReceiver(Sculk.RELOAD_RESOURCES_ID, (client, handler, buf, responseSender) -> SculkClient.reloadResources(client));

        ClientConfigurationNetworking.registerGlobalReceiver(Sculk.DYNAMIC_REGISTRY_SYNC_ID, (client, handler, buf, responseSender) -> {
            Map<Identifier, Item> registeredItems = buf.readMap(PacketByteBuf::readIdentifier, PacketByteBufSerialization::readItemSettings);

            registeredItems.forEach(DynamicItemRegistry::register);

            Map<Identifier, FabricBlockSettings> registeredBlocks = buf.readMap(PacketByteBuf::readIdentifier, PacketByteBufSerialization::readBlockSettings);

            registeredBlocks.forEach((identifier, settings) -> DynamicBlockRegistry.register(identifier, new Block(settings)));
        });
    }
}