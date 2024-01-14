package com.revolvingmadness.sculk.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface BreakBlockCallback {
    Event<BreakBlockCallback> EVENT = EventFactory.createArrayBacked(BreakBlockCallback.class, (listeners) -> (player, itemStack) -> {
        for (BreakBlockCallback listener : listeners) {
            ActionResult result = listener.interact(player, itemStack);

            if (result == ActionResult.FAIL) {
                return result;
            }
        }

        return ActionResult.PASS;
    });

    ActionResult interact(PlayerEntity player, Block block);
}
