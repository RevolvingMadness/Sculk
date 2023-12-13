package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.BooleanExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.IntegerExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.NullExpressionNode;
import net.minecraft.server.PlayerManager;

import java.util.List;

public class PlayerManagerClass implements LiteralExpressionNode {
    public final PlayerManager playerManager;
    public final VariableScope variableScope;

    public PlayerManagerClass() {
        this.variableScope = new VariableScope();
        this.playerManager = Testing.server.getPlayerManager();
        this.variableScope.declare(true, new IdentifierExpressionNode("areCheatsEnabled"), this.new AreCheatsEnabled());
        this.variableScope.declare(true, new IdentifierExpressionNode("getCurrentPlayerCount"), this.new GetCurrentPlayerCount());
        this.variableScope.declare(true, new IdentifierExpressionNode("getMaxPlayerCount"), this.new GetMaxPlayerCount());
        this.variableScope.declare(true, new IdentifierExpressionNode("getSimulationDistance"), this.new GetSimulationDistance());
        this.variableScope.declare(true, new IdentifierExpressionNode("getViewDistance"), this.new GetViewDistance());
        this.variableScope.declare(true, new IdentifierExpressionNode("isWhitelistEnabled"), this.new IsWhitelistEnabled());
        this.variableScope.declare(true, new IdentifierExpressionNode("setCheatsEnabled"), this.new SetCheatsEnabled());
        this.variableScope.declare(true, new IdentifierExpressionNode("setSimulationDistance"), this.new SetSimulationDistance());
        this.variableScope.declare(true, new IdentifierExpressionNode("setViewDistance"), this.new SetViewDistance());
        this.variableScope.declare(true, new IdentifierExpressionNode("setWhitelistEnabled"), this.new SetWhitelistEnabled());
    }

    @Override
    public Variable getProperty(IdentifierExpressionNode propertyName) {
        return this.variableScope.getOrThrow(propertyName);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("PlayerManager");
    }

    private class AreCheatsEnabled implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'areCheatsEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(PlayerManagerClass.this.playerManager.areCheatsAllowed());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetCurrentPlayerCount implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getCurrentPlayerCount' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerExpressionNode(PlayerManagerClass.this.playerManager.getCurrentPlayerCount());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetMaxPlayerCount implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getMaxPlayerCount' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerExpressionNode(PlayerManagerClass.this.playerManager.getMaxPlayerCount());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetSimulationDistance implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getSimulationDistance' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerExpressionNode(PlayerManagerClass.this.playerManager.getSimulationDistance());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetViewDistance implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getViewDistance' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerExpressionNode(PlayerManagerClass.this.playerManager.getViewDistance());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class IsWhitelistEnabled implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isWhitelistEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(PlayerManagerClass.this.playerManager.isWhitelistEnabled());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetCheatsEnabled implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setCheatsEnabled' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode cheatsEnabled = arguments.get(0).interpret(script);

            if (!cheatsEnabled.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setCheatsEnabled' requires type 'boolean' but got '" + cheatsEnabled.getType() + "'");
            }

            PlayerManagerClass.this.playerManager.setCheatsAllowed(((BooleanExpressionNode) cheatsEnabled).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetSimulationDistance implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSimulationDistance' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode simulationDistance = arguments.get(0).interpret(script);

            if (!simulationDistance.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'setSimulationDistance' requires type 'int' but got '" + simulationDistance.getType() + "'");
            }

            PlayerManagerClass.this.playerManager.setSimulationDistance(((IntegerExpressionNode) simulationDistance).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetViewDistance implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setViewDistance' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode viewDistance = arguments.get(0).interpret(script);

            if (!viewDistance.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'setViewDistance' requires type 'int' but got '" + viewDistance.getType() + "'");
            }

            PlayerManagerClass.this.playerManager.setViewDistance(((IntegerExpressionNode) viewDistance).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetWhitelistEnabled implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setWhitelistEnabled' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode whitelistEnabled = arguments.get(0).interpret(script);

            if (!whitelistEnabled.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setWhitelistEnabled' requires type 'boolean' but got '" + whitelistEnabled.getType() + "'");
            }

            PlayerManagerClass.this.playerManager.setWhitelistEnabled(((BooleanExpressionNode) whitelistEnabled).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }
}
