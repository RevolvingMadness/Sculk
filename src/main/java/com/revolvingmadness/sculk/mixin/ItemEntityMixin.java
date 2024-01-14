package com.revolvingmadness.sculk.mixin;

import com.revolvingmadness.sculk.events.PickupItemCallback;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @Inject(at = @At("HEAD"), method = "onPlayerCollision", cancellable = true)
    public void injectOnPlayerCollision(PlayerEntity player, CallbackInfo ci) {
        if (!((ItemEntity) (Object) this).getWorld().isClient) {
            ActionResult result = PickupItemCallback.EVENT.invoker().interact(player, ((ItemEntity) (Object) this).getStack());

            if (result == ActionResult.FAIL) {
                ci.cancel();
            }
        }
    }
}
