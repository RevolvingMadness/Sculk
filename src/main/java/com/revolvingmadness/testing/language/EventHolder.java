package com.revolvingmadness.testing.language;

import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.events.*;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.instances.*;
import com.revolvingmadness.testing.language.builtins.classes.types.BooleanType;
import com.revolvingmadness.testing.language.errors.Error;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;

import java.util.ArrayList;
import java.util.List;

public class EventHolder {
    public static final List<Event> onPlaceBlock = new ArrayList<>();
    public static final List<Event> onPlayerAttackEntity = new ArrayList<>();
    public static final List<Event> onPlayerBlockBreak = new ArrayList<>();
    public static final List<Event> onPlayerJump = new ArrayList<>();
    public static final List<Event> onPlayerPickupItem = new ArrayList<>();
    public static final List<Event> onPlayerSleep = new ArrayList<>();
    public static final List<Event> onPlayerUseItem = new ArrayList<>();
    public static final List<Event> onRingBell = new ArrayList<>();
    public static final List<Event> onSendChatMessage = new ArrayList<>();
    public static final List<Event> whilePlayerSneak = new ArrayList<>();

    public static void clearEvents() {
        EventHolder.onPlaceBlock.clear();
        EventHolder.onPlayerAttackEntity.clear();
        EventHolder.onPlayerBlockBreak.clear();
        EventHolder.onPlayerJump.clear();
        EventHolder.onPlayerPickupItem.clear();
        EventHolder.onPlayerSleep.clear();
        EventHolder.onPlayerUseItem.clear();
        EventHolder.onRingBell.clear();
        EventHolder.onSendChatMessage.clear();
        EventHolder.whilePlayerSneak.clear();
    }

    public static void registerEvents() {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!world.isClient) {
                try {
                    for (Event event : EventHolder.onPlayerAttackEntity) {
                        BuiltinClass eventResultClass = event.execute(List.of(new PlayerEntityInstance(player), new EntityInstance(entity), new ItemStackInstance(player.getStackInHand(hand))));

                        if (!eventResultClass.instanceOf(new BooleanType())) {
                            throw ErrorHolder.functionRequiresReturnType("onPlayerAttackEntity", new BooleanType(), eventResultClass.getType());
                        }

                        Boolean eventResult = eventResultClass.toBoolean();

                        if (!eventResult) {
                            return ActionResult.FAIL;
                        }
                    }
                } catch (Error error) {
                    Logger.error(error.message);
                }
            }

            return ActionResult.PASS;
        });

        BreakBlockCallback.EVENT.register((player, block) -> {
            if (!player.getWorld().isClient) {
                try {
                    for (Event event : EventHolder.onPlayerBlockBreak) {
                        event.execute(List.of(new PlayerEntityInstance(player), new BlockInstance(block)));
                    }
                } catch (Error error) {
                    Logger.error(error.message);
                }
            }

            return ActionResult.PASS;
        });

        PlayerJumpCallback.EVENT.register((player) -> {
            try {
                for (Event event : EventHolder.onPlayerJump) {
                    event.execute(List.of(new PlayerEntityInstance(player)));
                }
            } catch (Error error) {
                Logger.error(error.message);
            }

            return ActionResult.PASS;
        });

        ItemPickupCallback.EVENT.register((player, itemStack) -> {
            try {
                for (Event event : EventHolder.onPlayerPickupItem) {
                    BuiltinClass eventResultClass = event.execute(List.of(new PlayerEntityInstance(player), new ItemStackInstance(itemStack)));

                    if (!eventResultClass.instanceOf(new BooleanType())) {
                        throw ErrorHolder.functionRequiresReturnType("onPlayerPickupItem", new BooleanType(), eventResultClass.getType());
                    }

                    Boolean eventResult = eventResultClass.toBoolean();

                    if (!eventResult) {
                        return ActionResult.FAIL;
                    }
                }
            } catch (Error error) {
                Logger.error(error.message);
            }

            return ActionResult.PASS;
        });

        PlaceBlockCallback.EVENT.register((livingEntity, block) -> {
            if (!livingEntity.getWorld().isClient) {
                try {
                    for (Event event : EventHolder.onPlaceBlock) {
                        event.execute(List.of(new LivingEntityInstance(livingEntity), new BlockInstance(block)));
                    }
                } catch (Error error) {
                    Logger.error(error.message);
                }
            }

            return ActionResult.PASS;
        });

        EntitySleepEvents.START_SLEEPING.register((livingEntity, sleepingPos) -> {
            try {
                for (Event event : EventHolder.onPlayerSleep) {
                    event.execute(List.of(new LivingEntityInstance(livingEntity)));
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
            } catch (Error error) {
                Logger.error(error.message);
            }

            return ActionResult.PASS;
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
            } catch (Error error) {
                Logger.error(error.message);
            }

            return ActionResult.PASS;
        });

        UseItemCallback.EVENT.register((player, world, hand) -> {
            if (!world.isClient) {
                try {
                    for (Event event : EventHolder.onPlayerUseItem) {
                        BuiltinClass eventResultClass = event.execute(List.of(new PlayerEntityInstance(player), new ItemStackInstance(player.getStackInHand(hand))));

                        if (!eventResultClass.instanceOf(new BooleanType())) {
                            throw ErrorHolder.functionRequiresReturnType("onPlayerUseItem", new BooleanType(), eventResultClass.getType());
                        }

                        Boolean eventResult = eventResultClass.toBoolean();

                        if (!eventResult) {
                            return TypedActionResult.fail(player.getStackInHand(hand));
                        }
                    }
                } catch (Error error) {
                    Logger.error(error.message);
                }
            }

            return TypedActionResult.pass(player.getStackInHand(hand));
        });

        PlayerSneakCallback.EVENT.register((player) -> {
            try {
                for (Event event : EventHolder.whilePlayerSneak) {
                    event.execute(List.of(new ServerPlayerEntityInstance(player)));
                }
            } catch (Error error) {
                Logger.error(error.message);
            }

            return ActionResult.PASS;
        });
    }
}