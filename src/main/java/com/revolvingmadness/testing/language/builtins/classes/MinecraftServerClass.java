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
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import net.minecraft.world.Difficulty;

import java.util.List;

public class MinecraftServerClass extends BaseClassExpressionNode {
    public MinecraftServerClass() {
        this.variableScope.declare(true, new IdentifierExpressionNode("setPVPEnabled"), new SetPVPEnabled());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDifficultyLocked"), new SetDifficultyLocked());
        this.variableScope.declare(true, new IdentifierExpressionNode("isPVPEnabled"), new MinecraftServerClass.IsPVPEnabledFunction());
        this.variableScope.declare(true, new IdentifierExpressionNode("isNetherEnabled"), new IsNetherEnabledFunction());
        this.variableScope.declare(true, new IdentifierExpressionNode("isFlightEnabled"), new MinecraftServerClass.IsFlightEnabledFunction());
        this.variableScope.declare(true, new IdentifierExpressionNode("getServerPort"), new MinecraftServerClass.GetServerPortFunction());
        this.variableScope.declare(true, new IdentifierExpressionNode("getServerIP"), new MinecraftServerClass.GetServerIpFunction());
        this.variableScope.declare(true, new IdentifierExpressionNode("isHardcore"), new MinecraftServerClass.IsHardcoreFunction());
        this.variableScope.declare(true, new IdentifierExpressionNode("areCommandBlocksEnabled"), new AreCommandBlocksEnabledFunction());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDifficulty"), new SetDifficulty());
    }

    @Override
    public String getType() {
        return "MinecraftServer";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof MinecraftServerClass;
    }

    @Override
    public int hashCode() {
        return MinecraftServerClass.class.hashCode();
    }

    private static class AreCommandBlocksEnabledFunction extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'areCommandBlocksEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(Testing.server.areCommandBlocksEnabled());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    private static class GetServerIpFunction extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getServerIP' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new StringClass(Testing.server.getServerIp());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    private static class GetServerPortFunction extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getServerPort' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(Testing.server.getServerPort());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    private static class IsFlightEnabledFunction extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isFlightEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(Testing.server.isFlightEnabled());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    private static class IsHardcoreFunction extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isHardcore' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(Testing.server.isHardcore());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    private static class IsNetherEnabledFunction extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isNetherEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(Testing.server.isNetherAllowed());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    private static class IsPVPEnabledFunction extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isPVPEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(Testing.server.isPvpEnabled());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    private static class SetDifficulty extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDifficulty' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode difficulty = arguments.get(0);

            if (!difficulty.getType().equals("String")) {
                throw new TypeError("Argument 1 for function 'setDifficulty' requires type 'string' but got '" + difficulty.getType() + "'");
            }

            Difficulty difficulty1 = Difficulty.byName(((StringClass) difficulty).value);

            if (difficulty1 == null) {
                throw new ValueError("Difficulty '" + difficulty + "' does not exist");
            }

            Testing.server.setDifficulty(difficulty1, true);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    private static class SetDifficultyLocked extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDifficultyLocked' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode difficultyLocked = arguments.get(0);

            if (!difficultyLocked.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDifficultyLocked' requires type 'boolean' but got '" + difficultyLocked.getType() + "'");
            }

            Testing.server.setDifficultyLocked(((BooleanClass) difficultyLocked).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    private static class SetPVPEnabled extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setPVPEnabled' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode pvpEnabled = arguments.get(0);

            if (!pvpEnabled.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setPVPEnabled' requires type 'boolean' but got '" + pvpEnabled.getType() + "'");
            }

            Testing.server.setPvpEnabled(((BooleanClass) pvpEnabled).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }
}
