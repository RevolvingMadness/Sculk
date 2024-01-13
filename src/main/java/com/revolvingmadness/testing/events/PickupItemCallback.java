package com.revolvingmadness.testing.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public interface PickupItemCallback {
    Event<PickupItemCallback> EVENT = EventFactory.createArrayBacked(PickupItemCallback.class, (listeners) -> (player, itemStack) -> {
        for (PickupItemCallback listener : listeners) {
            ActionResult result = listener.interact(player, itemStack);

            if (result != ActionResult.PASS) {
                return result;
            }
        }

        return ActionResult.PASS;
    });

    ActionResult interact(PlayerEntity entity, ItemStack itemStack);
}
