package com.revolvingmadness.sculk.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface PlayerJumpCallback {
    Event<PlayerJumpCallback> EVENT = EventFactory.createArrayBacked(PlayerJumpCallback.class, (listeners) -> (player) -> {
        for (PlayerJumpCallback listener : listeners) {
            ActionResult result = listener.interact(player);

            if (result != ActionResult.PASS) {
                return result;
            }
        }

        return ActionResult.PASS;
    });

    ActionResult interact(PlayerEntity entity);
}
