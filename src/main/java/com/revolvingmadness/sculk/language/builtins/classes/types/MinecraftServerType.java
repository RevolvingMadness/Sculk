package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.*;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.ServerCommandSource;

import java.util.List;

public class MinecraftServerType extends BuiltinType {
    public static final MinecraftServerType TYPE = new MinecraftServerType();

    private MinecraftServerType() {
        super("MinecraftServer");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "setPVPEnabled", new SetPVPEnabled());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDifficultyLocked", new SetDifficultyLocked());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isPVPEnabled", new IsPVPEnabled());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isNetherEnabled", new IsNetherEnabled());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isFlightEnabled", new IsFlightEnabled());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getServerPort", new GetServerPort());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getServerIP", new GetServerIp());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isHardcore", new IsHardcore());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "areCommandBlocksEnabled", new AreCommandBlocksEnabled());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDifficulty", new SetDifficulty());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isModInstalled", new IsModInstalled());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "runCommand", new RunCommand());
    }

    @Override
    public int hashCode() {
        return MinecraftServerType.class.hashCode();
    }

    private static class AreCommandBlocksEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("areCommandBlocksEnabled", 0, arguments.size());
            }

            return new BooleanInstance(Sculk.server.areCommandBlocksEnabled());
        }
    }

    private static class GetServerIp extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getServerIP", 0, arguments.size());
            }

            return new StringInstance(Sculk.server.getServerIp());
        }
    }

    private static class GetServerPort extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getServerPort", 0, arguments.size());
            }

            return new IntegerInstance(Sculk.server.getServerPort());
        }
    }

    private static class IsFlightEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isFlightEnabled", 0, arguments.size());
            }

            return new BooleanInstance(Sculk.server.isFlightEnabled());
        }
    }

    private static class IsHardcore extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isHardcore", 0, arguments.size());
            }

            return new BooleanInstance(Sculk.server.isHardcore());
        }
    }

    private static class IsModInstalled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("isModInstalled", 1, arguments.size());
            }

            BuiltinClass modIDClass = arguments.get(0);

            if (!modIDClass.instanceOf(StringType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "isModInstalled", StringType.TYPE, modIDClass.getType());
            }

            String modID = modIDClass.toString();

            return new BooleanInstance(FabricLoader.getInstance().isModLoaded(modID));
        }
    }

    private static class IsNetherEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isNetherEnabled", 0, arguments.size());
            }

            return new BooleanInstance(Sculk.server.isNetherAllowed());
        }
    }

    private static class IsPVPEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isPVPEnabled", 0, arguments.size());
            }

            return new BooleanInstance(Sculk.server.isPvpEnabled());
        }
    }

    private static class RunCommand extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("runCommand", 1, arguments.size());
            }

            BuiltinClass commandClass = arguments.get(0);

            if (!commandClass.instanceOf(StringType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "runCommand", StringType.TYPE, commandClass.getType());
            }

            CommandDispatcher<ServerCommandSource> commandDispatcher = Sculk.server.getCommandManager().getDispatcher();
            ParseResults<ServerCommandSource> parseResults = commandDispatcher.parse(commandClass.toString(), Sculk.server.getCommandSource());
            int result;

            try {
                result = commandDispatcher.execute(parseResults);
            } catch (CommandSyntaxException e) {
                return new CommandResultInstance(new NullInstance(), new BooleanInstance(false), new StringInstance(e.getMessage()));
            }

            return new CommandResultInstance(new IntegerInstance(result), new BooleanInstance(true), new NullInstance());
        }
    }

    private static class SetDifficulty extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDifficulty", 1, arguments.size());
            }

            BuiltinClass difficulty = arguments.get(0);

            if (!difficulty.instanceOf(DifficultiesEnumType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "setDifficulty", DifficultiesEnumType.TYPE, difficulty.getType());
            }

            Sculk.server.setDifficulty(difficulty.toDifficulty(), true);

            return new NullInstance();
        }
    }

    private static class SetDifficultyLocked extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDifficultyLocked", 1, arguments.size());
            }

            BuiltinClass difficultyLocked = arguments.get(0);

            if (!difficultyLocked.instanceOf(BooleanType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "setDifficultyLocked", BooleanType.TYPE, difficultyLocked.getType());
            }

            Sculk.server.setDifficultyLocked(difficultyLocked.toBoolean());

            return new NullInstance();
        }
    }

    private static class SetPVPEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setPVPEnabled", 1, arguments.size());
            }

            BuiltinClass pvpEnabled = arguments.get(0);

            if (!pvpEnabled.instanceOf(BooleanType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "setPVPEnabled", BooleanType.TYPE, pvpEnabled.getType());
            }

            Sculk.server.setPvpEnabled(pvpEnabled.toBoolean());

            return new NullInstance();
        }
    }
}
