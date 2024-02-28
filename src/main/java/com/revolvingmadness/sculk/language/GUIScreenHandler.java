package com.revolvingmadness.sculk.language;

import com.revolvingmadness.sculk.backend.Logger;
import com.revolvingmadness.sculk.backend.SculkScriptManager;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.instances.GUIInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.entity.PlayerEntityInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.BooleanClassType;
import com.revolvingmadness.sculk.language.errors.Error;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.SlotActionType;

import java.util.List;

public class GUIScreenHandler extends GenericContainerScreenHandler {
    public final GUIInstance gui;
    public final Interpreter interpreter;

    public GUIScreenHandler(Interpreter interpreter, GUIInstance gui, int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ScreenHandlerType.GENERIC_9X3, syncId, playerInventory, inventory, 3);

        this.interpreter = interpreter;
        this.gui = gui;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        this.gui.call(this.interpreter, "onClose", List.of(new PlayerEntityInstance(player), this.gui));
    }

    @Override
    public void onSlotClick(int slotIndex, int button, SlotActionType actionType, PlayerEntity player) {
        try {
            BuiltinClass result = this.gui.call(this.interpreter, "onSlotClick", List.of(new IntegerInstance(slotIndex), new IntegerInstance(button), this.gui, new PlayerEntityInstance(player)));

            if (!result.instanceOf(BooleanClassType.TYPE)) {
                throw ErrorHolder.functionRequiresReturnType("onSlotClick", result.type, BooleanClassType.TYPE);
            }

            if (!result.toBoolean()) {
                return;
            }
        } catch (Error error) {
            Logger.scriptError(SculkScriptManager.currentScript, error);
        }

        super.onSlotClick(slotIndex, button, actionType, player);
    }
}
