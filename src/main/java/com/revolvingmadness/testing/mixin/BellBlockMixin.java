package com.revolvingmadness.testing.mixin;

import com.revolvingmadness.testing.events.PlayerRingBellCallback;
import net.minecraft.block.BellBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BellBlock.class)
public class BellBlockMixin {
    @Inject(at = @At("HEAD"), method = "ring(Lnet/minecraft/world/World;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/hit/BlockHitResult;Lnet/minecraft/entity/player/PlayerEntity;Z)Z")
    public void injectRing(World world, BlockState state, BlockHitResult hitResult, PlayerEntity player, boolean checkHitPos, CallbackInfoReturnable<Boolean> cir) {
        Direction direction = hitResult.getSide();
        BlockPos blockPos = hitResult.getBlockPos();
        if (!world.isClient && ((BellBlock) (Object) this).isPointOnBell(state, direction, hitResult.getPos().y - (double) blockPos.getY())) {
            PlayerRingBellCallback.EVENT.invoker().interact(player);
        }
    }
}
