package com.revolvingmadness.testing.backend;

import com.google.common.collect.ImmutableList;
import com.revolvingmadness.testing.Testing;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.util.Collection;
import java.util.List;

public class LangScriptManager {
    private static final Identifier TICK_TAG_ID = new Identifier(Testing.ID, "tick");
    private static final Identifier LOAD_TAG_ID = new Identifier(Testing.ID, "load");

    private List<LangScript> tickScripts = ImmutableList.of();
    private boolean justLoaded;
    private LangScriptLoader loader;
    private final MinecraftServer server;

    public LangScriptManager(MinecraftServer server, LangScriptLoader loader) {
        this.server = server;
        this.setLoader(loader);
    }

    public void setLoader(LangScriptLoader loader) {
        this.loader = loader;
        this.reload(loader);
    }

    public void reload(LangScriptLoader loader) {
        this.tickScripts = List.copyOf(loader.getScriptsFromTag(TICK_TAG_ID));
        this.justLoaded = true;
    }

    public void tick() {
        if (this.justLoaded) {
            Collection<LangScript> loadScripts = this.loader.getScriptsFromTag(LOAD_TAG_ID);
            this.executeAll(loadScripts, LOAD_TAG_ID);
            this.justLoaded = false;
        }

        this.executeAll(this.tickScripts, TICK_TAG_ID);
    }

    private void executeAll(Collection<LangScript> scripts, Identifier label) {
        Profiler serverProfiler = this.server.getProfiler();
        if (label == null) {
            throw new RuntimeException("Label is null");
        }
        serverProfiler.push(label::toString);

        scripts.forEach(this::execute);

        this.server.getProfiler().pop();
    }

    private void execute(LangScript script) {
        System.out.println("Executing script '" + script.identifier + "'");
    }
}
