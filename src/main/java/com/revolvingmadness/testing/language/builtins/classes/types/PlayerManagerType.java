package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.ServerPlayerEntityInstance;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

public class PlayerManagerType extends BuiltinType {


    public PlayerManagerType() {
        super("PlayerManager");

        this.typeVariableScope.declare(true, "areCheatsEnabled", new AreCheatsEnabled());
        this.typeVariableScope.declare(true, "getCurrentPlayerCount", new GetCurrentPlayerCount());
        this.typeVariableScope.declare(true, "getMaxPlayerCount", new GetMaxPlayerCount());
        this.typeVariableScope.declare(true, "getSimulationDistance", new GetSimulationDistance());
        this.typeVariableScope.declare(true, "getViewDistance", new GetViewDistance());
        this.typeVariableScope.declare(true, "isWhitelistEnabled", new IsWhitelistEnabled());
        this.typeVariableScope.declare(true, "setCheatsEnabled", new SetCheatsEnabled());
        this.typeVariableScope.declare(true, "setSimulationDistance", new SetSimulationDistance());
        this.typeVariableScope.declare(true, "setViewDistance", new SetViewDistance());
        this.typeVariableScope.declare(true, "setWhitelistEnabled", new SetWhitelistEnabled());
        this.typeVariableScope.declare(true, "getPlayer", new GetPlayer());
    }

    private static class AreCheatsEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("areCheatsEnabled", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toPlayerManager().areCheatsAllowed());
        }
    }

    private static class GetCurrentPlayerCount extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getCurrentPlayerCount", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toPlayerManager().getCurrentPlayerCount());
        }
    }

    private static class GetMaxPlayerCount extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getMaxPlayerCount", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toPlayerManager().getMaxPlayerCount());
        }
    }

    private static class GetPlayer extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("getPlayer", 0, arguments.size());
            }

            BuiltinClass playerName = arguments.get(0);

            if (!playerName.instanceOf(new StringType())) {
                throw ErrorHolder.argumentRequiresType(1, "getPlayer", new StringType(), playerName.getType());
            }

            ServerPlayerEntity serverPlayerEntity = this.boundClass.toPlayerManager().getPlayer(playerName.toStringType());

            if (serverPlayerEntity == null) {
                throw ErrorHolder.thereIsNoPlayerNamed(playerName.toStringType());
            }

            return new ServerPlayerEntityInstance(serverPlayerEntity);
        }
    }

    private static class GetSimulationDistance extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getSimulationDistance", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toPlayerManager().getSimulationDistance());
        }
    }

    private static class GetViewDistance extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getViewDistance", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toPlayerManager().getViewDistance());
        }
    }

    private static class IsWhitelistEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isWhitelistEnabled", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toPlayerManager().isWhitelistEnabled());
        }
    }

    private static class SetCheatsEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setCheatsEnabled", 1, arguments.size());
            }

            BuiltinClass cheatsEnabled = arguments.get(0);

            if (!cheatsEnabled.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setCheatsEnabled", new BooleanType(), cheatsEnabled.getType());
            }

            this.boundClass.toPlayerManager().setCheatsAllowed(cheatsEnabled.toBoolean());

            return new NullInstance();
        }
    }

    private static class SetSimulationDistance extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSimulationDistance", 1, arguments.size());
            }

            BuiltinClass simulationDistance = arguments.get(0);

            if (!simulationDistance.instanceOf(new IntegerType())) {
                throw ErrorHolder.argumentRequiresType(1, "setSimulationDistance", new IntegerType(), simulationDistance.getType());
            }

            this.boundClass.toPlayerManager().setSimulationDistance(simulationDistance.toInteger());

            return new NullInstance();
        }
    }

    private static class SetViewDistance extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setViewDistance", 1, arguments.size());
            }

            BuiltinClass viewDistance = arguments.get(0);

            if (!viewDistance.instanceOf(new IntegerType())) {
                throw ErrorHolder.argumentRequiresType(1, "setViewDistance", new IntegerType(), viewDistance.getType());
            }

            this.boundClass.toPlayerManager().setViewDistance(viewDistance.toInteger());

            return new NullInstance();
        }
    }

    private static class SetWhitelistEnabled extends BuiltinMethod {

        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setWhitelistEnabled", 1, arguments.size());
            }

            BuiltinClass whitelistEnabled = arguments.get(0);

            if (!whitelistEnabled.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setWhitelistEnabled", new BooleanType(), whitelistEnabled.getType());
            }

            this.boundClass.toPlayerManager().setWhitelistEnabled(whitelistEnabled.toBoolean());

            return new NullInstance();
        }
    }
}