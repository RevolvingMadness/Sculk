package com.revolvingmadness.sculk.language.builtins.classes.types.entity;

import com.revolvingmadness.sculk.language.GUIScreenHandler;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
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
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

@SuppressWarnings("unused")
public class PlayerEntityClassType extends BuiltinClassType {
    public static final PlayerEntityClassType TYPE = new PlayerEntityClassType();

    private PlayerEntityClassType() {
        super("PlayerEntity", LivingEntityClassType.TYPE);

        try {
            this.addMethod("addExperiencePoints", List.of(IntegerClassType.TYPE));
            this.addMethod("addExperienceLevels", List.of(IntegerClassType.TYPE));
            this.addNoArgMethod("isCreative", builtinClass -> new BooleanInstance(builtinClass.toPlayerEntity().isCreative()));
            this.addNoArgMethod("isSpectator", builtinClass -> new BooleanInstance(builtinClass.toPlayerEntity().isSpectator()));
            this.addNoArgMethod("getName", builtinClass -> new StringInstance(builtinClass.toPlayerEntity().getName().getString()));
            this.addNoArgMethod("getUUID", builtinClass -> new StringInstance(builtinClass.toPlayerEntity().getUuidAsString()));
            this.addNoArgMethod("getWorld", builtinClass -> {
                World world = builtinClass.toPlayerEntity().getWorld();

                if (!(world instanceof ServerWorld serverWorld)) {
                    throw new RuntimeException("World is on client");
                }

                return new WorldInstance(serverWorld);
            });
            this.addMethod("openGUI", List.of(GUIClassType.TYPE));
            this.addNoArgMethod("getStackInMainHand", builtinClass -> new ItemStackInstance(builtinClass.toPlayerEntity().getMainHandStack()));
            this.addNoArgMethod("getStackInOffHand", builtinClass -> new ItemStackInstance(builtinClass.toPlayerEntity().getOffHandStack()));
            this.addNoArgMethod("getEnderChestInventory", builtinClass -> new InventoryInstance(builtinClass.toPlayerEntity().getEnderChestInventory()));
            this.addMethod("setEnderChestInventory", List.of(InventoryClassType.TYPE));
            this.addNoArgMethod("getInventory", builtinClass -> new InventoryInstance(builtinClass.toPlayerEntity().getInventory()));
            this.addMethod("setInventory", List.of(InventoryClassType.TYPE));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public BuiltinClass addExperienceLevels(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        long experienceLevels = arguments[0].toInteger();

        boundClass.toPlayerEntity().addExperienceLevels((int) experienceLevels);

        return new NullInstance();
    }

    public BuiltinClass addExperiencePoints(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        long experience = arguments[0].toInteger();

        boundClass.toPlayerEntity().addExperience((int) experience);

        return new NullInstance();
    }

    public BuiltinClass openGUI(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        GUIInstance gui = arguments[0].toGUI();

        boundClass.toPlayerEntity().openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) -> new GUIScreenHandler(interpreter, gui, syncId, playerInventory, gui.inventory), Text.literal(gui.title)));

        return new NullInstance();
    }

    public BuiltinClass setEnderChestInventory(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        Inventory inventory = arguments[0].toInventory();
        EnderChestInventory enderChestInventory = boundClass.toPlayerEntity().getEnderChestInventory();

        for (int i = 0; i < enderChestInventory.size(); i++) {
            enderChestInventory.setStack(i, inventory.getStack(i));
        }

        return new NullInstance();
    }

    public BuiltinClass setInventory(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        Inventory playerInventory = boundClass.toPlayerEntity().getInventory();
        Inventory inventory = arguments[0].toInventory();

        for (int i = 0; i < playerInventory.size(); i++) {
            playerInventory.setStack(i, inventory.getStack(i));
        }

        return new NullInstance();
    }
}
