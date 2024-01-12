package com.revolvingmadness.testing.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;

public interface PlaceBlockCallback {
    Event<PlaceBlockCallback> EVENT = EventFactory.createArrayBacked(PlaceBlockCallback.class, (listeners) -> (player, itemStack) -> {
        for (PlaceBlockCallback listener : listeners) {
            ActionResult result = listener.interact(player, itemStack);

            if (result == ActionResult.FAIL) {
                return result;
            }
        }

        return ActionResult.PASS;
    });

    ActionResult interact(LivingEntity livingEntity, Block block);
}
