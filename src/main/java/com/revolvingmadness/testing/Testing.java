package com.revolvingmadness.testing;

import com.revolvingmadness.testing.gamerules.TestingGamerules;
import com.revolvingmadness.testing.language.lexer.TokenType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

import java.util.HashMap;
import java.util.Map;

public class Testing implements ModInitializer {
	public static final String ID = "testing";
	//    public static final Logger LOGGER = LoggerFactory.getLogger(ID);
	public static MinecraftServer server;
	public static final Map<String, TokenType> keywords = new HashMap<>();

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTING.register(server1 -> Testing.server = server1);
		TestingGamerules.registerGamerules();
		Testing.keywords.put("true", TokenType.TRUE);
		Testing.keywords.put("false", TokenType.FALSE);
		Testing.keywords.put("import", TokenType.IMPORT);
		Testing.keywords.put("if", TokenType.IF);
	}
}