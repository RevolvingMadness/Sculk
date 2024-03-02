package com.revolvingmadness.sculk.backend;

import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.language.errors.Error;
import com.revolvingmadness.sculk.language.errors.InternalError;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Logger {
    public static void broadcast(MutableText text) {
        if (Sculk.server.getPlayerManager() == null) {
            return;
        }

        Sculk.server.getPlayerManager().broadcast(text, false);
    }

    public static void error(String message) {
        Logger.broadcast(Text.literal(message).withColor(Colors.ERROR));
    }

    @SuppressWarnings("unused")
    public static void info(SculkScript script, String text) {
        Logger.broadcast(Text.literal(script.identifier.toString()).formatted(Formatting.GRAY));

        Logger.broadcast(Text.literal(text).withColor(Colors.INFO));
    }

    public static void scriptError(SculkScript script, Error error) {
        if (error instanceof InternalError internalError) {
            Logger.internalScriptError(script, internalError);
            return;
        }

        Logger.broadcast(Text.literal(script.identifier.toString()).formatted(Formatting.GRAY));

        Logger.broadcast(Text.literal(error.message).withColor(Colors.ERROR));
    }

    @SuppressWarnings("unused")
    public static void scriptWarn(SculkScript script, Error warning) {
        Logger.broadcast(Text.literal(script.identifier.toString()).formatted(Formatting.GRAY));

        Logger.broadcast(Text.literal(warning.message).withColor(Colors.WARN));
    }

    @SuppressWarnings("unused")
    public static void warn(String warning) {
        Logger.broadcast(Text.literal(warning).withColor(Colors.WARN));
    }

    private static void internalScriptError(SculkScript script, InternalError error) {
        Logger.broadcast(Text.literal(script.identifier.toString()).formatted(Formatting.GRAY));

        Logger.broadcast(Text.literal(error.message).withColor(Colors.INTERNAL_ERROR));
    }
}
