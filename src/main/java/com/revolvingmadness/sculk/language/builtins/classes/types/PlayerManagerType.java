package com.revolvingmadness.sculk.language.builtins.classes.types;

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
            this.validateCall("areCheatsEnabled", arguments);

            return new BooleanInstance(this.boundClass.toPlayerManager().areCheatsAllowed());
        }
    }

    private static class GetCurrentPlayerCount extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getCurrentPlayerCount", arguments);

            return new IntegerInstance(this.boundClass.toPlayerManager().getCurrentPlayerCount());
        }
    }

    private static class GetMaxPlayerCount extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getMaxPlayerCount", arguments);

            return new IntegerInstance(this.boundClass.toPlayerManager().getMaxPlayerCount());
        }
    }

    private static class GetPlayer extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getPlayer", arguments, List.of(StringType.TYPE));

            String playerName = arguments.get(0).toString();

            ServerPlayerEntity serverPlayerEntity = this.boundClass.toPlayerManager().getPlayer(playerName);

            if (serverPlayerEntity == null) {
                throw new NameError("Cannot find player named '" + playerName + "'");
            }

            return new ServerPlayerEntityInstance(serverPlayerEntity);
        }
    }

    private static class GetSimulationDistance extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getSimulationDistance", arguments);

            return new IntegerInstance(this.boundClass.toPlayerManager().getSimulationDistance());
        }
    }

    private static class GetViewDistance extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getViewDistance", arguments);

            return new IntegerInstance(this.boundClass.toPlayerManager().getViewDistance());
        }
    }

    private static class IsWhitelistEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isWhitelistEnabled", arguments);

            return new BooleanInstance(this.boundClass.toPlayerManager().isWhitelistEnabled());
        }
    }

    private static class SetCheatsEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("setCheatsEnabled", arguments, List.of(BooleanType.TYPE));

            boolean cheatsEnabled = arguments.get(0).toBoolean();

            this.boundClass.toPlayerManager().setCheatsAllowed(cheatsEnabled);

            return new NullInstance();
        }
    }

    private static class SetSimulationDistance extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("setSimulationDistance", arguments, List.of(IntegerType.TYPE));

            long simulationDistance = arguments.get(0).toInteger();

            this.boundClass.toPlayerManager().setSimulationDistance((int) simulationDistance);

            return new NullInstance();
        }
    }

    private static class SetViewDistance extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("setViewDistance", arguments, List.of(IntegerType.TYPE));

            long viewDistance = arguments.get(0).toInteger();

            this.boundClass.toPlayerManager().setViewDistance((int) viewDistance);

            return new NullInstance();
        }
    }

    private static class SetWhitelistEnabled extends BuiltinMethod {

        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("setWhitelistEnabled", arguments, List.of(BooleanType.TYPE));

            boolean whitelistEnabled = arguments.get(0).toBoolean();

            this.boundClass.toPlayerManager().setWhitelistEnabled(whitelistEnabled);

            return new NullInstance();
        }
    }
}