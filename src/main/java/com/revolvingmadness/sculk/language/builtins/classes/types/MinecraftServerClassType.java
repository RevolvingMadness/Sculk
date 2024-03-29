package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.instances.CommandResultInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.BooleanClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.builtins.enums.DifficultiesEnumType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.Difficulty;

import java.util.List;

public class MinecraftServerClassType extends BuiltinClassType {
    public static final MinecraftServerClassType TYPE = new MinecraftServerClassType();

    private MinecraftServerClassType() {
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
        return MinecraftServerClassType.class.hashCode();
    }

    private static class AreCommandBlocksEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("areCommandBlocksEnabled", arguments);

            return new BooleanInstance(Sculk.server.areCommandBlocksEnabled());
        }
    }

    private static class GetServerIp extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getServerIP", arguments);

            return new StringInstance(Sculk.server.getServerIp());
        }
    }

    private static class GetServerPort extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getServerPort", arguments);

            return new IntegerInstance(Sculk.server.getServerPort());
        }
    }

    private static class IsFlightEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isFlightEnabled", arguments);

            return new BooleanInstance(Sculk.server.isFlightEnabled());
        }
    }

    private static class IsHardcore extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isHardcore", arguments);

            return new BooleanInstance(Sculk.server.isHardcore());
        }
    }

    private static class IsModInstalled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isModInstalled", arguments, List.of(StringClassType.TYPE));

            String modID = arguments.get(0).toString();

            return new BooleanInstance(FabricLoader.getInstance().isModLoaded(modID));
        }
    }

    private static class IsNetherEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isNetherEnabled", arguments);

            return new BooleanInstance(Sculk.server.isNetherAllowed());
        }
    }

    private static class IsPVPEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isPVPEnabled", arguments);

            return new BooleanInstance(Sculk.server.isPvpEnabled());
        }
    }

    private static class RunCommand extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("runCommand", arguments, List.of(StringClassType.TYPE));
            String command = arguments.get(0).toString();

            CommandDispatcher<ServerCommandSource> commandDispatcher = Sculk.server.getCommandManager().getDispatcher();
            ParseResults<ServerCommandSource> parseResults = commandDispatcher.parse(command, Sculk.server.getCommandSource());
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
            this.validateCall("setDifficulty", arguments, List.of(DifficultiesEnumType.TYPE));

            Difficulty difficulty = arguments.get(0).toDifficulty();

            Sculk.server.setDifficulty(difficulty, true);

            return new NullInstance();
        }
    }

    private static class SetDifficultyLocked extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("setDifficultyLocked", arguments, List.of(BooleanClassType.TYPE));

            boolean difficultyLocked = arguments.get(0).toBoolean();

            Sculk.server.setDifficultyLocked(difficultyLocked);

            return new NullInstance();
        }
    }

    private static class SetPVPEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("setPVPEnabled", arguments, List.of(BooleanClassType.TYPE));

            boolean pvpEnabled = arguments.get(0).toBoolean();

            Sculk.server.setPvpEnabled(pvpEnabled);

            return new NullInstance();
        }
    }
}
