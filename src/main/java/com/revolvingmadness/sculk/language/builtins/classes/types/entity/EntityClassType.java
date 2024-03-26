package com.revolvingmadness.sculk.language.builtins.classes.types.entity;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockHitResultInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockPosInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.*;
import com.revolvingmadness.sculk.language.builtins.classes.instances.entity.EntityInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.BlockClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.BooleanClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.FloatClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class EntityClassType extends BuiltinClassType {
    public static final EntityClassType TYPE = new EntityClassType();

    private EntityClassType() {
        super("Entity");

        try {
            this.addMethod("addTag", List.of(StringClassType.TYPE));
            this.addNoArgMethod("getTags", builtinClass -> {
                List<BuiltinClass> tags = new ArrayList<>();

                builtinClass.toEntity().getCommandTags().forEach(string -> tags.add(new StringInstance(string)));

                return new ListInstance(tags);
            });
            this.addMethod("removeTag", List.of(StringClassType.TYPE));
            this.addNoArgMethod("dismount", builtinClass -> {
                builtinClass.toEntity().dismountVehicle();

                return new NullInstance();
            });
            this.addNoArgMethod("getBlockPos", builtinClass -> new BlockPosInstance(builtinClass.toEntity().getBlockPos()));
            this.addNoArgMethod("getBlockX", builtinClass -> new IntegerInstance(builtinClass.toEntity().getBlockX()));
            this.addNoArgMethod("getBlockY", builtinClass -> new IntegerInstance(builtinClass.toEntity().getBlockY()));
            this.addNoArgMethod("getBlockZ", builtinClass -> new IntegerInstance(builtinClass.toEntity().getBlockZ()));
            this.addNoArgMethod("getName", builtinClass -> new StringInstance(builtinClass.toEntity().getName().getString()));
            this.addNoArgMethod("getX", builtinClass -> new FloatInstance(builtinClass.toEntity().getX()));
            this.addNoArgMethod("getY", builtinClass -> new FloatInstance(builtinClass.toEntity().getY()));
            this.addNoArgMethod("getZ", builtinClass -> new FloatInstance(builtinClass.toEntity().getZ()));
            this.addNoArgMethod("hasVehicle", builtinClass -> new BooleanInstance(builtinClass.toEntity().hasVehicle()));
            this.addNoArgMethod("isCrawling", builtinClass -> new BooleanInstance(builtinClass.toEntity().isCrawling()));
            this.addNoArgMethod("isFrozen", builtinClass -> new BooleanInstance(builtinClass.toEntity().isFrozen()));
            this.addNoArgMethod("isGlowing", builtinClass -> new BooleanInstance(builtinClass.toEntity().isGlowing()));
            this.addNoArgMethod("isFireImmune", builtinClass -> new BooleanInstance(builtinClass.toEntity().isFireImmune()));
            this.addNoArgMethod("isDescending", builtinClass -> new BooleanInstance(builtinClass.toEntity().isDescending()));
            this.addNoArgMethod("isInFluid", builtinClass -> new BooleanInstance(builtinClass.toEntity().isInFluid()));
            this.addNoArgMethod("isInLava", builtinClass -> new BooleanInstance(builtinClass.toEntity().isInLava()));
            this.addNoArgMethod("isInsideWall", builtinClass -> new BooleanInstance(builtinClass.toEntity().isInsideWall()));
            this.addNoArgMethod("isInvisible", builtinClass -> new BooleanInstance(builtinClass.toEntity().isInvisible()));
            this.addNoArgMethod("isInvulnerable", builtinClass -> new BooleanInstance(builtinClass.toEntity().isInvulnerable()));
            this.addNoArgMethod("isOnFire", builtinClass -> new BooleanInstance(builtinClass.toEntity().isOnFire()));
            this.addNoArgMethod("isOnGround", builtinClass -> new BooleanInstance(builtinClass.toEntity().isOnGround()));
            this.addNoArgMethod("isOnRail", builtinClass -> new BooleanInstance(builtinClass.toEntity().isOnRail()));
            this.addNoArgMethod("isSilent", builtinClass -> new BooleanInstance(builtinClass.toEntity().isSilent()));
            this.addNoArgMethod("isSneaking", builtinClass -> new BooleanInstance(builtinClass.toEntity().isSneaking()));
            this.addNoArgMethod("isSprinting", builtinClass -> new BooleanInstance(builtinClass.toEntity().isSprinting()));
            this.addNoArgMethod("isSwimming", builtinClass -> new BooleanInstance(builtinClass.toEntity().isSwimming()));
            this.addNoArgMethod("isTouchingWater", builtinClass -> new BooleanInstance(builtinClass.toEntity().isTouchingWater()));
            this.addNoArgMethod("isTouchingWaterOrRain", builtinClass -> new BooleanInstance(builtinClass.toEntity().isTouchingWaterOrRain()));
            this.addNoArgMethod("isWet", builtinClass -> new BooleanInstance(builtinClass.toEntity().isWet()));
            this.addNoArgMethod("kill", builtinClass -> {
                builtinClass.toEntity().kill();

                return new NullInstance();
            });
            this.addNoArgMethod("removePassengers", builtinClass -> {
                builtinClass.toEntity().removeAllPassengers();

                return new NullInstance();
            });
            this.addNoArgMethod("resetPortalCooldown", builtinClass -> {
                builtinClass.toEntity().resetPortalCooldown();

                return new NullInstance();
            });
            this.addMethod("sendMessage", List.of(StringClassType.TYPE));
            this.addMethod("setInvisible", List.of(BooleanClassType.TYPE));
            this.addMethod("setInvulnerable", List.of(BooleanClassType.TYPE));
            this.addMethod("setNoGravity", List.of(BooleanClassType.TYPE));
            this.addMethod("setOnFire", List.of(BooleanClassType.TYPE));
            this.addMethod("setOnGround", List.of(BooleanClassType.TYPE));
            this.addMethod("setPortalCooldown", List.of(IntegerClassType.TYPE));
            this.addMethod("setPos", List.of(FloatClassType.TYPE, FloatClassType.TYPE, FloatClassType.TYPE));
            this.addMethod("setSilent", List.of(BooleanClassType.TYPE));
            this.addMethod("setSneaking", List.of(BooleanClassType.TYPE));
            this.addMethod("setSprinting", List.of(BooleanClassType.TYPE));
            this.addMethod("setSwimming", List.of(BooleanClassType.TYPE));
            this.addNoArgMethod("shouldDismountUnderwater", builtinClass -> new BooleanInstance(builtinClass.toEntity().shouldDismountUnderwater()));
            this.addNoArgMethod("stopRiding", builtinClass -> {
                builtinClass.toEntity().stopRiding();

                return new NullInstance();
            });
            this.addMethod("teleport", List.of(FloatClassType.TYPE, FloatClassType.TYPE, FloatClassType.TYPE));
            this.addNoArgMethod("canFreeze", builtinClass -> new BooleanInstance(builtinClass.toEntity().canFreeze()));
            this.addNoArgMethod("canUsePortals", builtinClass -> new BooleanInstance(builtinClass.toEntity().canUsePortals()));
            this.addMethod("extinguish", List.of(BooleanClassType.TYPE));
            this.addNoArgMethod("getPassengers", builtinClass -> {
                List<BuiltinClass> passengers = new ArrayList<>();

                builtinClass.toEntity().getPassengerList().forEach(entity -> passengers.add(new EntityInstance(entity)));

                return new ListInstance(passengers);
            });
            this.addNoArgMethod("getVehicle", builtinClass -> new EntityInstance(builtinClass.toEntity().getVehicle()));
            this.addNoArgMethod("hasControllingPassenger", builtinClass -> new BooleanInstance(builtinClass.toEntity().hasControllingPassenger()));
            this.addNoArgMethod("hasNoGravity", builtinClass -> new BooleanInstance(builtinClass.toEntity().hasNoGravity()));
            this.addNoArgMethod("hasPassengers", builtinClass -> new BooleanInstance(builtinClass.toEntity().hasPassengers()));
            this.addMethod("raycast", List.of(FloatClassType.TYPE, BlockClassType.TYPE, BooleanClassType.TYPE));
            this.addNoArgMethod("getPitch", builtinClass -> new FloatInstance(builtinClass.toEntity().getPitch()));
            this.addNoArgMethod("getYaw", builtinClass -> new FloatInstance(builtinClass.toEntity().getYaw()));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public BuiltinClass addTag(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        String commandTag = arguments[0].toString();

        boundClass.toEntity().addCommandTag(commandTag);

        return new NullInstance();

    }

    public BuiltinClass extinguish(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        boolean other = arguments[0].toBoolean();

        if (other) {
            boundClass.toEntity().extinguishWithSound();
        } else {
            boundClass.toEntity().extinguish();
        }

        return new NullInstance();

    }

    public BuiltinClass hasPassenger(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        Entity passenger = arguments[0].toEntity();

        return new BooleanInstance(boundClass.toEntity().hasPassenger(passenger));

    }

    public BuiltinClass raycast(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        double distance = arguments[0].toFloat();
        Block blockClass = arguments[1].toBlock();
        boolean includeFluids = arguments[2].toBoolean();

        HitResult result = boundClass.toEntity().raycast(distance, 1f, includeFluids);

        if (result.getType() != HitResult.Type.BLOCK) {
            return new BooleanInstance(false);
        }

        BlockHitResult blockHitResult = (BlockHitResult) result;
        BlockPos blockPos = blockHitResult.getBlockPos();
        BlockState blockState = boundClass.toEntity().getWorld().getBlockState(blockPos);
        Block block = blockState.getBlock();

        return new BlockHitResultInstance(blockPos, block, block.equals(blockClass));

    }

    public BuiltinClass removeTag(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        String commandTag = arguments[0].toString();

        boundClass.toEntity().removeCommandTag(commandTag);

        return new NullInstance();

    }

    public BuiltinClass sendMessage(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        String message = arguments[0].toString();

        boundClass.toEntity().sendMessage(Text.literal(message));

        return new NullInstance();

    }

    public BuiltinClass setInvisible(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        boolean invisible = arguments[0].toBoolean();

        boundClass.toEntity().setInvisible(invisible);

        return new NullInstance();

    }

    public BuiltinClass setInvulnerable(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        boolean invulnerable = arguments[0].toBoolean();

        boundClass.toEntity().setInvulnerable(invulnerable);

        return new NullInstance();

    }

    public BuiltinClass setNoGravity(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        boolean noGravity = arguments[0].toBoolean();

        boundClass.toEntity().setNoGravity(noGravity);

        return new NullInstance();

    }

    public BuiltinClass setOnFire(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        boolean onFire = arguments[0].toBoolean();

        boundClass.toEntity().setOnFire(onFire);

        return new NullInstance();

    }

    public BuiltinClass setOnGround(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        boolean onGround = arguments[0].toBoolean();

        boundClass.toEntity().setOnGround(onGround);

        return new NullInstance();

    }

    public BuiltinClass setPortalCooldown(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        long portalCooldown = arguments[0].toInteger();

        boundClass.toEntity().setPortalCooldown((int) portalCooldown);

        return new NullInstance();

    }

    public BuiltinClass setPos(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        double x = arguments[0].toFloat();
        double y = arguments[1].toFloat();
        double z = arguments[2].toFloat();

        boundClass.toEntity().setPos(x, y, z);

        return new NullInstance();

    }

    public BuiltinClass setSilent(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        boolean silent = arguments[0].toBoolean();

        boundClass.toEntity().setSilent(silent);

        return new NullInstance();

    }

    public BuiltinClass setSneaking(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        boolean sneaking = arguments[0].toBoolean();

        boundClass.toEntity().setSneaking(sneaking);

        return new NullInstance();

    }

    public BuiltinClass setSprinting(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        boolean sprinting = arguments[0].toBoolean();

        boundClass.toEntity().setSprinting(sprinting);

        return new NullInstance();

    }

    public BuiltinClass setSwimming(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        boolean swimming = arguments[0].toBoolean();

        boundClass.toEntity().setSwimming(swimming);

        return new NullInstance();

    }

    public BuiltinClass teleport(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        double destX = arguments[0].toFloat();
        double destY = arguments[1].toFloat();
        double destZ = arguments[2].toFloat();

        boundClass.toEntity().teleport(destX, destY, destZ);

        return new NullInstance();
    }

    static {
        try {
            TYPE.addMethod("hasPassenger", List.of(EntityClassType.TYPE));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
