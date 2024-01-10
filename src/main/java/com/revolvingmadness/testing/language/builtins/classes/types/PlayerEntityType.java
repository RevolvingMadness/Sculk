package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.testing.language.interpreter.Interpreter;

import java.util.List;

public class PlayerEntityType extends BuiltinType {
    public PlayerEntityType() {
        super("PlayerEntity", new LivingEntityType());

        this.typeVariableScope.declare(true, "addExperiencePoints", new AddExperiencePoints());
        this.typeVariableScope.declare(true, "addExperienceLevels", new AddExperienceLevels());
        this.typeVariableScope.declare(true, "isCreative", new IsCreative());
        this.typeVariableScope.declare(true, "isSpectator", new IsSpectator());
        this.typeVariableScope.declare(true, "getName", new GetName());
        this.typeVariableScope.declare(true, "getUUID", new GetUUID());
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

            this.boundClass.toPlayerEntity().addExperienceLevels(experienceLevels.toInteger());

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

            this.boundClass.toPlayerEntity().addExperience(experience.toInteger());

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
