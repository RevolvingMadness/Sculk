package com.revolvingmadness.testing.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface RingBellCallback {
    Event<RingBellCallback> EVENT = EventFactory.createArrayBacked(RingBellCallback.class, (listeners) -> (player) -> {
        for (RingBellCallback listener : listeners) {
            ActionResult result = listener.interact(player);

            if (result != ActionResult.PASS) {
                return result;
            }
        }

        return ActionResult.PASS;
    });

    ActionResult interact(PlayerEntity entity);
}
