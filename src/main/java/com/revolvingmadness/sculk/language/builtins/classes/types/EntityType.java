package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.*;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class EntityType extends BuiltinType {
    public static final EntityType TYPE = new EntityType();

    private EntityType() {
        super("Entity");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "addTag", new AddTag());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getTags", new GetTags());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "removeTag", new RemoveTag());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "dismount", new Dismount());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getBlockPos", new GetBlockPos());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getBlockX", new GetBlockX());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getBlockY", new GetBlockY());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getBlockZ", new GetBlockZ());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getName", new GetName());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getX", new GetX());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getY", new GetY());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getZ", new GetZ());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "hasVehicle", new HasVehicle());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isCrawling", new IsCrawling());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isFrozen", new IsFrozen());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isGlowing", new IsGlowing());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isFireImmune", new IsFireImmune());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isDescending", new IsDescending());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isInFluid", new IsInFluid());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isInLava", new IsInLava());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isInsideWall", new IsInsideWall());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isInvisible", new IsInvisible());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isInvulnerable", new IsInvulnerable());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isOnFire", new IsOnFire());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isOnGround", new IsOnGround());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isOnRail", new IsOnRail());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isSilent", new IsSilent());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isSneaking", new IsSneaking());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isSprinting", new IsSprinting());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isSwimming", new IsSwimming());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isTouchingWater", new IsTouchingWater());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isTouchingWaterOrRain", new IsTouchingWaterOrRain());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isWet", new IsWet());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "kill", new Kill());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "removePassengers", new RemovePassengers());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "resetPortalCooldown", new ResetPortalCooldown());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "sendMessage", new SendMessage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setInvisible", new SetInvisible());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setInvulnerable", new SetInvulnerable());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setNoGravity", new SetNoGravity());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setOnFire", new SetOnFire());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setOnGround", new SetOnGround());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setPortalCooldown", new SetPortalCooldown());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setPos", new SetPos());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setSilent", new SetSilent());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setSneaking", new SetSneaking());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setSprinting", new SetSprinting());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setSwimming", new SetSwimming());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "shouldDismountUnderwater", new ShouldDismountUnderwater());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "stopRiding", new StopRiding());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "teleport", new Teleport());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "canFreeze", new CanFreeze());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "canUsePortals", new CanUsePortals());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "extinguish", new Extinguish());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getPassengers", new GetPassengers());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getVehicle", new GetVehicle());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "hasControllingPassenger", new HasControllingPassenger());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "hasNoGravity", new HasNoGravity());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "hasPassenger", new HasPassenger());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "hasPassengers", new HasPassengers());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "raycast", new Raycast());
    }

    private static class AddTag extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("addTag", arguments, List.of(StringType.TYPE));

            String commandTag = arguments.get(0).toString();

            this.boundClass.toEntity().addCommandTag(commandTag);

            return new NullInstance();
        }
    }

    private static class CanFreeze extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("canFreeze", arguments);

            return new BooleanInstance(this.boundClass.toEntity().canFreeze());
        }
    }

    private static class CanUsePortals extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("canUsePortals", arguments);

            return new BooleanInstance(this.boundClass.toEntity().canUsePortals());
        }
    }

    private static class Dismount extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("dismount", arguments);

            this.boundClass.toEntity().dismountVehicle();

            return new NullInstance();
        }
    }

    private static class Extinguish extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("extinguish", arguments, List.of(BooleanType.TYPE));

            boolean other = arguments.get(0).toBoolean();

            if (other) {
                this.boundClass.toEntity().extinguishWithSound();
            } else {
                this.boundClass.toEntity().extinguish();
            }

            return new NullInstance();
        }
    }

    private static class GetBlockPos extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getBlockPos", arguments);

            BlockPos blockPos = this.boundClass.toEntity().getBlockPos();

            return new BlockPosInstance(blockPos);
        }
    }

    private static class GetBlockX extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getBlockX", arguments);

            long blockX = this.boundClass.toEntity().getBlockX();

            return new IntegerInstance(blockX);
        }
    }

    private static class GetBlockY extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getBlockY", arguments);

            long blockY = this.boundClass.toEntity().getBlockY();

            return new IntegerInstance(blockY);
        }
    }

    private static class GetBlockZ extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getBlockZ", arguments);

            long blockZ = this.boundClass.toEntity().getBlockZ();

            return new IntegerInstance(blockZ);
        }
    }

    private static class GetName extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getName", arguments);

            String name = this.boundClass.toEntity().getName().getString();

            return new StringInstance(name);
        }
    }

    private static class GetPassengers extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getPassengers", arguments);

            List<BuiltinClass> passengers = new ArrayList<>();

            this.boundClass.toEntity().getPassengerList().forEach(passenger -> passengers.add(new EntityInstance(passenger)));

            return new ListInstance(passengers);
        }
    }

    private static class GetTags extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getTags", arguments);

            List<BuiltinClass> commandTags = new ArrayList<>();

            this.boundClass.toEntity().getCommandTags().forEach(commandTag -> commandTags.add(new StringInstance(commandTag)));

            return new ListInstance(commandTags);
        }
    }

    private static class GetVehicle extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getVehicle", arguments);

            return new EntityInstance(this.boundClass.toEntity().getVehicle());
        }
    }

    private static class GetX extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getX", arguments);

            double x = this.boundClass.toEntity().getX();

            return new FloatInstance(x);
        }
    }

    private static class GetY extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getY", arguments);

            double y = this.boundClass.toEntity().getY();

            return new FloatInstance(y);
        }
    }

    private static class GetZ extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getZ", arguments);

            double z = this.boundClass.toEntity().getZ();

            return new FloatInstance(z);
        }
    }

    private static class HasControllingPassenger extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("hasControllingPassenger", arguments);

            return new BooleanInstance(this.boundClass.toEntity().hasControllingPassenger());
        }
    }

    private static class HasNoGravity extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("hasNoGravity", arguments);

            return new BooleanInstance(this.boundClass.toEntity().hasNoGravity());
        }
    }

    private static class HasPassenger extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("hasPassenger", arguments, List.of(BooleanType.TYPE));

            Entity passenger = arguments.get(0).toEntity();

            return new BooleanInstance(this.boundClass.toEntity().hasPassenger(passenger));
        }
    }

    private static class HasPassengers extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("hasPassengers", arguments);

            return new BooleanInstance(this.boundClass.toEntity().hasPassengers());
        }
    }

    private static class HasVehicle extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("hasVehicle", arguments);

            return new BooleanInstance(this.boundClass.toEntity().hasVehicle());
        }
    }

    private static class IsCrawling extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isCrawling", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isCrawling());
        }
    }

    private static class IsDescending extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isDecending", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isDescending());
        }
    }

    private static class IsFireImmune extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isFireImmune", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isFireImmune());
        }
    }

    private static class IsFrozen extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isFrozen", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isFrozen());
        }
    }

    private static class IsGlowing extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isGlowing", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isGlowing());
        }
    }

    private static class IsInFluid extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isInFluid", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isInFluid());
        }
    }

    private static class IsInLava extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isInLava", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isInLava());
        }
    }

    private static class IsInsideWall extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isInsideWall", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isInsideWall());
        }
    }

    private static class IsInvisible extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isInvisible", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isInvisible());
        }
    }

    private static class IsInvulnerable extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isInvulnerable", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isInvulnerable());
        }
    }

    private static class IsOnFire extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isOnFire", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isOnFire());
        }
    }

    private static class IsOnGround extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isOnGround", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isOnGround());
        }
    }

    private static class IsOnRail extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isOnRail", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isOnRail());
        }
    }

    private static class IsSilent extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isSilent", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isSilent());
        }
    }

    private static class IsSneaking extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isSneaking", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isSneaking());
        }
    }

    private static class IsSprinting extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isSprinting", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isSprinting());
        }
    }

    private static class IsSwimming extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isSwimming", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isSwimming());
        }
    }

    private static class IsTouchingWater extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isTouchingWater", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isTouchingWater());
        }
    }

    private static class IsTouchingWaterOrRain extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isTouchingWaterOrRain", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isTouchingWaterOrRain());
        }
    }

    private static class IsWet extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isWet", arguments);

            return new BooleanInstance(this.boundClass.toEntity().isWet());
        }
    }

    private static class Kill extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("kill", arguments);

            this.boundClass.toEntity().kill();

            return new NullInstance();
        }
    }

    private static class Raycast extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("raycast", List.of(FloatType.TYPE, BlockType.TYPE, BooleanType.TYPE));

            double distance = arguments.get(0).toFloat();
            Block blockClass = arguments.get(1).toBlock();
            boolean includeFluids = arguments.get(2).toBoolean();

            HitResult result = this.boundClass.toEntity().raycast(distance, 1f, includeFluids);

            if (result.getType() != HitResult.Type.BLOCK) {
                return new BooleanInstance(false);
            }

            BlockHitResult blockHitResult = (BlockHitResult) result;
            BlockPos blockPos = blockHitResult.getBlockPos();
            BlockState blockState = this.boundClass.toEntity().getWorld().getBlockState(blockPos);
            Block block = blockState.getBlock();

            return new BlockHitResultInstance(blockPos, block, block.equals(blockClass));
        }
    }

    private static class RemovePassengers extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("removePassengers", arguments);

            this.boundClass.toEntity().removeAllPassengers();

            return new NullInstance();
        }
    }

    private static class RemoveTag extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("removeTag", List.of(StringType.TYPE));

            String commandTag = arguments.get(0).toString();

            this.boundClass.toEntity().removeCommandTag(commandTag);

            return new NullInstance();
        }
    }

    private static class ResetPortalCooldown extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("resetPortalCooldown", arguments);

            this.boundClass.toEntity().resetPortalCooldown();

            return new NullInstance();
        }
    }

    private static class SendMessage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("sendMessage", arguments, List.of(StringType.TYPE));

            String message = arguments.get(0).toString();

            this.boundClass.toEntity().sendMessage(Text.literal(message));

            return new NullInstance();
        }
    }

    private static class SetInvisible extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setInvisible", arguments, List.of(BooleanType.TYPE));

            boolean invisible = arguments.get(0).toBoolean();

            this.boundClass.toEntity().setInvisible(invisible);

            return new NullInstance();
        }
    }

    private static class SetInvulnerable extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setInvulnerable", arguments, List.of(BooleanType.TYPE));

            boolean invulnerable = arguments.get(0).toBoolean();

            this.boundClass.toEntity().setInvulnerable(invulnerable);

            return new NullInstance();
        }
    }

    private static class SetNoGravity extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setNoGravity", arguments, List.of(BooleanType.TYPE));

            boolean noGravity = arguments.get(0).toBoolean();

            this.boundClass.toEntity().setNoGravity(noGravity);

            return new NullInstance();
        }
    }

    private static class SetOnFire extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setOnFire", arguments, List.of(BooleanType.TYPE));

            boolean onFire = arguments.get(0).toBoolean();

            this.boundClass.toEntity().setOnFire(onFire);

            return new NullInstance();
        }
    }

    private static class SetOnGround extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setOnGround", arguments, List.of(BooleanType.TYPE));

            boolean onGround = arguments.get(0).toBoolean();

            this.boundClass.toEntity().setOnGround(onGround);

            return new NullInstance();
        }
    }

    private static class SetPortalCooldown extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setPortalCooldown", arguments, List.of(IntegerType.TYPE));

            long portalCooldown = arguments.get(0).toInteger();

            this.boundClass.toEntity().setPortalCooldown((int) portalCooldown);

            return new NullInstance();
        }
    }

    private static class SetPos extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setPos", arguments, List.of(FloatType.TYPE, FloatType.TYPE, FloatType.TYPE));

            double x = arguments.get(0).toFloat();
            double y = arguments.get(1).toFloat();
            double z = arguments.get(2).toFloat();

            this.boundClass.toEntity().setPos(x, y, z);

            return new NullInstance();
        }
    }

    private static class SetSilent extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setSilent", arguments, List.of(BooleanType.TYPE));

            boolean silent = arguments.get(0).toBoolean();

            this.boundClass.toEntity().setSilent(silent);

            return new NullInstance();
        }
    }

    private static class SetSneaking extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setSneaking", arguments, List.of(BooleanType.TYPE));

            boolean sneaking = arguments.get(0).toBoolean();

            this.boundClass.toEntity().setSneaking(sneaking);

            return new NullInstance();
        }
    }

    private static class SetSprinting extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setSprinting", arguments, List.of(BooleanType.TYPE));

            boolean sprinting = arguments.get(0).toBoolean();

            this.boundClass.toEntity().setSprinting(sprinting);

            return new NullInstance();
        }
    }

    private static class SetSwimming extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setSwimming", arguments, List.of(BooleanType.TYPE));

            boolean swimming = arguments.get(0).toBoolean();

            this.boundClass.toEntity().setSwimming(swimming);

            return new NullInstance();
        }
    }

    private static class ShouldDismountUnderwater extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("shouldDismountUnderwater", arguments);

            return new BooleanInstance(this.boundClass.toEntity().shouldDismountUnderwater());
        }
    }

    private static class StopRiding extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("stopRiding", arguments);

            this.boundClass.toEntity().stopRiding();

            return new NullInstance();
        }
    }

    private static class Teleport extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("teleport", arguments, List.of(FloatType.TYPE, FloatType.TYPE, FloatType.TYPE));

            double destX = arguments.get(0).toFloat();
            double destY = arguments.get(1).toFloat();
            double destZ = arguments.get(2).toFloat();

            this.boundClass.toEntity().teleport(destX, destY, destZ);

            return new NullInstance();
        }
    }
}
