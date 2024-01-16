package com.revolvingmadness.sculk.backend;

import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.language.errors.Error;
import com.revolvingmadness.sculk.language.errors.InternalError;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Logger {
    public static void broadcast(MutableText text) {
        Sculk.server.getPlayerManager().broadcast(text, false);
    }

    public static void error(String message) {
        Logger.broadcast(Text.literal(message).formatted(Formatting.RED));
    }

    @SuppressWarnings("unused")
    public static void info(String text) {
        Logger.broadcast(Text.literal(text));
    }

    public static void scriptError(SculkScript script, Error error) {
        if (error instanceof InternalError internalError) {
            Logger.internalScriptError(script, internalError);
            return;
        }

        Logger.broadcast(Text.literal("The script '" + script.identifier + "' encountered an error:").formatted(Formatting.GRAY));

        MutableText textError = Text.literal(error.getClass().getSimpleName() + ": ").formatted(Formatting.GRAY);

        textError.append(Text.literal(error.message).formatted(Formatting.RED));

        Logger.broadcast(textError);
    }

    @SuppressWarnings("unused")
    public static void warn(String text) {
        Logger.broadcast(Text.literal(text).formatted(Formatting.YELLOW));
    }

    private static void internalScriptError(SculkScript script, InternalError error) {
        Logger.broadcast(Text.literal("The script '" + script.identifier + "' encountered an internal error:").formatted(Formatting.GRAY));

        MutableText textError = Text.literal(error.getClass().getSimpleName() + ": ").formatted(Formatting.GRAY);

        textError.append(Text.literal(error.message).formatted(Formatting.RED));

        Logger.broadcast(textError);
    }
}
