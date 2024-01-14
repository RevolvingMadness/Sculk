package com.revolvingmadness.sculk.mixin;

import com.revolvingmadness.sculk.events.PlayerJumpCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(at = @At("HEAD"), method = "jump", cancellable = true)
    public void injectJump(CallbackInfo ci) {
        if (!((PlayerEntity) (Object) this).getWorld().isClient) {
            ActionResult result = PlayerJumpCallback.EVENT.invoker().interact((PlayerEntity) (Object) this);

            if (result == ActionResult.FAIL) {
                ci.cancel();
            }
        }
    }
}
