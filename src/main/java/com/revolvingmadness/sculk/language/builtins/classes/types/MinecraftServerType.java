package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.fabricmc.loader.api.FabricLoader;

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
        this.typeVariableScope.declare(List.of(TokenType.CONST), "equalTo", new EqualTo());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isModInstalled", new IsModInstalled());
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

    private static class EqualTo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("equalTo", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new MinecraftServerType())) {
                return new BooleanInstance(other.toMinecraftServerInstance().equals(this.boundClass.toMinecraftServerInstance()));
            }

            return new BooleanInstance(false);
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

            if (!modIDClass.instanceOf(new StringType())) {
                throw ErrorHolder.argumentRequiresType(1, "isModInstalled", new StringType(), modIDClass.getType());
            }

            String modID = modIDClass.toStringType();

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

    private static class SetDifficulty extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDifficulty", 1, arguments.size());
            }

            BuiltinClass difficulty = arguments.get(0);

            if (!difficulty.instanceOf(new DifficultiesEnumType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDifficulty", new DifficultiesEnumType(), difficulty.getType());
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

            if (!difficultyLocked.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDifficultyLocked", new BooleanType(), difficultyLocked.getType());
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

            if (!pvpEnabled.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setPVPEnabled", new BooleanType(), pvpEnabled.getType());
            }

            Sculk.server.setPvpEnabled(pvpEnabled.toBoolean());

            return new NullInstance();
        }
    }
}
