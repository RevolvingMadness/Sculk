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
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;
import java.util.Objects;

public class PlayerManagerClass extends BaseClassExpressionNode {
    public final PlayerManager playerManager;

    public PlayerManagerClass() {
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
        this.variableScope.declare(true, new IdentifierExpressionNode("getPlayer"), this.new GetPlayer());
        this.variableScope.declare(true, new IdentifierExpressionNode("equalTo"), new EqualTo());
        this.variableScope.declare(true, new IdentifierExpressionNode("notEqualTo"), new NotEqualTo());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        PlayerManagerClass that = (PlayerManagerClass) o;
        return Objects.equals(this.playerManager, that.playerManager);
    }

    @Override
    public String getType() {
        return "PlayerManager";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.playerManager);
    }

    public class AreCheatsEnabled extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'areCheatsEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(PlayerManagerClass.this.playerManager.areCheatsAllowed());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class EqualTo extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'equalTo' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(PlayerManagerClass.this.equals(o));
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class GetCurrentPlayerCount extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getCurrentPlayerCount' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(PlayerManagerClass.this.playerManager.getCurrentPlayerCount());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class GetMaxPlayerCount extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getMaxPlayerCount' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(PlayerManagerClass.this.playerManager.getMaxPlayerCount());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class GetPlayer extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'getPlayer' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode playerName = arguments.get(0);

            if (!playerName.getType().equals("String")) {
                throw new TypeError("Argument 1 for function 'getPlayer' requires type 'String' but got '" + playerName.getType() + "'");
            }

            ServerPlayerEntity serverPlayerEntity = PlayerManagerClass.this.playerManager.getPlayer(((StringClass) playerName).value);

            if (serverPlayerEntity == null) {
                throw new ValueError("There is no player named '" + playerName + "'");
            }

            return new ServerPlayerEntityClass(serverPlayerEntity);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class GetSimulationDistance extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getSimulationDistance' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(PlayerManagerClass.this.playerManager.getSimulationDistance());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class GetViewDistance extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getViewDistance' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(PlayerManagerClass.this.playerManager.getViewDistance());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsWhitelistEnabled extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isWhitelistEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(PlayerManagerClass.this.playerManager.isWhitelistEnabled());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class NotEqualTo extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'notEqualTo' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(!PlayerManagerClass.this.equals(o));
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SetCheatsEnabled extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setCheatsEnabled' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode cheatsEnabled = arguments.get(0);

            if (!cheatsEnabled.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setCheatsEnabled' requires type 'Boolean' but got '" + cheatsEnabled.getType() + "'");
            }

            PlayerManagerClass.this.playerManager.setCheatsAllowed(((BooleanClass) cheatsEnabled).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SetSimulationDistance extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSimulationDistance' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode simulationDistance = arguments.get(0);

            if (!simulationDistance.getType().equals("Integer")) {
                throw new TypeError("Argument 1 for function 'setSimulationDistance' requires type 'Integer' but got '" + simulationDistance.getType() + "'");
            }

            PlayerManagerClass.this.playerManager.setSimulationDistance(((IntegerClass) simulationDistance).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SetViewDistance extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setViewDistance' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode viewDistance = arguments.get(0);

            if (!viewDistance.getType().equals("Integer")) {
                throw new TypeError("Argument 1 for function 'setViewDistance' requires type 'Integer' but got '" + viewDistance.getType() + "'");
            }

            PlayerManagerClass.this.playerManager.setViewDistance(((IntegerClass) viewDistance).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SetWhitelistEnabled extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setWhitelistEnabled' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode whitelistEnabled = arguments.get(0);

            if (!whitelistEnabled.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setWhitelistEnabled' requires type 'Boolean' but got '" + whitelistEnabled.getType() + "'");
            }

            PlayerManagerClass.this.playerManager.setWhitelistEnabled(((BooleanClass) whitelistEnabled).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }
}
