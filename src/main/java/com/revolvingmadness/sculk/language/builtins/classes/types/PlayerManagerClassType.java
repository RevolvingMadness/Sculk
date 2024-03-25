package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.entity.ServerPlayerEntityInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.BooleanClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.errors.NameError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

@SuppressWarnings("unused")
public class PlayerManagerClassType extends BuiltinClassType {
    public static final PlayerManagerClassType TYPE = new PlayerManagerClassType();

    private PlayerManagerClassType() {
        super("PlayerManager");

        try {
            this.addGetterMethod("areCheatsEnabled", builtinClass -> new BooleanInstance(builtinClass.toPlayerManager().areCheatsAllowed()));
            this.addGetterMethod("getCurrentPlayerCount", builtinClass -> new IntegerInstance(builtinClass.toPlayerManager().getCurrentPlayerCount()));
            this.addGetterMethod("getMaxPlayerCount", builtinClass -> new IntegerInstance(builtinClass.toPlayerManager().getMaxPlayerCount()));
            this.addGetterMethod("getSimulationDistance", builtinClass -> new IntegerInstance(builtinClass.toPlayerManager().getSimulationDistance()));
            this.addGetterMethod("getViewDistance", builtinClass -> new IntegerInstance(builtinClass.toPlayerManager().getViewDistance()));
            this.addGetterMethod("isWhitelistEnabled", builtinClass -> new BooleanInstance(builtinClass.toPlayerManager().isWhitelistEnabled()));
            this.addMethod("setCheatsEnabled", List.of(BooleanClassType.TYPE));
            this.addMethod("setSimulationDistance", List.of(IntegerClassType.TYPE));
            this.addMethod("setViewDistance", List.of(IntegerClassType.TYPE));
            this.addMethod("setWhitelistEnabled", List.of(BooleanClassType.TYPE));
            this.addMethod("getPlayer", List.of(StringClassType.TYPE));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public BuiltinClass getPlayer(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        String playerName = arguments[0].toString();

        ServerPlayerEntity serverPlayerEntity = boundClass.toPlayerManager().getPlayer(playerName);

        if (serverPlayerEntity == null) {
            throw new NameError("Cannot find player named '" + playerName + "'");
        }

        return new ServerPlayerEntityInstance(serverPlayerEntity);
    }

    public BuiltinClass setCheatsEnabled(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        boolean cheatsEnabled = arguments[0].toBoolean();

        boundClass.toPlayerManager().setCheatsAllowed(cheatsEnabled);

        return new NullInstance();
    }

    public BuiltinClass setSimulationDistance(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        long simulationDistance = arguments[0].toInteger();

        boundClass.toPlayerManager().setSimulationDistance((int) simulationDistance);

        return new NullInstance();
    }

    public BuiltinClass setViewDistance(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        long viewDistance = arguments[0].toInteger();

        boundClass.toPlayerManager().setViewDistance((int) viewDistance);

        return new NullInstance();
    }

    public BuiltinClass setWhitelistEnabled(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        boolean whitelistEnabled = arguments[0].toBoolean();

        boundClass.toPlayerManager().setWhitelistEnabled(whitelistEnabled);

        return new NullInstance();
    }
}