package com.revolvingmadness.testing.loader;

import net.minecraft.util.Identifier;

import java.util.List;

public class LangScript {
    public final Identifier identifier;
    public final List<String> contents;

    public LangScript(Identifier identifier, List<String> contents) {
        this.identifier = identifier;
        this.contents = contents;
    }
}
