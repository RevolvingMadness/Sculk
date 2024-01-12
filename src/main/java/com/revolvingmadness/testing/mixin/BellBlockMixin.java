package com.revolvingmadness.testing.mixin;

import com.revolvingmadness.testing.events.RingBellCallback;
import net.minecraft.block.BellBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BellBlock.class)
public class BellBlockMixin {
    @Inject(at = @At("HEAD"), method = "ring(Lnet/minecraft/world/World;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/hit/BlockHitResult;Lnet/minecraft/entity/player/PlayerEntity;Z)Z")
    public void injectRing(World world, BlockState state, BlockHitResult hitResult, PlayerEntity player, boolean checkHitPos, CallbackInfoReturnable<Boolean> cir) {
        if (!world.isClient) {
            RingBellCallback.EVENT.invoker().interact(player);
        }
    }
}
