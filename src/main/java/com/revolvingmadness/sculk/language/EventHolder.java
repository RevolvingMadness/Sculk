package com.revolvingmadness.sculk.language;

import com.revolvingmadness.sculk.backend.Logger;
import com.revolvingmadness.sculk.events.*;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockPosInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.entity.EntityInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.entity.LivingEntityInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.entity.PlayerEntityInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.entity.ServerPlayerEntityInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.item.ItemStackInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.BooleanClassType;
import com.revolvingmadness.sculk.language.errors.Error;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;

import java.util.ArrayList;
import java.util.List;

public class EventHolder {
    public static final List<Event> onAttackEntity = new ArrayList<>();
    public static final List<Event> onBreakBlock = new ArrayList<>();
    public static final List<Event> onCraftItem = new ArrayList<>();
    public static final List<Event> onDropItem = new ArrayList<>();
    public static final List<Event> onEntitySleep = new ArrayList<>();
    public static final List<Event> onJump = new ArrayList<>();
    public static final List<Event> onPickupItem = new ArrayList<>();
    public static final List<Event> onPlaceBlock = new ArrayList<>();
    public static final List<Event> onRightClickItem = new ArrayList<>();
    public static final List<Event> onRingBell = new ArrayList<>();
    public static final List<Event> onSendChatMessage = new ArrayList<>();
    public static final List<Event> whileSneaking = new ArrayList<>();

    public static void clearEvents() {
        EventHolder.onEntitySleep.clear();
        EventHolder.onPlaceBlock.clear();
        EventHolder.onAttackEntity.clear();
        EventHolder.onBreakBlock.clear();
        EventHolder.onCraftItem.clear();
        EventHolder.onDropItem.clear();
        EventHolder.onJump.clear();
        EventHolder.onPickupItem.clear();
        EventHolder.onRingBell.clear();
        EventHolder.onSendChatMessage.clear();
        EventHolder.whileSneaking.clear();
        EventHolder.onRightClickItem.clear();
    }

    public static void registerEvents() {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!world.isClient) {
                try {
                    for (Event event : EventHolder.onAttackEntity) {
                        BuiltinClass eventResultClass = event.execute(List.of(new PlayerEntityInstance(player), new EntityInstance(entity), new ItemStackInstance(player.getStackInHand(hand))));

                        if (!eventResultClass.instanceOf(BooleanClassType.TYPE)) {
                            throw ErrorHolder.functionRequiresReturnType("onPlayerAttackEntity", eventResultClass.type, BooleanClassType.TYPE);
                        }

                        boolean eventResult = eventResultClass.toBoolean();

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

        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, blockState, blockEntity) -> {
            if (!player.getWorld().isClient) {
                try {
                    for (Event event : EventHolder.onBreakBlock) {
                        BuiltinClass eventResultClass = event.execute(List.of(new PlayerEntityInstance(player), new BlockPosInstance(pos), new BlockInstance(world.getBlockState(pos).getBlock())));

                        if (!eventResultClass.instanceOf(BooleanClassType.TYPE)) {
                            throw ErrorHolder.functionRequiresReturnType("onPlayerBreakBlock", eventResultClass.type, BooleanClassType.TYPE);
                        }

                        boolean eventResult = eventResultClass.toBoolean();

                        if (!eventResult) {
                            return false;
                        }
                    }
                } catch (Error error) {
                    Logger.error(error.message);
                }
            }

            return true;
        });

        CraftItemCallback.EVENT.register((player, itemStack) -> {
            if (!player.getWorld().isClient) {
                try {
                    for (Event event : EventHolder.onCraftItem) {
                        event.execute(List.of(new PlayerEntityInstance(player), new ItemStackInstance(itemStack)));
                    }
                } catch (Error error) {
                    Logger.error(error.message);
                }
            }

            return ActionResult.PASS;
        });

        DropItemCallback.EVENT.register((player, itemStack) -> {
            if (!player.getWorld().isClient) {
                try {
                    for (Event event : EventHolder.onDropItem) {
                        event.execute(List.of(new PlayerEntityInstance(player), new ItemStackInstance(itemStack)));
                    }
                } catch (Error error) {
                    Logger.error(error.message);
                }
            }

            return ActionResult.PASS;
        });

        PlayerJumpCallback.EVENT.register((player) -> {
            try {
                for (Event event : EventHolder.onJump) {
                    event.execute(List.of(new PlayerEntityInstance(player)));
                }
            } catch (Error error) {
                Logger.error(error.message);
            }

            return ActionResult.PASS;
        });

        PickupItemCallback.EVENT.register((player, itemStack) -> {
            try {
                for (Event event : EventHolder.onPickupItem) {
                    BuiltinClass eventResultClass = event.execute(List.of(new PlayerEntityInstance(player), new ItemStackInstance(itemStack)));

                    if (!eventResultClass.instanceOf(BooleanClassType.TYPE)) {
                        throw ErrorHolder.functionRequiresReturnType("onPlayerPickupItem", eventResultClass.type, BooleanClassType.TYPE);
                    }

                    boolean eventResult = eventResultClass.toBoolean();

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
                for (Event event : EventHolder.onEntitySleep) {
                    event.execute(List.of(new LivingEntityInstance(livingEntity)));
                }
            } catch (Error error) {
                Logger.error(error.message);
            }
        });

        PlayerRingBellCallback.EVENT.register((player) -> {
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

                    if (!eventResultClass.instanceOf(BooleanClassType.TYPE)) {
                        throw ErrorHolder.functionRequiresReturnType("onPlayerSendChatMessage", eventResultClass.type, BooleanClassType.TYPE);
                    }

                    boolean eventResult = eventResultClass.toBoolean();

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
                    for (Event event : EventHolder.onRightClickItem) {
                        BuiltinClass eventResultClass = event.execute(List.of(new PlayerEntityInstance(player), new ItemStackInstance(player.getStackInHand(hand))));

                        if (!eventResultClass.instanceOf(BooleanClassType.TYPE)) {
                            throw ErrorHolder.functionRequiresReturnType("onPlayerUseItem", eventResultClass.type, BooleanClassType.TYPE);
                        }

                        boolean eventResult = eventResultClass.toBoolean();

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
                for (Event event : EventHolder.whileSneaking) {
                    event.execute(List.of(new ServerPlayerEntityInstance(player)));
                }
            } catch (Error error) {
                Logger.error(error.message);
            }

            return ActionResult.PASS;
        });
    }
}