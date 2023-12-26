package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.language.builtins.classes.types.BooleanClass;
import com.revolvingmadness.testing.language.builtins.classes.types.IntegerClass;
import com.revolvingmadness.testing.language.builtins.classes.types.NullClass;
import com.revolvingmadness.testing.language.builtins.classes.types.StringClass;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;
import java.util.Objects;

public class PlayerManagerClass extends BaseClassExpressionNode {
    public final PlayerManager playerManager;

    public PlayerManagerClass() {
        this.playerManager = Testing.server.getPlayerManager();

        this.variableScope.declare(true, "areCheatsEnabled", this.new AreCheatsEnabled());
        this.variableScope.declare(true, "getCurrentPlayerCount", this.new GetCurrentPlayerCount());
        this.variableScope.declare(true, "getMaxPlayerCount", this.new GetMaxPlayerCount());
        this.variableScope.declare(true, "getSimulationDistance", this.new GetSimulationDistance());
        this.variableScope.declare(true, "getViewDistance", this.new GetViewDistance());
        this.variableScope.declare(true, "isWhitelistEnabled", this.new IsWhitelistEnabled());
        this.variableScope.declare(true, "setCheatsEnabled", this.new SetCheatsEnabled());
        this.variableScope.declare(true, "setSimulationDistance", this.new SetSimulationDistance());
        this.variableScope.declare(true, "setViewDistance", this.new SetViewDistance());
        this.variableScope.declare(true, "setWhitelistEnabled", this.new SetWhitelistEnabled());
        this.variableScope.declare(true, "getPlayer", this.new GetPlayer());
        this.variableScope.declare(true, "equalTo", new EqualTo());
        this.variableScope.declare(true, "notEqualTo", new NotEqualTo());
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

    public class AreCheatsEnabled extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("areCheatsEnabled", 0, arguments.size());
            }

            return new BooleanClass(PlayerManagerClass.this.playerManager.areCheatsAllowed());
        }
    }

    public class EqualTo extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("equalTo", 1, arguments.size());
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(PlayerManagerClass.this.equals(o));
        }
    }

    public class GetCurrentPlayerCount extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getCurrentPlayerCount", 0, arguments.size());
            }

            return new IntegerClass(PlayerManagerClass.this.playerManager.getCurrentPlayerCount());
        }
    }

    public class GetMaxPlayerCount extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getMaxPlayerCount", 0, arguments.size());
            }

            return new IntegerClass(PlayerManagerClass.this.playerManager.getMaxPlayerCount());
        }
    }

    public class GetPlayer extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("getPlayer", 0, arguments.size());
            }

            BaseClassExpressionNode playerName = arguments.get(0);

            if (!playerName.getType().equals("String")) {
                throw ErrorHolder.argumentRequiresType(1, "getPlayer", "String", playerName.getType());
            }

            ServerPlayerEntity serverPlayerEntity = PlayerManagerClass.this.playerManager.getPlayer(((StringClass) playerName).value);

            if (serverPlayerEntity == null) {
                throw ErrorHolder.thereIsNoPlayerNamed(((StringClass) playerName).value);
            }

            return new ServerPlayerEntityClass(serverPlayerEntity);
        }
    }

    public class GetSimulationDistance extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getSimulationDistance", 0, arguments.size());
            }

            return new IntegerClass(PlayerManagerClass.this.playerManager.getSimulationDistance());
        }
    }

    public class GetViewDistance extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getViewDistance", 0, arguments.size());
            }

            return new IntegerClass(PlayerManagerClass.this.playerManager.getViewDistance());
        }
    }

    public class IsWhitelistEnabled extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isWhitelistEnabled", 0, arguments.size());
            }

            return new BooleanClass(PlayerManagerClass.this.playerManager.isWhitelistEnabled());
        }
    }

    public class NotEqualTo extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("notEqualTo", 1, arguments.size());
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(!PlayerManagerClass.this.equals(o));
        }
    }

    public class SetCheatsEnabled extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setCheatsEnabled", 1, arguments.size());
            }

            BaseClassExpressionNode cheatsEnabled = arguments.get(0);

            if (!cheatsEnabled.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setCheatsEnabled", "Boolean", cheatsEnabled.getType());
            }

            PlayerManagerClass.this.playerManager.setCheatsAllowed(((BooleanClass) cheatsEnabled).value);

            return new NullClass();
        }
    }

    public class SetSimulationDistance extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSimulationDistance", 1, arguments.size());
            }

            BaseClassExpressionNode simulationDistance = arguments.get(0);

            if (!simulationDistance.getType().equals("Integer")) {
                throw ErrorHolder.argumentRequiresType(1, "setSimulationDistance", "Integer", simulationDistance.getType());
            }

            PlayerManagerClass.this.playerManager.setSimulationDistance(((IntegerClass) simulationDistance).value);

            return new NullClass();
        }
    }

    public class SetViewDistance extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setViewDistance", 1, arguments.size());
            }

            BaseClassExpressionNode viewDistance = arguments.get(0);

            if (!viewDistance.getType().equals("Integer")) {
                throw ErrorHolder.argumentRequiresType(1, "setViewDistance", "Integer", viewDistance.getType());
            }

            PlayerManagerClass.this.playerManager.setViewDistance(((IntegerClass) viewDistance).value);

            return new NullClass();
        }
    }

    public class SetWhitelistEnabled extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setWhitelistEnabled", 1, arguments.size());
            }

            BaseClassExpressionNode whitelistEnabled = arguments.get(0);

            if (!whitelistEnabled.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setWhitelistEnabled", "Boolean", whitelistEnabled.getType());
            }

            PlayerManagerClass.this.playerManager.setWhitelistEnabled(((BooleanClass) whitelistEnabled).value);

            return new NullClass();
        }
    }
}
