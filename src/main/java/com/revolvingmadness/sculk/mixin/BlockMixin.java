package com.revolvingmadness.sculk.mixin;

import com.revolvingmadness.sculk.events.BreakBlockCallback;
import com.revolvingmadness.sculk.events.PlaceBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(at = @At("HEAD"), method = "onBreak")
    public void injectOnBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfoReturnable<BlockState> cir) {
        if (!world.isClient) {
            BreakBlockCallback.EVENT.invoker().interact(player, (Block) (Object) this);
        }
    }

    @Inject(at = @At("HEAD"), method = "onPlaced")
    public void injectOnPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
        if (!world.isClient) {
            PlaceBlockCallback.EVENT.invoker().interact(placer, (Block) (Object) this);
        }
    }
}
