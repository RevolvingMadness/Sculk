package com.revolvingmadness.sculk.mixin;

import com.mojang.authlib.GameProfile;
import com.revolvingmadness.sculk.Sculk;
import net.fabricmc.fabric.api.networking.v1.FabricServerConfigurationNetworkHandler;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.listener.ServerConfigurationPacketListener;
import net.minecraft.network.listener.TickablePacketListener;
import net.minecraft.network.packet.c2s.common.SyncedClientOptions;
import net.minecraft.network.packet.c2s.config.ReadyC2SPacket;
import net.minecraft.network.packet.s2c.common.DisconnectS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.*;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerConfigurationNetworkHandler.class)
public abstract class ServerConfigurationNetworkHandlerMixin extends ServerCommonNetworkHandler implements TickablePacketListener, ServerConfigurationPacketListener, FabricServerConfigurationNetworkHandler {
    @Shadow
    @Final
    private static Text INVALID_PLAYER_DATA_TEXT;
    @Shadow
    @Final
    private static Logger LOGGER;
    @Shadow
    @Final
    private GameProfile profile;
    @Shadow
    private SyncedClientOptions syncedOptions;

    public ServerConfigurationNetworkHandlerMixin(MinecraftServer server, ClientConnection connection, ConnectedClientData clientData) {
        super(server, connection, clientData);
    }

    @Inject(at = @At("HEAD"), method = "onReady", cancellable = true)
    public void injectOnReady(ReadyC2SPacket packet, CallbackInfo ci) {
        this.connection.disableAutoRead();
        NetworkThreadUtils.forceMainThread(packet, this, this.server);
        this.onTaskFinished(JoinWorldTask.KEY);
        try {
            PlayerManager playerManager = this.server.getPlayerManager();
            if (playerManager.getPlayer(this.profile.getId()) != null) {
                this.disconnect(PlayerManager.DUPLICATE_LOGIN_TEXT);
                ci.cancel();
            }
            Text text = playerManager.checkCanJoin(this.connection.getAddress(), this.profile);
            if (text != null) {
                this.disconnect(text);
                ci.cancel();
            }
            ServerPlayerEntity serverPlayerEntity = playerManager.createPlayer(this.profile, this.syncedOptions);
            playerManager.onPlayerConnect(this.connection, serverPlayerEntity, this.createClientData(this.syncedOptions));
            this.connection.enableAutoRead();
            ServerPlayNetworking.send(serverPlayerEntity, Sculk.RELOAD_RESOURCES_ID, PacketByteBufs.empty());
        } catch (Exception exception) {
            LOGGER.error("Couldn't place player in world", exception);
            this.connection.send(new DisconnectS2CPacket(INVALID_PLAYER_DATA_TEXT));
            this.connection.disconnect(INVALID_PLAYER_DATA_TEXT);
        }
        ci.cancel();
    }

    @Shadow
    protected abstract void onTaskFinished(ServerPlayerConfigurationTask.Key key);
}
