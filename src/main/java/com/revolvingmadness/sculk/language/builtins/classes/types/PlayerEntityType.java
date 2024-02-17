package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.GUIScreenHandler;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.*;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
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
        this.typeVariableScope.declare(List.of(TokenType.CONST), "openGUI", new OpenGUI());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getStackInMainHand", new GetStackInMainHand());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getStackInOffHand", new GetStackInOffHand());
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

    private static class GetStackInMainHand extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getStackInMainHand", arguments, List.of());

            return new ItemStackInstance(this.boundClass.toPlayerEntity().getMainHandStack());
        }
    }

    private static class GetStackInOffHand extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getStackInOffHand", arguments, List.of());

            return new ItemStackInstance(this.boundClass.toPlayerEntity().getOffHandStack());
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

    private static class OpenGUI extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("openGUI", arguments, List.of(GUIType.TYPE));

            GUIInstance gui = arguments.get(0).toGUI();

            this.boundClass.toPlayerEntity().openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) -> new GUIScreenHandler(interpreter, gui, syncId, playerInventory, gui.inventory), Text.literal(gui.title)));

            return new NullInstance();
        }
    }
}
