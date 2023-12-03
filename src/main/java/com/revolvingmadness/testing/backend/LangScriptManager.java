package com.revolvingmadness.testing.backend;

import com.google.common.collect.ImmutableList;
import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.language.interpreter.LangInterpreter;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class LangScriptManager {
    private static final Identifier TICK_TAG_ID = new Identifier(Testing.ID, "tick");
    private static final Identifier LOAD_TAG_ID = new Identifier(Testing.ID, "load");

    private List<LangScript> tickScripts = ImmutableList.of();
    private boolean justLoaded;
    private LangScriptLoader loader;
    private final MinecraftServer server;
    private final LangInterpreter interpreter;

    public LangScriptManager(MinecraftServer server, LangScriptLoader loader) {
        this.server = server;
        this.interpreter = new LangInterpreter(server);
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
            loader.scripts.forEach((identifier, script) -> {
                try {
                    script.initialize();
                } catch (RuntimeException exception) {
                    this.server.getPlayerManager().broadcast(Text.literal("The script '" + script.identifier + "' has the following error:").formatted(Formatting.GRAY), false);
                    this.server.getPlayerManager().broadcast(Text.literal(exception.getClass().getSimpleName() + ": ").formatted(Formatting.DARK_GRAY).append(Text.literal(exception.getMessage()).formatted(Formatting.RED)), false);
                    script.hasErrors = true;
                }
            });


            Collection<LangScript> loadScripts = this.loader.getScriptsFromTag(LOAD_TAG_ID);
            this.executeAll(loadScripts, LOAD_TAG_ID);
            this.justLoaded = false;
        }

        this.executeAll(this.tickScripts, TICK_TAG_ID);
    }

    private void executeAll(Collection<LangScript> scripts, Identifier label) {
        Profiler serverProfiler = this.server.getProfiler();

        Objects.requireNonNull(label);

        serverProfiler.push(label::toString);

        scripts.forEach(this::execute);

        this.server.getProfiler().pop();
    }

    private void execute(LangScript script) {
        if (script.hasErrors) {
            return;
        }

        try {
            this.interpreter.interpret(script.scriptNode);
        } catch (RuntimeException exception) {
            this.server.getPlayerManager().broadcast(Text.literal("The script '" + script.identifier + "' has the following error:").formatted(Formatting.GRAY), false);
            this.server.getPlayerManager().broadcast(Text.literal(exception.getMessage()).formatted(Formatting.RED), false);
            script.hasErrors = true;
        }
    }
}
