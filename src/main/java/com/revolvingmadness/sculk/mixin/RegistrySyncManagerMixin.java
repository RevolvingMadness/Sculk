package com.revolvingmadness.sculk.mixin;

import com.revolvingmadness.sculk.PacketByteBufSerialization;
import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.dynamicreg.DynamicBlockRegistry;
import com.revolvingmadness.sculk.dynamicreg.DynamicItemRegistry;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationNetworking;
import net.fabricmc.fabric.impl.registry.sync.RegistrySyncManager;
import net.fabricmc.fabric.impl.registry.sync.packet.RegistryPacketHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerConfigurationNetworkHandler;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@SuppressWarnings("UnstableApiUsage")
@Mixin(RegistrySyncManager.class)
public class RegistrySyncManagerMixin {
    @Shadow
    @Final
    public static boolean DEBUG;

    @Shadow
    @Final
    public static RegistryPacketHandler DIRECT_PACKET_HANDLER;

    @Inject(at = @At("HEAD"), method = "configureClient", cancellable = true)
    private static void injectConfigureClient(ServerConfigurationNetworkHandler handler, MinecraftServer server, CallbackInfo ci) {
        if (!DEBUG && server.isHost(handler.getDebugProfile())) {
            // Dont send in singleplayer
            ci.cancel();
            return;
        }

        if (!ServerConfigurationNetworking.canSend(handler, DIRECT_PACKET_HANDLER.getPacketId())) {
            // Don't send if the client cannot receive
            ci.cancel();
            return;
        }

        PacketByteBuf buf = PacketByteBufs.create();

        buf.writeMap(DynamicItemRegistry.REGISTERED_ITEMS, PacketByteBuf::writeIdentifier, PacketByteBufSerialization::writeItemSettings);
        buf.writeMap(DynamicBlockRegistry.REGISTERED_BLOCKS, PacketByteBuf::writeIdentifier, (packetByteBuf, block) -> PacketByteBufSerialization.writeBlockSettings(packetByteBuf, block.getSettings()));

        ServerConfigurationNetworking.send(handler, Sculk.DYNAMIC_REGISTRY_SYNC_ID, buf);

        final Map<Identifier, Object2IntMap<Identifier>> map = RegistrySyncManager.createAndPopulateRegistryMap();

        if (map == null) {
            // Don't send when there is nothing to map
            ci.cancel();
            return;
        }

        handler.addTask(new RegistrySyncManager.SyncConfigurationTask(handler, map));
        ci.cancel();
    }
}
