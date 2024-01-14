package com.revolvingmadness.sculk.mixin;

import com.revolvingmadness.sculk.events.DropItemCallback;
import com.revolvingmadness.sculk.events.PlayerSneakCallback;
import com.revolvingmadness.sculk.events.SendChatMessageCallback;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SentMessage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(at = @At("HEAD"), method = "dropItem")
    public void injectDropItem(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir) {
        if (!((ServerPlayerEntity) (Object) this).getWorld().isClient) {
            DropItemCallback.EVENT.invoker().interact((ServerPlayerEntity) (Object) this, stack);
        }
    }

    @Inject(at = @At("HEAD"), method = "increaseTravelMotionStats")
    public void injectIncreaseTravelMotionStats(double deltaX, double deltaY, double deltaZ, CallbackInfo ci) {
        if (!((ServerPlayerEntity) (Object) this).getWorld().isClient && ((ServerPlayerEntity) (Object) this).isSneaking()) {
            PlayerSneakCallback.EVENT.invoker().interact((ServerPlayerEntity) (Object) this);
        }
    }

    @Inject(at = @At("HEAD"), method = "sendChatMessage", cancellable = true)
    public void injectSendChatMessage(SentMessage message, boolean filterMaskEnabled, MessageType.Parameters params, CallbackInfo ci) {
        if (!((ServerPlayerEntity) (Object) this).getWorld().isClient) {
            ActionResult result = SendChatMessageCallback.EVENT.invoker().interact((ServerPlayerEntity) (Object) this, message);

            if (result == ActionResult.FAIL) {
                ci.cancel();
            }
        }
    }
}
