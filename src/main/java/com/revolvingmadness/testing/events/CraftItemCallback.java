package com.revolvingmadness.testing.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public interface CraftItemCallback {
    Event<CraftItemCallback> EVENT = EventFactory.createArrayBacked(CraftItemCallback.class, (listeners) -> (player, itemStack) -> {
        for (CraftItemCallback listener : listeners) {
            ActionResult result = listener.interact(player, itemStack);

            if (result == ActionResult.FAIL) {
                return result;
            }
        }

        return ActionResult.PASS;
    });

    ActionResult interact(PlayerEntity player, ItemStack item);
}
