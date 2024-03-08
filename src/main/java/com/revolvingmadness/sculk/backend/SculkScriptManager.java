package com.revolvingmadness.sculk.backend;

import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.keybinds.KeybindHelper;
import com.revolvingmadness.sculk.language.EventHolder;
import com.revolvingmadness.sculk.language.errors.Error;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;

public class SculkScriptManager {
    public static final Identifier LOAD_TAG_ID = new Identifier(Sculk.ID, "load");
    public static final Identifier START_TAG_ID = new Identifier(Sculk.ID, "start");
    public static final Identifier TICK_TAG_ID = new Identifier(Sculk.ID, "tick");
    public static SculkScript currentScript;
    public static SculkScriptLoader loader;
    private static boolean shouldRunLoadScripts = true;
    private static Collection<SculkScript> tickScripts = new ArrayList<>();

    public static void executeAll(Collection<SculkScript> scripts, Identifier tag) {
        if (Sculk.server != null) {
            Sculk.server.getProfiler().push(tag::toString);
        }

        scripts.forEach(SculkScriptManager::execute);

        if (Sculk.server != null) {
            Sculk.server.getProfiler().pop();
        }
    }

    public static void initialize() {
        SculkScriptManager.loader.scripts.forEach((identifier, script) -> {
            try {
                script.initialize();
            } catch (Error exception) {
                Logger.scriptError(script, exception);
                script.hasErrors = true;
            }
        });
    }

    public static void setLoader(SculkScriptLoader loader) {
        SculkScriptManager.loader = loader;
        SculkScriptManager.reload();
    }

    public static void tick() {
        if (SculkScriptManager.shouldRunLoadScripts) {
            SculkScriptManager.initialize();

            Collection<SculkScript> scripts = SculkScriptManager.loader.getScriptsFromTag(SculkScriptManager.LOAD_TAG_ID);

            SculkScriptManager.executeAll(scripts, LOAD_TAG_ID);

            SculkScriptManager.shouldRunLoadScripts = false;
        }

        SculkScriptManager.executeAll(SculkScriptManager.tickScripts, TICK_TAG_ID);
    }

    private static void execute(SculkScript script) {
        if (script.hasErrors) {
            return;
        }

        SculkScriptManager.currentScript = script;

        try {
            script.interpret();
            script.reset();
        } catch (Error exception) {
            Logger.scriptError(script, exception);
            script.hasErrors = true;
        }
    }

    private static void reload() {
        SculkScriptManager.tickScripts = SculkScriptManager.loader.getScriptsFromTag(SculkScriptManager.TICK_TAG_ID);
        SculkScriptManager.currentScript = null;
        SculkScriptManager.shouldRunLoadScripts = true;

        EventHolder.clearEvents();
        KeybindHelper.clearEvents();
    }
}
