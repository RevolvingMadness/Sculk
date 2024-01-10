package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.lexer.TokenType;
import net.minecraft.world.Difficulty;

import java.util.List;

public class MinecraftServerType extends BuiltinType {
    public MinecraftServerType() {
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

            return new BooleanInstance(Testing.server.areCommandBlocksEnabled());
        }
    }

    private static class GetServerIp extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getServerIP", 0, arguments.size());
            }

            return new StringInstance(Testing.server.getServerIp());
        }
    }

    private static class GetServerPort extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getServerPort", 0, arguments.size());
            }

            return new IntegerInstance(Testing.server.getServerPort());
        }
    }

    private static class IsFlightEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isFlightEnabled", 0, arguments.size());
            }

            return new BooleanInstance(Testing.server.isFlightEnabled());
        }
    }

    private static class IsHardcore extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isHardcore", 0, arguments.size());
            }

            return new BooleanInstance(Testing.server.isHardcore());
        }
    }

    private static class IsNetherEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isNetherEnabled", 0, arguments.size());
            }

            return new BooleanInstance(Testing.server.isNetherAllowed());
        }
    }

    private static class IsPVPEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isPVPEnabled", 0, arguments.size());
            }

            return new BooleanInstance(Testing.server.isPvpEnabled());
        }
    }

    private static class SetDifficulty extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDifficulty", 1, arguments.size());
            }

            BuiltinClass difficulty = arguments.get(0);

            if (!difficulty.instanceOf(new StringType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDifficulty", new StringType(), difficulty.getType());
            }

            Difficulty difficulty1 = Difficulty.byName(difficulty.toStringType());

            if (difficulty1 == null) {
                throw ErrorHolder.difficultyDoesNotExist(difficulty.toStringType());
            }

            Testing.server.setDifficulty(difficulty1, true);

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

            if (!difficultyLocked.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDifficultyLocked", new BooleanType(), difficultyLocked.getType());
            }

            Testing.server.setDifficultyLocked(difficultyLocked.toBoolean());

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

            if (!pvpEnabled.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setPVPEnabled", new BooleanType(), pvpEnabled.getType());
            }

            Testing.server.setPvpEnabled(pvpEnabled.toBoolean());

            return new NullInstance();
        }
    }
}
