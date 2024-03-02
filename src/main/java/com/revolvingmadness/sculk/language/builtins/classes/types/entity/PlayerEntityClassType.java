package com.revolvingmadness.sculk.language.builtins.classes.types.entity;

import com.revolvingmadness.sculk.language.GUIScreenHandler;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.instances.GUIInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.InventoryInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.WorldInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.item.ItemStackInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.GUIClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.InventoryClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.LivingEntityClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class PlayerEntityClassType extends BuiltinClassType {
    public static final PlayerEntityClassType TYPE = new PlayerEntityClassType();

    private PlayerEntityClassType() {
        super("PlayerEntity", LivingEntityClassType.TYPE);

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
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getEnderChestInventory", new GetEnderChestInventory());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setEnderChestInventory", new SetEnderChestInventory());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getInventory", new GetInventory());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setInventory", new SetInventory());
    }

    private static class AddExperienceLevels extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("addExperienceLevels", arguments, List.of(IntegerClassType.TYPE));

            long experienceLevels = arguments.get(0).toInteger();

            this.boundClass.toPlayerEntity().addExperienceLevels((int) experienceLevels);

            return new NullInstance();
        }
    }

    private static class AddExperiencePoints extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("addExperiencePoints", arguments, List.of(IntegerClassType.TYPE));

            long experience = arguments.get(0).toInteger();

            this.boundClass.toPlayerEntity().addExperience((int) experience);

            return new NullInstance();
        }
    }

    private static class GetEnderChestInventory extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getEnderChestInventory", arguments);

            return new InventoryInstance(this.boundClass.toPlayerEntity().getEnderChestInventory());
        }
    }

    private static class GetInventory extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getInventory", arguments);

            return new InventoryInstance(this.boundClass.toPlayerEntity().getInventory());
        }
    }

    private static class GetName extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getName", arguments);

            return new StringInstance(this.boundClass.toPlayerEntity().getName().getLiteralString());
        }
    }

    private static class GetStackInMainHand extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getStackInMainHand", arguments, List.of());

            return new ItemStackInstance(this.boundClass.toPlayerEntity().getMainHandStack());
        }
    }

    private static class GetStackInOffHand extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getStackInOffHand", arguments, List.of());

            return new ItemStackInstance(this.boundClass.toPlayerEntity().getOffHandStack());
        }
    }

    private static class GetUUID extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getUUID", arguments);

            return new StringInstance(this.boundClass.toPlayerEntity().getUuidAsString());
        }
    }

    private static class GetWorld extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getWorld", arguments);

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
            this.validateCall("isCreative", arguments);

            return new BooleanInstance(this.boundClass.toPlayerEntity().isCreative());
        }
    }

    private static class IsSpectator extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isSpectator", arguments);

            return new BooleanInstance(this.boundClass.toPlayerEntity().isSpectator());
        }
    }

    private static class OpenGUI extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("openGUI", arguments, List.of(GUIClassType.TYPE));

            GUIInstance gui = arguments.get(0).toGUI();

            this.boundClass.toPlayerEntity().openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) -> new GUIScreenHandler(interpreter, gui, syncId, playerInventory, gui.inventory), Text.literal(gui.title)));

            return new NullInstance();
        }
    }

    private static class SetEnderChestInventory extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("setEnderChestInventory", arguments, List.of(InventoryClassType.TYPE));

            Inventory inventory = arguments.get(0).toInventory();
            EnderChestInventory enderChestInventory = this.boundClass.toPlayerEntity().getEnderChestInventory();

            for (int i = 0; i < enderChestInventory.size(); i++) {
                enderChestInventory.setStack(i, inventory.getStack(i));
            }

            return new NullInstance();
        }
    }

    private static class SetInventory extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("setInventory", arguments, List.of(InventoryClassType.TYPE));

            Inventory playerInventory = this.boundClass.toPlayerEntity().getInventory();
            Inventory inventory = arguments.get(0).toInventory();

            for (int i = 0; i < playerInventory.size(); i++) {
                playerInventory.setStack(i, inventory.getStack(i));
            }

            return new NullInstance();
        }
    }
}
