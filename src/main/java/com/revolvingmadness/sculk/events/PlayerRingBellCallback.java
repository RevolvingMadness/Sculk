package com.revolvingmadness.sculk.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface PlayerRingBellCallback {
    Event<PlayerRingBellCallback> EVENT = EventFactory.createArrayBacked(PlayerRingBellCallback.class, (listeners) -> (player) -> {
        for (PlayerRingBellCallback listener : listeners) {
            ActionResult result = listener.interact(player);

            if (result != ActionResult.PASS) {
                return result;
            }
        }

        return ActionResult.PASS;
    });

    ActionResult interact(PlayerEntity entity);
}
