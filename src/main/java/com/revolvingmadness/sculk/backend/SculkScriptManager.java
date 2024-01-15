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
    private static final Identifier LOAD_TAG_ID = new Identifier(Sculk.ID, "load");
    private static final Identifier TICK_TAG_ID = new Identifier(Sculk.ID, "tick");
    public static SculkScript currentScript;
    private boolean justLoaded;
    private SculkScriptLoader loader;
    private List<SculkScript> tickScripts = ImmutableList.of();

    public SculkScriptManager(SculkScriptLoader loader) {
        this.setLoader(loader);
    }

    private void execute(SculkScript script) {
        if (script.hasErrors) {
            return;
        }

        SculkScriptManager.currentScript = script;

        try {
            script.interpret();
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
        this.justLoaded = true;
        EventHolder.clearEvents();
    }

    public void setLoader(SculkScriptLoader loader) {
        this.loader = loader;
        this.reload(loader);
    }

    public void tick() {
        if (this.justLoaded) {
            this.loader.scripts.forEach((identifier, script) -> {
                try {
                    script.initialize();
                } catch (Error exception) {
                    Logger.scriptError(script, exception);
                    script.hasErrors = true;
                }
            });


            Collection<SculkScript> loadScripts = this.loader.getScriptsFromTag(LOAD_TAG_ID);
            this.executeAll(loadScripts, LOAD_TAG_ID);
            this.justLoaded = false;
        }

        this.executeAll(this.tickScripts, TICK_TAG_ID);
    }
}