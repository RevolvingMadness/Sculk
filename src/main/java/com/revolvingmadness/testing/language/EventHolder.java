package com.revolvingmadness.testing.language;

import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.events.PlayerJumpCallback;
import com.revolvingmadness.testing.events.PlayerSneakCallback;
import com.revolvingmadness.testing.events.RingBellCallback;
import com.revolvingmadness.testing.events.SendChatMessageCallback;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.instances.*;
import com.revolvingmadness.testing.language.builtins.classes.types.BooleanType;
import com.revolvingmadness.testing.language.errors.Error;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.util.ActionResult;

import java.util.ArrayList;
import java.util.List;

public class EventHolder {
    public static List<Event> onPlayerAttackEntity = new ArrayList<>();
    public static List<Event> onPlayerJump = new ArrayList<>();
    public static List<Event> onPlayerSleep = new ArrayList<>();
    public static List<Event> onRingBell = new ArrayList<>();
    public static List<Event> onSendChatMessage = new ArrayList<>();
    public static List<Event> whilePlayerSneak = new ArrayList<>();

    public static void clearEvents() {
        EventHolder.onPlayerAttackEntity.clear();
        EventHolder.onPlayerJump.clear();
        EventHolder.onPlayerSleep.clear();
        EventHolder.onRingBell.clear();
        EventHolder.onSendChatMessage.clear();
        EventHolder.whilePlayerSneak.clear();
    }

    public static void registerEvents() {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!world.isClient) {
                try {
                    for (Event event : EventHolder.onPlayerAttackEntity) {
                        BuiltinClass eventResultClass = event.execute(List.of(new PlayerEntityInstance(player), new EntityInstance(entity)));

                        if (!eventResultClass.instanceOf(new BooleanType())) {
                            throw ErrorHolder.functionRequiresReturnType("onPlayerAttackEntity", new BooleanType(), eventResultClass.getType());
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
            }

            return ActionResult.PASS;
        });

        PlayerJumpCallback.EVENT.register((player) -> {
            try {
                for (Event event : EventHolder.onPlayerJump) {
                    event.execute(List.of(new PlayerEntityInstance(player)));
                }

                return ActionResult.PASS;
            } catch (Error error) {
                Logger.error(error.message);
                return ActionResult.FAIL;
            }
        });

        EntitySleepEvents.START_SLEEPING.register((livingEntity, sleepingPos) -> {
            try {
                for (Event event : EventHolder.onPlayerSleep) {
                    event.execute(List.of(new LivingEntityInstance(livingEntity), new BlockPosInstance(sleepingPos)));
                }
            } catch (Error error) {
                Logger.error(error.message);
            }
        });

        RingBellCallback.EVENT.register((player) -> {
            try {
                for (Event event : EventHolder.onRingBell) {
                    event.execute(List.of(new PlayerEntityInstance(player)));
                }

                return ActionResult.PASS;
            } catch (Error error) {
                Logger.error(error.message);
                return ActionResult.FAIL;
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

        PlayerSneakCallback.EVENT.register((player) -> {
            try {
                for (Event event : EventHolder.whilePlayerSneak) {
                    BuiltinClass eventResultClass = event.execute(List.of(new ServerPlayerEntityInstance(player)));

                    if (!eventResultClass.instanceOf(new BooleanType())) {
                        throw ErrorHolder.functionRequiresReturnType("whilePlayerSneak", new BooleanType(), eventResultClass.getType());
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