package com.revolvingmadness.testing.language;

import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.events.SendChatMessageCallback;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.instances.BlockPosInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.LivingEntityInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.ServerPlayerEntityInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.testing.language.builtins.classes.types.BooleanType;
import com.revolvingmadness.testing.language.errors.Error;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.minecraft.util.ActionResult;

import java.util.ArrayList;
import java.util.List;

public class EventHolder {
    public static List<Event> onSendChatMessage = new ArrayList<>();
    public static List<Event> onSleep = new ArrayList<>();

    public static void clearEvents() {
        EventHolder.onSendChatMessage.clear();
        EventHolder.onSleep.clear();
    }

    public static void registerEvents() {
        EntitySleepEvents.START_SLEEPING.register((livingEntity, sleepingPos) -> {
            try {
                for (Event event : EventHolder.onSleep) {
                    event.execute(List.of(new LivingEntityInstance(livingEntity), new BlockPosInstance(sleepingPos)));
                }
            } catch (Error error) {
                Logger.error(error.message);
            }
        });

        SendChatMessageCallback.EVENT.register((player, message) -> {
            try {
                for (Event event : EventHolder.onSendChatMessage) {
                    BuiltinClass eventResultClass = event.execute(List.of(new ServerPlayerEntityInstance(player), new StringInstance(message.content().getString())));

                    if (!eventResultClass.instanceOf(new BooleanType())) {
                        throw ErrorHolder.functionRequiresReturnType("onSendChatMessage", new BooleanType(), eventResultClass.getType());
                    }

                    Boolean eventResult = eventResultClass.toBoolean();

                    if (!eventResult) {
                        return ActionResult.FAIL;
                    }
                }

                return ActionResult.PASS;
            } catch (Error error) {
                Logger.error(error.message);
                return ActionResult.FAIL;
            }
        });
    }
}