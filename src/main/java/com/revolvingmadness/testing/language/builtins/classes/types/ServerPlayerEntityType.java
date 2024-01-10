package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import net.minecraft.world.GameMode;

import java.util.List;

public class ServerPlayerEntityType extends BuiltinType {
    public ServerPlayerEntityType() {
        super("ServerPlayerEntity", new PlayerEntityType());

        this.typeVariableScope.declare(true, "changeGameMode", new ChangeGameMode());
        this.typeVariableScope.declare(true, "dropSelectedItem", new DropSelectedItem());
        this.typeVariableScope.declare(true, "getIp", new GetIp());
        this.typeVariableScope.declare(true, "getViewDistance", new GetViewDistance());
        this.typeVariableScope.declare(true, "setExperienceLevels", new SetExperienceLevels());
        this.typeVariableScope.declare(true, "setExperiencePoints", new SetExperiencePoints());
    }

    private static class ChangeGameMode extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("changeGameMode", 1, arguments.size());
            }

            BuiltinClass gameMode = arguments.get(0);

            if (!gameMode.instanceOf(new StringType())) {
                throw ErrorHolder.argumentRequiresType(1, "changeGameMode", new StringType(), gameMode.getType());
            }

            GameMode gameMode1 = GameMode.byName(gameMode.toStringType(), null);

            if (gameMode1 == null) {
                throw ErrorHolder.gamemodeDoesNotExist(gameMode.toStringType());
            }

            this.boundClass.toServerPlayerEntity().changeGameMode(gameMode1);

            return new NullInstance();
        }
    }

    private static class DropSelectedItem extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("dropSelectedItem", 1, arguments.size());
            }

            BuiltinClass entireStack = arguments.get(0);

            if (!entireStack.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "dropSelectedItem", new BooleanType(), entireStack.getType());
            }

            this.boundClass.toServerPlayerEntity().dropSelectedItem(entireStack.toBoolean());

            return new NullInstance();
        }
    }

    private static class GetIp extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getIp", 0, arguments.size());
            }

            return new StringInstance(this.boundClass.toServerPlayerEntity().getIp());
        }
    }

    private static class GetViewDistance extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getViewDistance", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toServerPlayerEntity().getViewDistance());
        }
    }

    private static class SetExperienceLevels extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setExperienceLevels", 1, arguments.size());
            }

            BuiltinClass experienceLevel = arguments.get(0);

            if (!experienceLevel.instanceOf(new IntegerType())) {
                throw ErrorHolder.argumentRequiresType(1, "setExperienceLevels", new IntegerType(), experienceLevel.getType());
            }

            this.boundClass.toServerPlayerEntity().setExperienceLevel(experienceLevel.toInteger());

            return new NullInstance();
        }
    }

    private static class SetExperiencePoints extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setExperiencePoints", 1, arguments.size());
            }

            BuiltinClass experiencePoints = arguments.get(0);

            if (!experiencePoints.instanceOf(new IntegerType())) {
                throw ErrorHolder.argumentRequiresType(1, "setExperiencePoints", new IntegerType(), experiencePoints.getType());
            }

            this.boundClass.toServerPlayerEntity().setExperiencePoints(experiencePoints.toInteger());

            return new NullInstance();
        }
    }
}
