package com.revolvingmadness.sculk;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class SculkClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(Sculk.RELOAD_RESOURCES_ID, (client, handler, buf, responseSender) -> {
            Sculk.LOGGER.info("Reloading resources...");
            client.reloadResources();
        });
    }
}