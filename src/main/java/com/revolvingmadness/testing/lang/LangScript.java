package com.revolvingmadness.testing.lang;

import com.revolvingmadness.testing.Testing;
import net.minecraft.util.Identifier;

import java.util.List;

public class LangScript {
    private final Identifier id;
    private final List<String> contents;

    public LangScript(Identifier id, List<String> contents) {
        this.id = id;
        this.contents = contents;

        Testing.LOGGER.info("Loaded script '" + this.id + "' with contents:");
        Testing.LOGGER.info(String.join("\n", this.contents));
    }
}
