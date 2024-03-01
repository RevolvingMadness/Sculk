package com.revolvingmadness.sculk.backend;

import com.google.common.collect.ImmutableList;
import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.language.EventHolder;
import com.revolvingmadness.sculk.language.errors.Error;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class SculkScriptManager {
    public static final Identifier LOAD_TAG_ID = new Identifier(Sculk.ID, "load");
    public static final Identifier START_TAG_ID = new Identifier(Sculk.ID, "start");
    public static final Identifier TICK_TAG_ID = new Identifier(Sculk.ID, "tick");
    public static SculkScript currentScript;
    public static SculkScriptLoader loader;
    private boolean shouldRunLoadScripts;
    private boolean shouldRunStartScripts;
    private List<SculkScript> tickScripts = ImmutableList.of();

    public SculkScriptManager(SculkScriptLoader loader) {
        this.setLoader(loader);

        this.shouldRunLoadScripts = true;
        this.shouldRunStartScripts = true;
    }

    private void execute(SculkScript script) {
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

    private void executeAll(Collection<SculkScript> scripts, Identifier label) {
        Profiler serverProfiler = Sculk.server.getProfiler();

        Objects.requireNonNull(label);

        serverProfiler.push(label::toString);

        scripts.forEach(this::execute);

        Sculk.server.getProfiler().pop();
    }

    public void reload(SculkScriptLoader loader) {
        this.tickScripts = List.copyOf(loader.getScriptsFromTag(TICK_TAG_ID));
        SculkScriptManager.currentScript = null;
        this.shouldRunLoadScripts = true;

        EventHolder.clearEvents();
    }

    public void setLoader(SculkScriptLoader loader) {
        SculkScriptManager.loader = loader;
        this.reload(loader);
    }

    public void tick() {
        if (this.shouldRunLoadScripts) {
            SculkScriptManager.loader.scripts.forEach((identifier, script) -> {
                try {
                    script.initialize();
                } catch (Error exception) {
                    Logger.scriptError(script, exception);
                    script.hasErrors = true;
                }
            });

            Collection<SculkScript> loadScripts = SculkScriptManager.loader.getScriptsFromTag(LOAD_TAG_ID);

            this.executeAll(loadScripts, LOAD_TAG_ID);

            this.shouldRunLoadScripts = false;
        }

        if (this.shouldRunStartScripts) {
            Collection<SculkScript> startScripts = SculkScriptManager.loader.getScriptsFromTag(START_TAG_ID);

            this.executeAll(startScripts, START_TAG_ID);

            this.shouldRunStartScripts = false;
        }

        this.executeAll(this.tickScripts, TICK_TAG_ID);
    }
}
