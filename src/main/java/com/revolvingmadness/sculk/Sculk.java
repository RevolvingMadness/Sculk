package com.revolvingmadness.sculk;

import com.revolvingmadness.sculk.accessors.DatapackContentsAccessor;
import com.revolvingmadness.sculk.backend.SculkScript;
import com.revolvingmadness.sculk.backend.SculkScriptManager;
import com.revolvingmadness.sculk.gamerules.SculkGamerules;
import com.revolvingmadness.sculk.language.EventHolder;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Sculk implements ModInitializer {
    public static final String ID = "sculk";
    public static final Logger LOGGER = LoggerFactory.getLogger(Sculk.ID);
    public static final Map<String, TokenType> keywords = new HashMap<>();
    public static MinecraftServer server;

    public static int getMaxArgumentCount() {
        if (Sculk.server.getOverworld() == null) {
            return 255;
        }

        return Sculk.getMaxArgumentCount();
    }

    public static int getMaxLoops() {
        if (Sculk.server.getOverworld() == null) {
            return 65535;
        }

        return Sculk.server.getGameRules().getInt(SculkGamerules.MAX_LOOPS);
    }

    @Override
    public void onInitialize() {
        SculkGamerules.registerGamerules();
        EventHolder.registerEvents();

        ServerLifecycleEvents.SERVER_STARTING.register(server_ -> {
            Sculk.server = server_;

            SculkScriptManager.setLoader(((DatapackContentsAccessor) Sculk.server.resourceManagerHolder.dataPackContents()).sculk$getSculkScriptLoader());

            SculkScriptManager.initialize();

            Collection<SculkScript> scripts = SculkScriptManager.loader.getScriptsFromTag(SculkScriptManager.START_TAG_ID);

            SculkScriptManager.executeAll(scripts, SculkScriptManager.START_TAG_ID);
        });

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
        Sculk.keywords.put("nonnull", TokenType.NONULL);

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
        Sculk.keywords.put("enum", TokenType.ENUM);
        Sculk.keywords.put("var", TokenType.VAR);
        Sculk.keywords.put("function", TokenType.FUNCTION);

        // Misc
        Sculk.keywords.put("import", TokenType.IMPORT);
        Sculk.keywords.put("switch", TokenType.SWITCH);
        Sculk.keywords.put("case", TokenType.CASE);
        Sculk.keywords.put("default", TokenType.DEFAULT);
        Sculk.keywords.put("yield", TokenType.YIELD);
        Sculk.keywords.put("as", TokenType.AS);
        Sculk.keywords.put("from", TokenType.FROM);
        Sculk.keywords.put("extends", TokenType.EXTENDS);
        Sculk.keywords.put("instanceof", TokenType.INSTANCEOF);
        Sculk.keywords.put("delete", TokenType.DELETE);
    }
}