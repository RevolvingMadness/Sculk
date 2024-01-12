package com.revolvingmadness.testing.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;

public interface PlayerSneakCallback {
    Event<PlayerSneakCallback> EVENT = EventFactory.createArrayBacked(PlayerSneakCallback.class, (listeners) -> (player) -> {
        for (PlayerSneakCallback listener : listeners) {
            ActionResult result = listener.interact(player);

            if (result != ActionResult.PASS) {
                return result;
            }
        }

        return ActionResult.PASS;
    });

    ActionResult interact(ServerPlayerEntity entity);
}
