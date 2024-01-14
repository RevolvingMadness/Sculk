package com.revolvingmadness.sculk;

import com.revolvingmadness.sculk.gamerules.SculkGamerules;
import com.revolvingmadness.sculk.language.EventHolder;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

import java.util.HashMap;
import java.util.Map;

public class Sculk implements ModInitializer {
    public static final String ID = "sculk";
    public static final Map<String, TokenType> keywords = new HashMap<>();
    public static MinecraftServer server;

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTING.register(server1 -> Sculk.server = server1);

        SculkGamerules.registerGamerules();
        EventHolder.registerEvents();

        // Values
        Sculk.keywords.put("true", TokenType.TRUE);
        Sculk.keywords.put("false", TokenType.FALSE);
        Sculk.keywords.put("null", TokenType.NULL);

        // Access Modifiers
        Sculk.keywords.put("public", TokenType.PUBLIC);
        Sculk.keywords.put("private", TokenType.PRIVATE);
        Sculk.keywords.put("abstract", TokenType.ABSTRACT);
        Sculk.keywords.put("static", TokenType.STATIC);
        Sculk.keywords.put("const", TokenType.CONST);

        // Control flow
        Sculk.keywords.put("if", TokenType.IF);
        Sculk.keywords.put("else", TokenType.ELSE);
        Sculk.keywords.put("for", TokenType.FOR);
        Sculk.keywords.put("foreach", TokenType.FOREACH);
        Sculk.keywords.put("while", TokenType.WHILE);

        Sculk.keywords.put("return", TokenType.RETURN);
        Sculk.keywords.put("continue", TokenType.CONTINUE);
        Sculk.keywords.put("break", TokenType.BREAK);

        // Declaration
        Sculk.keywords.put("class", TokenType.CLASS);
        Sculk.keywords.put("var", TokenType.VAR);
        Sculk.keywords.put("function", TokenType.FUNCTION);

        // Misc
        Sculk.keywords.put("import", TokenType.IMPORT);
        Sculk.keywords.put("extends", TokenType.EXTENDS);
        Sculk.keywords.put("instanceof", TokenType.INSTANCE_OF);
        Sculk.keywords.put("delete", TokenType.DELETE);
    }
}