package com.revolvingmadness.testing.backend;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.gamerules.TestingGamerules;
import com.revolvingmadness.testing.language.InternalError;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Logger {
    public static void error(String error) {
        Logger.broadcast(Text.literal(error).formatted(Formatting.RED), true);
    }

    public static void broadcast(MutableText text, boolean broadcastWithoutLogsEnabled) {
        if (Testing.server.getGameRules().getBoolean(TestingGamerules.SCRIPT_LOGS_ENABLED) || broadcastWithoutLogsEnabled) {
            Testing.server.getPlayerManager().broadcast(text, false);
        }
    }

    public static void info(String text) {
        Logger.broadcast(Text.literal(text));
    }

    public static void broadcast(MutableText text) {
        Logger.broadcast(text, false);
    }

    public static void scriptError(LangScript script, RuntimeException exception) {
        if (exception instanceof InternalError internalError) {
            Logger.internalScriptError(script, internalError);
            return;
        }
        Logger.broadcast(Text.literal("The script '" + script.identifier + "' encountered an error:").formatted(Formatting.GRAY), true);

        MutableText textError = Text.literal(exception.getClass().getSimpleName() + ": ").formatted(Formatting.GRAY);

        textError.append(Text.literal(exception.getMessage()).formatted(Formatting.RED));

        Logger.broadcast(textError, true);
    }

    private static void internalScriptError(LangScript script, InternalError internalError) {
        MutableText errorText = Text.literal("The script '" + script.identifier + "' encountered an internal error:").formatted(Formatting.DARK_RED);

        errorText.append(internalError.getClass().getSimpleName() + ": ");

        errorText.append(internalError.getMessage());

        Logger.broadcast(errorText, true);
    }

    public static void warn(String text) {
        Logger.broadcast(Text.literal(text).formatted(Formatting.YELLOW));
    }
}
