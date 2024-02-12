package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.ServerPlayerEntityInstance;
import com.revolvingmadness.sculk.language.errors.NameError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

public class PlayerManagerType extends BuiltinType {
    public static final PlayerManagerType TYPE = new PlayerManagerType();

    private PlayerManagerType() {
        super("PlayerManager");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "areCheatsEnabled", new AreCheatsEnabled());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getCurrentPlayerCount", new GetCurrentPlayerCount());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getMaxPlayerCount", new GetMaxPlayerCount());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getSimulationDistance", new GetSimulationDistance());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getViewDistance", new GetViewDistance());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isWhitelistEnabled", new IsWhitelistEnabled());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setCheatsEnabled", new SetCheatsEnabled());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setSimulationDistance", new SetSimulationDistance());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setViewDistance", new SetViewDistance());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setWhitelistEnabled", new SetWhitelistEnabled());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getPlayer", new GetPlayer());
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

            if (!playerName.instanceOf(StringType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "getPlayer", StringType.TYPE, playerName.getType());
            }

            ServerPlayerEntity serverPlayerEntity = this.boundClass.toPlayerManager().getPlayer(playerName.toString());

            if (serverPlayerEntity == null) {
                throw new NameError("Cannot find player named '" + playerName + "'");
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

            if (!cheatsEnabled.instanceOf(BooleanType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "setCheatsEnabled", BooleanType.TYPE, cheatsEnabled.getType());
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

            if (!simulationDistance.instanceOf(IntegerType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "setSimulationDistance", IntegerType.TYPE, simulationDistance.getType());
            }

            this.boundClass.toPlayerManager().setSimulationDistance((int) simulationDistance.toInteger());

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

            if (!viewDistance.instanceOf(IntegerType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "setViewDistance", IntegerType.TYPE, viewDistance.getType());
            }

            this.boundClass.toPlayerManager().setViewDistance((int) viewDistance.toInteger());

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

            if (!whitelistEnabled.instanceOf(BooleanType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "setWhitelistEnabled", BooleanType.TYPE, whitelistEnabled.getType());
            }

            this.boundClass.toPlayerManager().setWhitelistEnabled(whitelistEnabled.toBoolean());

            return new NullInstance();
        }
    }
}