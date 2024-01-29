package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.WorldInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.util.List;

public class PlayerEntityType extends BuiltinType {
    public PlayerEntityType() {
        super("PlayerEntity", new LivingEntityType());

        this.typeVariableScope.declare(List.of(TokenType.CONST), "addExperiencePoints", new AddExperiencePoints());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "addExperienceLevels", new AddExperienceLevels());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isCreative", new IsCreative());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isSpectator", new IsSpectator());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getName", new GetName());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getUUID", new GetUUID());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getWorld", new GetWorld());
    }

    private static class GetWorld extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getWorld", 0, arguments.size());
            }

            World world = this.boundClass.toPlayerEntity().getWorld();

            if (!(world instanceof ServerWorld serverWorld)) {
                throw new RuntimeException("World is on client");
            }

            return new WorldInstance(serverWorld);
        }
    }

    private static class AddExperienceLevels extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("addExperienceLevels", 1, arguments.size());
            }

            BuiltinClass experienceLevels = arguments.get(0);

            if (!experienceLevels.instanceOf(new IntegerType())) {
                throw ErrorHolder.argumentRequiresType(1, "addExperienceLevels", new IntegerType(), experienceLevels.getType());
            }

            this.boundClass.toPlayerEntity().addExperienceLevels((int) experienceLevels.toInteger());

            return new NullInstance();
        }
    }

    private static class AddExperiencePoints extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("addExperiencePoints", 1, arguments.size());
            }

            BuiltinClass experience = arguments.get(0);

            if (!experience.instanceOf(new IntegerType())) {
                throw ErrorHolder.argumentRequiresType(1, "addExperiencePoints", new IntegerType(), experience.getType());
            }

            this.boundClass.toPlayerEntity().addExperience((int) experience.toInteger());

            return new NullInstance();
        }
    }

    private static class GetName extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getName", 0, arguments.size());
            }

            return new StringInstance(this.boundClass.toPlayerEntity().getName().getLiteralString());
        }
    }

    private static class GetUUID extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getUUID", 0, arguments.size());
            }

            return new StringInstance(this.boundClass.toPlayerEntity().getUuidAsString());
        }
    }

    private static class IsCreative extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isCreative", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toPlayerEntity().isCreative());
        }
    }

    private static class IsSpectator extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isSpectator", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toPlayerEntity().isSpectator());
        }
    }
}
