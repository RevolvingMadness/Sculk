package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.language.builtins.classes.types.BooleanClass;
import com.revolvingmadness.testing.language.builtins.classes.types.IntegerClass;
import com.revolvingmadness.testing.language.builtins.classes.types.NullClass;
import com.revolvingmadness.testing.language.builtins.classes.types.StringClass;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import net.minecraft.world.Difficulty;

import java.util.List;

public class MinecraftServerClass extends BaseClassExpressionNode {
    public MinecraftServerClass() {
        this.variableScope.declare(true, "setPVPEnabled", new SetPVPEnabled());
        this.variableScope.declare(true, "setDifficultyLocked", new SetDifficultyLocked());
        this.variableScope.declare(true, "isPVPEnabled", new IsPVPEnabled());
        this.variableScope.declare(true, "isNetherEnabled", new IsNetherEnabled());
        this.variableScope.declare(true, "isFlightEnabled", new IsFlightEnabled());
        this.variableScope.declare(true, "getServerPort", new GetServerPort());
        this.variableScope.declare(true, "getServerIP", new GetServerIp());
        this.variableScope.declare(true, "isHardcore", new IsHardcore());
        this.variableScope.declare(true, "areCommandBlocksEnabled", new AreCommandBlocksEnabled());
        this.variableScope.declare(true, "setDifficulty", new SetDifficulty());
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof MinecraftServerClass;
    }

    @Override
    public String getType() {
        return "MinecraftServer";
    }

    @Override
    public int hashCode() {
        return MinecraftServerClass.class.hashCode();
    }

    private static class AreCommandBlocksEnabled extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("areCommandBlocksEnabled", 0, arguments.size());
            }

            return new BooleanClass(Testing.server.areCommandBlocksEnabled());
        }
    }

    private static class GetServerIp extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getServerIP", 0, arguments.size());
            }

            return new StringClass(Testing.server.getServerIp());
        }
    }

    private static class GetServerPort extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getServerPort", 0, arguments.size());
            }

            return new IntegerClass(Testing.server.getServerPort());
        }
    }

    private static class IsFlightEnabled extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isFlightEnabled", 0, arguments.size());
            }

            return new BooleanClass(Testing.server.isFlightEnabled());
        }
    }

    private static class IsHardcore extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isHardcore", 0, arguments.size());
            }

            return new BooleanClass(Testing.server.isHardcore());
        }
    }

    private static class IsNetherEnabled extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isNetherEnabled", 0, arguments.size());
            }

            return new BooleanClass(Testing.server.isNetherAllowed());
        }
    }

    private static class IsPVPEnabled extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isPVPEnabled", 0, arguments.size());
            }

            return new BooleanClass(Testing.server.isPvpEnabled());
        }
    }

    private static class SetDifficulty extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDifficulty", 1, arguments.size());
            }

            BaseClassExpressionNode difficulty = arguments.get(0);

            if (!difficulty.getType().equals("String")) {
                throw ErrorHolder.argumentRequiresType(1, "setDifficulty", "String", difficulty.getType());
            }

            Difficulty difficulty1 = Difficulty.byName(((StringClass) difficulty).value);

            if (difficulty1 == null) {
                throw ErrorHolder.difficultyDoesNotExist(((StringClass) difficulty).value);
            }

            Testing.server.setDifficulty(difficulty1, true);

            return new NullClass();
        }
    }

    private static class SetDifficultyLocked extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDifficultyLocked", 1, arguments.size());
            }

            BaseClassExpressionNode difficultyLocked = arguments.get(0);

            if (!difficultyLocked.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDifficultyLocked", "Boolean", difficultyLocked.getType());
            }

            Testing.server.setDifficultyLocked(((BooleanClass) difficultyLocked).value);

            return new NullClass();
        }
    }

    private static class SetPVPEnabled extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setPVPEnabled", 1, arguments.size());
            }

            BaseClassExpressionNode pvpEnabled = arguments.get(0);

            if (!pvpEnabled.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setPVPEnabled", "Boolean", pvpEnabled.getType());
            }

            Testing.server.setPvpEnabled(((BooleanClass) pvpEnabled).value);

            return new NullClass();
        }
    }
}
