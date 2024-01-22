package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class ServerPlayerEntityType extends BuiltinType {
    public ServerPlayerEntityType() {
        super("ServerPlayerEntity", new PlayerEntityType());

        this.typeVariableScope.declare(List.of(TokenType.CONST), "changeGameMode", new ChangeGameMode());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "dropSelectedItem", new DropSelectedItem());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getViewDistance", new GetViewDistance());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setExperienceLevels", new SetExperienceLevels());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setExperiencePoints", new SetExperiencePoints());
    }

    private static class ChangeGameMode extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("changeGameMode", 1, arguments.size());
            }

            BuiltinClass gameMode = arguments.get(0);

            if (!gameMode.instanceOf(new GameModesEnumType())) {
                throw ErrorHolder.argumentRequiresType(1, "changeGameMode", new GameModesEnumType(), gameMode.getType());
            }

            this.boundClass.toServerPlayerEntity().changeGameMode(gameMode.toGameMode());

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

            this.boundClass.toServerPlayerEntity().setExperienceLevel((int) experienceLevel.toInteger());

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

            this.boundClass.toServerPlayerEntity().setExperiencePoints((int) experiencePoints.toInteger());

            return new NullInstance();
        }
    }
}
