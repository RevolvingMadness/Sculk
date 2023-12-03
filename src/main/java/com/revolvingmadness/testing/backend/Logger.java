package com.revolvingmadness.testing.backend;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.gamerule.TestingGamerules;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Logger {
    public static void info(String text) {
        Logger.broadcast(Text.literal(text));
    }

    public static void error(String text) {
        Logger.broadcast(Text.literal(text).formatted(Formatting.RED));
    }

    public static void warn(String text) {
        Logger.broadcast(Text.literal(text).formatted(Formatting.YELLOW));
    }

    public static void broadcast(MutableText text) {
        if (!Testing.server.getGameRules().getBoolean(TestingGamerules.SCRIPT_LOGS_ENABLED)) {
            return;
        }

        Testing.server.getPlayerManager().broadcast(text, false);
    }
}
