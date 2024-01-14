package com.revolvingmadness.sculk.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.message.SentMessage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;

public interface SendChatMessageCallback {
    Event<SendChatMessageCallback> EVENT = EventFactory.createArrayBacked(SendChatMessageCallback.class, (listeners) -> (player, message) -> {
        for (SendChatMessageCallback listener : listeners) {
            ActionResult result = listener.interact(player, message);

            if (result != ActionResult.PASS) {
                return result;
            }
        }

        return ActionResult.PASS;
    });

    ActionResult interact(ServerPlayerEntity entity, SentMessage message);
}
