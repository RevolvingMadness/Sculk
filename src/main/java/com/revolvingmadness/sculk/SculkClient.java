package com.revolvingmadness.sculk;

import com.revolvingmadness.sculk.dynamicreg.DynamicBlockRegistry;
import com.revolvingmadness.sculk.dynamicreg.DynamicItemRegistry;
import com.revolvingmadness.sculk.network.PacketByteBufSerialization;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.Map;

public class SculkClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(Sculk.RELOAD_RESOURCES_ID, (client, handler, buf, responseSender) -> client.reloadResources());

        ClientConfigurationNetworking.registerGlobalReceiver(Sculk.DYNAMIC_REGISTRY_SYNC_ID, (client, handler, buf, responseSender) -> {
            Map<Identifier, Item> registeredItems = buf.readMap(PacketByteBuf::readIdentifier, PacketByteBufSerialization::readItemSettings);

            registeredItems.forEach(DynamicItemRegistry::register);

            Map<Identifier, FabricBlockSettings> registeredBlocks = buf.readMap(PacketByteBuf::readIdentifier, PacketByteBufSerialization::readBlockSettings);

            registeredBlocks.forEach((identifier, settings) -> DynamicBlockRegistry.register(identifier, new Block(settings)));
        });
    }
}