package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import net.minecraft.server.MinecraftServer;

public class LangInterpreter {
    public final MinecraftServer server;

    public LangInterpreter(MinecraftServer server) {
        this.server = server;
    }

    public void interpret(ScriptNode program) {
        program.interpret();
    }
}
