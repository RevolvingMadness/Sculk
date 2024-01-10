package com.revolvingmadness.testing.backend;

import com.google.common.collect.ImmutableList;
import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.language.EventHolder;
import com.revolvingmadness.testing.language.errors.Error;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class LangScriptManager {
    private static final Identifier LOAD_TAG_ID = new Identifier(Testing.ID, "load");
    private static final Identifier TICK_TAG_ID = new Identifier(Testing.ID, "tick");
    public static LangScript currentScript;
    private boolean justLoaded;
    private LangScriptLoader loader;
    private List<LangScript> tickScripts = ImmutableList.of();

    public LangScriptManager(LangScriptLoader loader) {
        this.setLoader(loader);
    }

    private void execute(LangScript script) {
        if (script.hasErrors) {
            return;
        }

        LangScriptManager.currentScript = script;

        try {
            script.interpret();
        } catch (Error exception) {
            Logger.scriptError(script, exception);
            script.hasErrors = true;
        }
    }

    private void executeAll(Collection<LangScript> scripts, Identifier label) {
        Profiler serverProfiler = Testing.server.getProfiler();

        Objects.requireNonNull(label);

        serverProfiler.push(label::toString);

        scripts.forEach(this::execute);

        Testing.server.getProfiler().pop();
    }

    public void reload(LangScriptLoader loader) {
        this.tickScripts = List.copyOf(loader.getScriptsFromTag(TICK_TAG_ID));
        this.justLoaded = true;
        EventHolder.clearEvents();
    }

    public void setLoader(LangScriptLoader loader) {
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


            Collection<LangScript> loadScripts = this.loader.getScriptsFromTag(LOAD_TAG_ID);
            this.executeAll(loadScripts, LOAD_TAG_ID);
            this.justLoaded = false;
        }

        this.executeAll(this.tickScripts, TICK_TAG_ID);
    }
}
