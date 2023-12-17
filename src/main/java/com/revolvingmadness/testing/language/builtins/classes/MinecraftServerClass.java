package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.interpreter.errors.ValueError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.*;
import net.minecraft.world.Difficulty;

import java.util.List;

public class MinecraftServerClass implements LiteralExpressionNode {
    public final VariableScope variableScope;

    public MinecraftServerClass() {
        this.variableScope = new VariableScope();
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
    public Variable getProperty(IdentifierExpressionNode propertyName) {
        return this.variableScope.getOrThrow(propertyName);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("MinecraftServer");
    }

    private static class AreCommandBlocksEnabledFunction implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'areCommandBlocksEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(Testing.server.areCommandBlocksEnabled());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private static class GetServerIpFunction implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getServerIP' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new StringExpressionNode(Testing.server.getServerIp());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private static class GetServerPortFunction implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getServerPort' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerExpressionNode(Testing.server.getServerPort());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private static class IsFlightEnabledFunction implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isFlightEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(Testing.server.isFlightEnabled());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private static class IsHardcoreFunction implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isHardcore' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(Testing.server.isHardcore());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private static class IsNetherEnabledFunction implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isNetherEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(Testing.server.isNetherAllowed());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private static class IsPVPEnabledFunction implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isPVPEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(Testing.server.isPvpEnabled());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private static class SetDifficulty implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDifficulty' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode difficulty = arguments.get(0).interpret(script);

            if (!difficulty.getType().equals(new IdentifierExpressionNode("string"))) {
                throw new TypeError("Argument 1 for function 'setDifficulty' requires type 'string' but got '" + difficulty.getType() + "'");
            }

            Difficulty difficulty1 = Difficulty.byName(((StringExpressionNode) difficulty).value);

            if (difficulty1 == null) {
                throw new ValueError("Difficulty '" + difficulty + "' does not exist");
            }

            Testing.server.setDifficulty(difficulty1, true);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private static class SetDifficultyLocked implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDifficultyLocked' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode difficultyLocked = arguments.get(0).interpret(script);

            if (!difficultyLocked.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDifficultyLocked' requires type 'boolean' but got '" + difficultyLocked.getType() + "'");
            }

            Testing.server.setDifficultyLocked(((BooleanExpressionNode) difficultyLocked).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private static class SetPVPEnabled implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setPVPEnabled' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode pvpEnabled = arguments.get(0).interpret(script);

            if (!pvpEnabled.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setPVPEnabled' requires type 'boolean' but got '" + pvpEnabled.getType() + "'");
            }

            Testing.server.setPvpEnabled(((BooleanExpressionNode) pvpEnabled).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }
}
