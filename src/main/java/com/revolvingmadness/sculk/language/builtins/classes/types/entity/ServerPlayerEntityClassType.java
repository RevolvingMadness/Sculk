package com.revolvingmadness.sculk.language.builtins.classes.types.entity;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.BooleanClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerClassType;
import com.revolvingmadness.sculk.language.builtins.enums.GameModesEnumType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.world.GameMode;

import java.util.List;

@SuppressWarnings("unused")
public class ServerPlayerEntityClassType extends BuiltinClassType {
    public static final ServerPlayerEntityClassType TYPE = new ServerPlayerEntityClassType();

    private ServerPlayerEntityClassType() {
        super("ServerPlayerEntity", PlayerEntityClassType.TYPE);

        try {
            this.addMethod("setGameMode", List.of(GameModesEnumType.TYPE));
            this.addMethod("dropSelectedItem", List.of(BooleanClassType.TYPE));
            this.addNoArgMethod("getViewDistance", builtinClass -> new IntegerInstance(builtinClass.toServerPlayerEntity().getViewDistance()));
            this.addMethod("setExperienceLevels", List.of(IntegerClassType.TYPE));
            this.addMethod("setExperiencePoints", List.of(IntegerClassType.TYPE));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public BuiltinClass dropSelectedItem(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        boolean entireStack = arguments[0].toBoolean();

        boundClass.toServerPlayerEntity().dropSelectedItem(entireStack);

        return new NullInstance();
    }

    public BuiltinClass setExperienceLevels(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        long experienceLevel = arguments[0].toInteger();

        boundClass.toServerPlayerEntity().setExperienceLevel((int) experienceLevel);

        return new NullInstance();
    }

    public BuiltinClass setExperiencePoints(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        long experiencePoints = arguments[0].toInteger();

        boundClass.toServerPlayerEntity().setExperiencePoints((int) experiencePoints);

        return new NullInstance();
    }

    public BuiltinClass setGameMode(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        GameMode gameMode = arguments[0].toGameMode();

        boundClass.toServerPlayerEntity().changeGameMode(gameMode);

        return new NullInstance();
    }
}
