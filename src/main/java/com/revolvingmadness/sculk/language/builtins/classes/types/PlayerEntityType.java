package com.revolvingmadness.sculk.language.builtins.classes.types;

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
    public static final PlayerEntityType TYPE = new PlayerEntityType();

    private PlayerEntityType() {
        super("PlayerEntity", LivingEntityType.TYPE);

        this.typeVariableScope.declare(List.of(TokenType.CONST), "addExperiencePoints", new AddExperiencePoints());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "addExperienceLevels", new AddExperienceLevels());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isCreative", new IsCreative());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isSpectator", new IsSpectator());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getName", new GetName());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getUUID", new GetUUID());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getWorld", new GetWorld());
    }

    private static class AddExperienceLevels extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("addExperienceLevels", arguments, List.of(IntegerType.TYPE));

            long experienceLevels = arguments.get(0).toInteger();

            this.boundClass.toPlayerEntity().addExperienceLevels((int) experienceLevels);

            return new NullInstance();
        }
    }

    private static class AddExperiencePoints extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("addExperiencePoints", arguments, List.of(IntegerType.TYPE));

            long experience = arguments.get(0).toInteger();

            this.boundClass.toPlayerEntity().addExperience((int) experience);

            return new NullInstance();
        }
    }

    private static class GetName extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getName", arguments);

            return new StringInstance(this.boundClass.toPlayerEntity().getName().getLiteralString());
        }
    }

    private static class GetUUID extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getUUID", arguments);

            return new StringInstance(this.boundClass.toPlayerEntity().getUuidAsString());
        }
    }

    private static class GetWorld extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getWorld", arguments);

            World world = this.boundClass.toPlayerEntity().getWorld();

            if (!(world instanceof ServerWorld serverWorld)) {
                throw new RuntimeException("World is on client");
            }

            return new WorldInstance(serverWorld);
        }
    }

    private static class IsCreative extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isCreative", arguments);

            return new BooleanInstance(this.boundClass.toPlayerEntity().isCreative());
        }
    }

    private static class IsSpectator extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isSpectator", arguments);

            return new BooleanInstance(this.boundClass.toPlayerEntity().isSpectator());
        }
    }
}
