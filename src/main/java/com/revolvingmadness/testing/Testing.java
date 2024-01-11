package com.revolvingmadness.testing;

import com.revolvingmadness.testing.events.SendChatMessageCallback;
import com.revolvingmadness.testing.gamerules.TestingGamerules;
import com.revolvingmadness.testing.language.EventHolder;
import com.revolvingmadness.testing.language.builtins.classes.instances.BlockPosInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.LivingEntityInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.ServerPlayerEntityInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.testing.language.lexer.TokenType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Testing implements ModInitializer {
    public static final String ID = "testing";
    public static final Map<String, TokenType> keywords = new HashMap<>();
    //    public static final Logger LOGGER = LoggerFactory.getLogger(ID);
    public static MinecraftServer server;

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTING.register(server1 -> Testing.server = server1);
        TestingGamerules.registerGamerules();

        // Values
        Testing.keywords.put("true", TokenType.TRUE);
        Testing.keywords.put("false", TokenType.FALSE);
        Testing.keywords.put("null", TokenType.NULL);

        // Access Modifiers
        Testing.keywords.put("public", TokenType.PUBLIC);
        Testing.keywords.put("private", TokenType.PRIVATE);
        Testing.keywords.put("abstract", TokenType.ABSTRACT);
        Testing.keywords.put("static", TokenType.STATIC);
        Testing.keywords.put("const", TokenType.CONST);

        // Control flow
        Testing.keywords.put("if", TokenType.IF);
        Testing.keywords.put("for", TokenType.FOR);
        Testing.keywords.put("while", TokenType.WHILE);

        Testing.keywords.put("return", TokenType.RETURN);
        Testing.keywords.put("continue", TokenType.CONTINUE);
        Testing.keywords.put("break", TokenType.BREAK);

        Testing.keywords.put("else", TokenType.ELSE);

        // Declaration
        Testing.keywords.put("class", TokenType.CLASS);
        Testing.keywords.put("var", TokenType.VAR);
        Testing.keywords.put("function", TokenType.FUNCTION);

        // Misc
        Testing.keywords.put("import", TokenType.IMPORT);
        Testing.keywords.put("extends", TokenType.EXTENDS);
        Testing.keywords.put("instanceof", TokenType.INSTANCE_OF);
        Testing.keywords.put("delete", TokenType.DELETE);

        EntitySleepEvents.START_SLEEPING.register((livingEntity, sleepingPos) -> EventHolder.onSleep.forEach(event -> event.execute(List.of(new LivingEntityInstance(livingEntity), new BlockPosInstance(sleepingPos)))));

        SendChatMessageCallback.EVENT.register((player, message) -> {
            EventHolder.onSendChatMessage.forEach(event -> event.execute(List.of(new ServerPlayerEntityInstance(player), new StringInstance(message.content().getString()))));

            return ActionResult.PASS;
        });
    }
}