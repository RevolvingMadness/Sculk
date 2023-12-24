package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.language.builtins.classes.types.BooleanClass;
import com.revolvingmadness.testing.language.builtins.classes.types.IntegerClass;
import com.revolvingmadness.testing.language.builtins.classes.types.NullClass;
import com.revolvingmadness.testing.language.builtins.classes.types.StringClass;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.errors.ValueError;
import net.minecraft.world.Difficulty;

import java.util.List;

public class MinecraftServerClass extends BaseClassExpressionNode {
    public MinecraftServerClass() {
        this.variableScope.declare(true, "setPVPEnabled", new SetPVPEnabled());
        this.variableScope.declare(true, "setDifficultyLocked", new SetDifficultyLocked());
        this.variableScope.declare(true, "isPVPEnabled", new MinecraftServerClass.IsPVPEnabledFunction());
        this.variableScope.declare(true, "isNetherEnabled", new IsNetherEnabledFunction());
        this.variableScope.declare(true, "isFlightEnabled", new MinecraftServerClass.IsFlightEnabledFunction());
        this.variableScope.declare(true, "getServerPort", new MinecraftServerClass.GetServerPortFunction());
        this.variableScope.declare(true, "getServerIP", new MinecraftServerClass.GetServerIpFunction());
        this.variableScope.declare(true, "isHardcore", new MinecraftServerClass.IsHardcoreFunction());
        this.variableScope.declare(true, "areCommandBlocksEnabled", new AreCommandBlocksEnabledFunction());
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

    private static class AreCommandBlocksEnabledFunction extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'areCommandBlocksEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(Testing.server.areCommandBlocksEnabled());
        }
    }

    private static class GetServerIpFunction extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getServerIP' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new StringClass(Testing.server.getServerIp());
        }
    }

    private static class GetServerPortFunction extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getServerPort' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(Testing.server.getServerPort());
        }
    }

    private static class IsFlightEnabledFunction extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isFlightEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(Testing.server.isFlightEnabled());
        }
    }

    private static class IsHardcoreFunction extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isHardcore' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(Testing.server.isHardcore());
        }
    }

    private static class IsNetherEnabledFunction extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isNetherEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(Testing.server.isNetherAllowed());
        }
    }

    private static class IsPVPEnabledFunction extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isPVPEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(Testing.server.isPvpEnabled());
        }
    }

    private static class SetDifficulty extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDifficulty' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode difficulty = arguments.get(0);

            if (!difficulty.getType().equals("String")) {
                throw new TypeError("Argument 1 for function 'setDifficulty' requires type 'String' but got '" + difficulty.getType() + "'");
            }

            Difficulty difficulty1 = Difficulty.byName(((StringClass) difficulty).value);

            if (difficulty1 == null) {
                throw new ValueError("Difficulty '" + difficulty + "' does not exist");
            }

            Testing.server.setDifficulty(difficulty1, true);

            return new NullClass();
        }
    }

    private static class SetDifficultyLocked extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDifficultyLocked' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode difficultyLocked = arguments.get(0);

            if (!difficultyLocked.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDifficultyLocked' requires type 'Boolean' but got '" + difficultyLocked.getType() + "'");
            }

            Testing.server.setDifficultyLocked(((BooleanClass) difficultyLocked).value);

            return new NullClass();
        }
    }

    private static class SetPVPEnabled extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setPVPEnabled' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode pvpEnabled = arguments.get(0);

            if (!pvpEnabled.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setPVPEnabled' requires type 'Boolean' but got '" + pvpEnabled.getType() + "'");
            }

            Testing.server.setPvpEnabled(((BooleanClass) pvpEnabled).value);

            return new NullClass();
        }
    }
}
