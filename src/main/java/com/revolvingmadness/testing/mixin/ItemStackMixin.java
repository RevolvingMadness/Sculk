package com.revolvingmadness.testing.mixin;

import com.revolvingmadness.testing.events.CraftItemCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(at = @At("HEAD"), method = "onCraftByPlayer")
    public void injectOnCraftByPlayer(World world, PlayerEntity player, int amount, CallbackInfo ci) {
        if (!world.isClient) {
            CraftItemCallback.EVENT.invoker().interact(player, (ItemStack) (Object) this);
        }
    }
}
