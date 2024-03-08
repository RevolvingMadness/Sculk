package com.revolvingmadness.sculk.mixin;

import com.revolvingmadness.sculk.PacketByteBufSerialization;
import com.revolvingmadness.sculk.Sculk;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public class KeyBindingMixin {
    @Inject(at = @At("HEAD"), method = "onKeyPressed")
    private static void injectOnKeyPressed(InputUtil.Key key, CallbackInfo ci) {
        PacketByteBuf buf = PacketByteBufs.create();

        PacketByteBufSerialization.writeKey(key, buf);

        ClientPlayNetworking.send(Sculk.KEY_PRESS_ID, buf);
    }
}
