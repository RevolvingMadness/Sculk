package com.revolvingmadness.sculk.language;

import net.minecraft.util.Identifier;

public enum ScriptTag {
    START, LOAD, TICK, OTHER;

    public static ScriptTag of(Identifier tag) {
        return switch (tag.getPath()) {
            case "start" -> ScriptTag.START;
            case "load" -> ScriptTag.LOAD;
            case "tick" -> ScriptTag.TICK;
            default -> ScriptTag.OTHER;
        };
    }
}
