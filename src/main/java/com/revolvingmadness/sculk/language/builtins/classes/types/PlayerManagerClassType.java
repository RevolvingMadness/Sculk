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
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

@SuppressWarnings("unused")
public class PlayerManagerClassType extends BuiltinClassType {
    public static final PlayerManagerClassType TYPE = new PlayerManagerClassType();

    private PlayerManagerClassType() {
        super("PlayerManager");

        try {
            this.addMethod("areCheatsEnabled", List.of());
            this.addMethod("getCurrentPlayerCount", List.of());
            this.addMethod("getMaxPlayerCount", List.of());
            this.addMethod("getSimulationDistance", List.of());
            this.addMethod("getViewDistance", List.of());
            this.addMethod("isWhitelistEnabled", List.of());
            this.addMethod("setCheatsEnabled", List.of(BooleanClassType.TYPE));
            this.addMethod("setSimulationDistance", List.of(IntegerClassType.TYPE));
            this.addMethod("setViewDistance", List.of(IntegerClassType.TYPE));
            this.addMethod("setWhitelistEnabled", List.of(BooleanClassType.TYPE));
            this.addMethod("getPlayer", List.of(StringClassType.TYPE));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public BuiltinClass areCheatsEnabled(BuiltinClass boundClass, BuiltinClass[] arguments) {
        return new BooleanInstance(boundClass.toPlayerManager().areCheatsAllowed());
    }

    public BuiltinClass getCurrentPlayerCount(BuiltinClass boundClass, BuiltinClass[] arguments) {
        return new IntegerInstance(boundClass.toPlayerManager().getCurrentPlayerCount());
    }

    public BuiltinClass getMaxPlayerCount(BuiltinClass boundClass, BuiltinClass[] arguments) {
        return new IntegerInstance(boundClass.toPlayerManager().getMaxPlayerCount());
    }

    public BuiltinClass getPlayer(BuiltinClass boundClass, BuiltinClass[] arguments) {
        String playerName = arguments[0].toString();

        ServerPlayerEntity serverPlayerEntity = boundClass.toPlayerManager().getPlayer(playerName);

        if (serverPlayerEntity == null) {
            throw new NameError("Cannot find player named '" + playerName + "'");
        }

        return new ServerPlayerEntityInstance(serverPlayerEntity);
    }

    public BuiltinClass getSimulationDistance(BuiltinClass boundClass, BuiltinClass[] arguments) {
        return new IntegerInstance(boundClass.toPlayerManager().getSimulationDistance());
    }

    public BuiltinClass getViewDistance(BuiltinClass boundClass, BuiltinClass[] arguments) {
        return new IntegerInstance(boundClass.toPlayerManager().getViewDistance());
    }

    public BuiltinClass isWhitelistEnabled(BuiltinClass boundClass, BuiltinClass[] arguments) {
        return new BooleanInstance(boundClass.toPlayerManager().isWhitelistEnabled());
    }

    public BuiltinClass setCheatsEnabled(BuiltinClass boundClass, BuiltinClass[] arguments) {
        boolean cheatsEnabled = arguments[0].toBoolean();

        boundClass.toPlayerManager().setCheatsAllowed(cheatsEnabled);

        return new NullInstance();
    }

    public BuiltinClass setSimulationDistance(BuiltinClass boundClass, BuiltinClass[] arguments) {
        long simulationDistance = arguments[0].toInteger();

        boundClass.toPlayerManager().setSimulationDistance((int) simulationDistance);

        return new NullInstance();
    }


    public BuiltinClass setViewDistance(BuiltinClass boundClass, BuiltinClass[] arguments) {
        long viewDistance = arguments[0].toInteger();

        boundClass.toPlayerManager().setViewDistance((int) viewDistance);

        return new NullInstance();
    }


    public BuiltinClass setWhitelistEnabled(BuiltinClass boundClass, BuiltinClass[] arguments) {
        boolean whitelistEnabled = arguments[0].toBoolean();

        boundClass.toPlayerManager().setWhitelistEnabled(whitelistEnabled);

        return new NullInstance();
    }
}