package com.revolvingmadness.sculk.language.builtins.classes.types.entity;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.BooleanClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerClassType;
import com.revolvingmadness.sculk.language.builtins.enums.GameModesEnumType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.world.GameMode;

import java.util.List;

public class ServerPlayerEntityClassType extends BuiltinClassType {
    public static final ServerPlayerEntityClassType TYPE = new ServerPlayerEntityClassType();

    private ServerPlayerEntityClassType() {
        super("ServerPlayerEntity", PlayerEntityClassType.TYPE);

        this.typeVariableScope.declare(List.of(TokenType.CONST), "setGameMode", new SetGameMode());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "dropSelectedItem", new DropSelectedItem());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getViewDistance", new GetViewDistance());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setExperienceLevels", new SetExperienceLevels());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setExperiencePoints", new SetExperiencePoints());
    }

    private static class DropSelectedItem extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("dropSelectedItem", arguments, List.of(BooleanClassType.TYPE));

            boolean entireStack = arguments.get(0).toBoolean();

            this.boundClass.toServerPlayerEntity().dropSelectedItem(entireStack);

            return new NullInstance();
        }
    }

    private static class GetViewDistance extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getViewDistance", arguments);

            return new IntegerInstance(this.boundClass.toServerPlayerEntity().getViewDistance());
        }
    }

    private static class SetExperienceLevels extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("setExperienceLevels", arguments, List.of(IntegerClassType.TYPE));

            long experienceLevel = arguments.get(0).toInteger();

            this.boundClass.toServerPlayerEntity().setExperienceLevel((int) experienceLevel);

            return new NullInstance();
        }
    }

    private static class SetExperiencePoints extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("setExperiencePoints", arguments, List.of(IntegerClassType.TYPE));

            long experiencePoints = arguments.get(0).toInteger();

            this.boundClass.toServerPlayerEntity().setExperiencePoints((int) experiencePoints);

            return new NullInstance();
        }
    }

    private static class SetGameMode extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("setGameMode", arguments, List.of(GameModesEnumType.TYPE));

            GameMode gameMode = arguments.get(0).toGameMode();

            this.boundClass.toServerPlayerEntity().changeGameMode(gameMode);

            return new NullInstance();
        }
    }
}