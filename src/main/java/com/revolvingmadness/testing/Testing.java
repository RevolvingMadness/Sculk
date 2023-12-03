package com.revolvingmadness.testing;

import com.revolvingmadness.testing.gamerule.TestingGamerules;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

public class Testing implements ModInitializer {
	public static final String ID = "testing";
	//    public static final Logger LOGGER = LoggerFactory.getLogger(ID);
	public static MinecraftServer server;

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTING.register(server1 -> Testing.server = server1);
		TestingGamerules.registerGamerules();
	}
}