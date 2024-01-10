package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.*;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class EntityType extends BuiltinType {
    public EntityType() {
        super("Entity");

        this.typeVariableScope.declare(true, "addCommandTag", new AddCommandTag());
        this.typeVariableScope.declare(true, "dismountVehicle", new DismountVehicle());
        this.typeVariableScope.declare(true, "getBlockPos", new GetBlockPos());
        this.typeVariableScope.declare(true, "getBlockX", new GetBlockX());
        this.typeVariableScope.declare(true, "getBlockY", new GetBlockY());
        this.typeVariableScope.declare(true, "getBlockZ", new GetBlockZ());
        this.typeVariableScope.declare(true, "getName", new GetName());
        this.typeVariableScope.declare(true, "getX", new GetX());
        this.typeVariableScope.declare(true, "getY", new GetY());
        this.typeVariableScope.declare(true, "getZ", new GetZ());
        this.typeVariableScope.declare(true, "hasVehicle", new HasVehicle());
        this.typeVariableScope.declare(true, "isCrawling", new IsCrawling());
        this.typeVariableScope.declare(true, "isFrozen", new IsFrozen());
        this.typeVariableScope.declare(true, "isGlowing", new IsGlowing());
        this.typeVariableScope.declare(true, "isFireImmune", new IsFireImmune());
        this.typeVariableScope.declare(true, "isDescending", new IsDescending());
        this.typeVariableScope.declare(true, "isInFluid", new IsInFluid());
        this.typeVariableScope.declare(true, "isInLava", new IsInLava());
        this.typeVariableScope.declare(true, "isInsideWall", new IsInsideWall());
        this.typeVariableScope.declare(true, "isInvisible", new IsInvisible());
        this.typeVariableScope.declare(true, "isInvulnerable", new IsInvulnerable());
        this.typeVariableScope.declare(true, "isOnFire", new IsOnFire());
        this.typeVariableScope.declare(true, "isOnGround", new IsOnGround());
        this.typeVariableScope.declare(true, "isOnRail", new IsOnRail());
        this.typeVariableScope.declare(true, "isSilent", new IsSilent());
        this.typeVariableScope.declare(true, "isSneaking", new IsSneaking());
        this.typeVariableScope.declare(true, "isSprinting", new IsSprinting());
        this.typeVariableScope.declare(true, "isSwimming", new IsSwimming());
        this.typeVariableScope.declare(true, "isTouchingWater", new IsTouchingWater());
        this.typeVariableScope.declare(true, "isTouchingWaterOrRain", new IsTouchingWaterOrRain());
        this.typeVariableScope.declare(true, "isWet", new IsWet());
        this.typeVariableScope.declare(true, "kill", new Kill());
        this.typeVariableScope.declare(true, "removeAllPassengers", new RemoveAllPassengers());
        this.typeVariableScope.declare(true, "resetPortalCooldown", new ResetPortalCooldown());
        this.typeVariableScope.declare(true, "sendMessage", new SendMessage());
        this.typeVariableScope.declare(true, "setInvisible", new SetInvisible());
        this.typeVariableScope.declare(true, "setInvulnerable", new SetInvulnerable());
        this.typeVariableScope.declare(true, "setNoGravity", new SetNoGravity());
        this.typeVariableScope.declare(true, "setOnFire", new SetOnFire());
        this.typeVariableScope.declare(true, "setOnGround", new SetOnGround());
        this.typeVariableScope.declare(true, "setPortalCooldown", new SetPortalCooldown());
        this.typeVariableScope.declare(true, "setPos", new SetPos());
        this.typeVariableScope.declare(true, "setSilent", new SetSilent());
        this.typeVariableScope.declare(true, "setSneaking", new SetSneaking());
        this.typeVariableScope.declare(true, "setSprinting", new SetSprinting());
        this.typeVariableScope.declare(true, "setSwimming", new SetSwimming());
        this.typeVariableScope.declare(true, "shouldDismountUnderwater", new ShouldDismountUnderwater());
        this.typeVariableScope.declare(true, "stopRiding", new StopRiding());
        this.typeVariableScope.declare(true, "teleport", new Teleport());
    }

    private static class AddCommandTag extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("addCommandTag", 1, arguments.size());
            }

            BuiltinClass commandTag = arguments.get(0);

            if (!commandTag.instanceOf(new StringType())) {
                throw ErrorHolder.argumentRequiresType(1, "addCommandTag", new StringType(), commandTag.getType());
            }

            this.boundClass.toEntity().addCommandTag(commandTag.toStringType());

            return new NullInstance();
        }
    }

    private static class DismountVehicle extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("dismountVehicle", 0, arguments.size());
            }

            this.boundClass.toEntity().dismountVehicle();

            return new NullInstance();
        }
    }

    private static class GetBlockPos extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getBlockPos", 0, arguments.size());
            }

            BlockPos blockPos = this.boundClass.toEntity().getBlockPos();

            return new Vec3dInstance(blockPos.toCenterPos());
        }
    }

    private static class GetBlockX extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getBlockX", 0, arguments.size());
            }

            int blockX = this.boundClass.toEntity().getBlockX();

            return new IntegerInstance(blockX);
        }
    }

    private static class GetBlockY extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getBlockY", 0, arguments.size());
            }

            int blockY = this.boundClass.toEntity().getBlockY();

            return new IntegerInstance(blockY);
        }
    }

    private static class GetBlockZ extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getBlockZ", 0, arguments.size());
            }

            int blockZ = this.boundClass.toEntity().getBlockZ();

            return new IntegerInstance(blockZ);
        }
    }

    private static class GetName extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getName", 0, arguments.size());
            }

            String name = this.boundClass.toEntity().getName().getString();

            return new StringInstance(name);
        }
    }

    private static class GetX extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getX", 0, arguments.size());
            }

            double x = this.boundClass.toEntity().getX();

            return new FloatInstance(x);
        }
    }

    private static class GetY extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getY", 0, arguments.size());
            }

            double y = this.boundClass.toEntity().getY();

            return new FloatInstance(y);
        }
    }

    private static class GetZ extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getZ", 0, arguments.size());
            }

            double z = this.boundClass.toEntity().getZ();

            return new FloatInstance(z);
        }
    }

    private static class HasVehicle extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("hasVehicle", 0, arguments.size());
            }

            boolean hasVehicle = this.boundClass.toEntity().hasVehicle();

            return new BooleanInstance(hasVehicle);
        }
    }

    private static class IsCrawling extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isCrawling", 0, arguments.size());
            }

            boolean isCrawling = this.boundClass.toEntity().isCrawling();

            return new BooleanInstance(isCrawling);
        }
    }

    private static class IsDescending extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isDecending", 0, arguments.size());
            }

            boolean isDescending = this.boundClass.toEntity().isDescending();

            return new BooleanInstance(isDescending);
        }
    }

    private static class IsFireImmune extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isFireImmune", 0, arguments.size());
            }

            boolean isFireImmune = this.boundClass.toEntity().isFireImmune();

            return new BooleanInstance(isFireImmune);
        }
    }

    private static class IsFrozen extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isFrozen", 0, arguments.size());
            }

            boolean isFrozen = this.boundClass.toEntity().isFrozen();

            return new BooleanInstance(isFrozen);
        }
    }

    private static class IsGlowing extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isGlowing", 0, arguments.size());
            }

            boolean isGlowing = this.boundClass.toEntity().isGlowing();

            return new BooleanInstance(isGlowing);
        }
    }

    private static class IsInFluid extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isInFluid", 0, arguments.size());
            }

            boolean isInFluid = this.boundClass.toEntity().isInFluid();

            return new BooleanInstance(isInFluid);
        }
    }

    private static class IsInLava extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isInLava", 0, arguments.size());
            }

            boolean isInLava = this.boundClass.toEntity().isInLava();

            return new BooleanInstance(isInLava);
        }
    }

    private static class IsInsideWall extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isInsideWall", 0, arguments.size());
            }

            boolean isInsideWall = this.boundClass.toEntity().isInsideWall();

            return new BooleanInstance(isInsideWall);
        }
    }

    private static class IsInvisible extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isInvisible", 0, arguments.size());
            }

            boolean isInvisible = this.boundClass.toEntity().isInvisible();

            return new BooleanInstance(isInvisible);
        }
    }

    private static class IsInvulnerable extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isInvulnerable", 0, arguments.size());
            }

            boolean isInvulnerable = this.boundClass.toEntity().isInvulnerable();

            return new BooleanInstance(isInvulnerable);
        }
    }

    private static class IsOnFire extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isOnFire", 0, arguments.size());
            }

            boolean isOnFire = this.boundClass.toEntity().isOnFire();

            return new BooleanInstance(isOnFire);
        }
    }

    private static class IsOnGround extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isOnGround", 0, arguments.size());
            }

            boolean isOnGround = this.boundClass.toEntity().isOnGround();

            return new BooleanInstance(isOnGround);
        }
    }

    private static class IsOnRail extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isOnRail", 0, arguments.size());
            }

            boolean isOnRail = this.boundClass.toEntity().isOnRail();

            return new BooleanInstance(isOnRail);
        }
    }

    private static class IsSilent extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isSilent", 0, arguments.size());
            }

            boolean isSilent = this.boundClass.toEntity().isSilent();

            return new BooleanInstance(isSilent);
        }
    }

    private static class IsSneaking extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isSneaking", 0, arguments.size());
            }

            boolean isSneaking = this.boundClass.toEntity().isSneaking();

            return new BooleanInstance(isSneaking);
        }
    }

    private static class IsSprinting extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isSprinting", 0, arguments.size());
            }

            boolean isSprinting = this.boundClass.toEntity().isSprinting();

            return new BooleanInstance(isSprinting);
        }
    }

    private static class IsSwimming extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isSwimming", 0, arguments.size());
            }

            boolean isSwimming = this.boundClass.toEntity().isSwimming();

            return new BooleanInstance(isSwimming);
        }
    }

    private static class IsTouchingWater extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isTouchingWater", 0, arguments.size());
            }

            boolean isTouchingWater = this.boundClass.toEntity().isTouchingWater();

            return new BooleanInstance(isTouchingWater);
        }
    }

    private static class IsTouchingWaterOrRain extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isTouchingWaterOrRain", 0, arguments.size());
            }

            boolean isTouchingWaterOrRain = this.boundClass.toEntity().isTouchingWaterOrRain();

            return new BooleanInstance(isTouchingWaterOrRain);
        }
    }

    private static class IsWet extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isWet", 0, arguments.size());
            }

            boolean isWet = this.boundClass.toEntity().isWet();

            return new BooleanInstance(isWet);
        }
    }

    private static class Kill extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("kill", 0, arguments.size());
            }

            this.boundClass.toEntity().kill();

            return new NullInstance();
        }
    }

    private static class RemoveAllPassengers extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("removeAllPassengers", 0, arguments.size());
            }

            this.boundClass.toEntity().removeAllPassengers();

            return new NullInstance();
        }
    }

    private static class ResetPortalCooldown extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("resetPortalCooldown", 0, arguments.size());
            }

            this.boundClass.toEntity().resetPortalCooldown();

            return new NullInstance();
        }
    }

    private static class SendMessage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("sendMessage", 1, arguments.size());
            }

            BuiltinClass message = arguments.get(0);

            if (!message.instanceOf(new StringType())) {
                throw ErrorHolder.argumentRequiresType(1, "sendMessage", new StringType(), message.getType());
            }

            this.boundClass.toEntity().sendMessage(Text.literal(message.toStringType()));

            return new NullInstance();
        }
    }

    private static class SetInvisible extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setInvisible", 1, arguments.size());
            }

            BuiltinClass invisible = arguments.get(0);

            if (!invisible.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setInvisible", new BooleanType(), invisible.getType());
            }

            this.boundClass.toEntity().setInvisible(invisible.toBoolean());

            return new NullInstance();
        }
    }

    private static class SetInvulnerable extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setInvulnerable", 1, arguments.size());
            }

            BuiltinClass invulnerable = arguments.get(0);

            if (!invulnerable.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setInvulnerable", new BooleanType(), invulnerable.getType());
            }

            this.boundClass.toEntity().setInvulnerable(invulnerable.toBoolean());

            return new NullInstance();
        }
    }

    private static class SetNoGravity extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setNoGravity", 1, arguments.size());
            }

            BuiltinClass noGravity = arguments.get(0);

            if (!noGravity.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setNoGravity", new BooleanType(), noGravity.getType());
            }

            this.boundClass.toEntity().setNoGravity(noGravity.toBoolean());

            return new NullInstance();
        }
    }

    private static class SetOnFire extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setOnFire", 1, arguments.size());
            }

            BuiltinClass onFire = arguments.get(0);

            if (!onFire.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setOnFire", new BooleanType(), onFire.getType());
            }

            this.boundClass.toEntity().setOnFire(onFire.toBoolean());

            return new NullInstance();
        }
    }

    private static class SetOnGround extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setOnGround", 1, arguments.size());
            }

            BuiltinClass onGround = arguments.get(0);

            if (!onGround.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setOnGround", new BooleanType(), onGround.getType());
            }

            this.boundClass.toEntity().setOnGround(onGround.toBoolean());

            return new NullInstance();
        }
    }

    private static class SetPortalCooldown extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setPortalCooldown", 1, arguments.size());
            }

            BuiltinClass portalCooldown = arguments.get(0);

            if (!portalCooldown.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setPortalCooldown", new BooleanType(), portalCooldown.getType());
            }

            this.boundClass.toEntity().setPortalCooldown(portalCooldown.toInteger());

            return new NullInstance();
        }
    }

    private static class SetPos extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 3) {
                throw ErrorHolder.invalidArgumentCount("setPos", 3, arguments.size());
            }

            BuiltinClass x = arguments.get(0);

            if (!x.instanceOf(new FloatType())) {
                throw ErrorHolder.argumentRequiresType(1, "setPos", new FloatType(), x.getType());
            }

            BuiltinClass y = arguments.get(1);

            if (!y.instanceOf(new FloatType())) {
                throw ErrorHolder.argumentRequiresType(1, "setPos", new FloatType(), y.getType());
            }

            BuiltinClass z = arguments.get(2);

            if (!z.instanceOf(new FloatType())) {
                throw ErrorHolder.argumentRequiresType(1, "setPos", new FloatType(), z.getType());
            }

            this.boundClass.toEntity().setPos(x.toInteger(), y.toInteger(), z.toInteger());

            return new NullInstance();
        }
    }

    private static class SetSilent extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSilent", 1, arguments.size());
            }

            BuiltinClass silent = arguments.get(0);

            if (!silent.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setSilent", new BooleanType(), silent.getType());
            }

            this.boundClass.toEntity().setSilent(silent.toBoolean());

            return new NullInstance();
        }
    }

    private static class SetSneaking extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSneaking", 1, arguments.size());
            }

            BuiltinClass sneaking = arguments.get(0);

            if (!sneaking.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setSneaking", new BooleanType(), sneaking.getType());
            }

            this.boundClass.toEntity().setSneaking(sneaking.toBoolean());

            return new NullInstance();
        }
    }

    private static class SetSprinting extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSprinting", 1, arguments.size());
            }

            BuiltinClass sprinting = arguments.get(0);

            if (!sprinting.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setSprinting", new BooleanType(), sprinting.getType());
            }

            this.boundClass.toEntity().setSprinting(sprinting.toBoolean());

            return new NullInstance();
        }
    }

    private static class SetSwimming extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSwimming", 1, arguments.size());
            }

            BuiltinClass swimming = arguments.get(0);

            if (!swimming.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setSwimming", new BooleanType(), swimming.getType());
            }

            this.boundClass.toEntity().setSwimming(swimming.toBoolean());

            return new NullInstance();
        }
    }

    private static class ShouldDismountUnderwater extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("shouldDismountUnderwater", 0, arguments.size());
            }

            boolean shouldDismountUnderwater = this.boundClass.toEntity().shouldDismountUnderwater();

            return new BooleanInstance(shouldDismountUnderwater);
        }
    }

    private static class StopRiding extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("stopRiding", 0, arguments.size());
            }

            this.boundClass.toEntity().stopRiding();

            return new NullInstance();
        }
    }

    private static class Teleport extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 3) {
                throw ErrorHolder.invalidArgumentCount("teleport", 3, arguments.size());
            }

            BuiltinClass destX = arguments.get(0);

            if (!destX.instanceOf(new FloatType())) {
                throw ErrorHolder.argumentRequiresType(1, "teleport", new FloatType(), destX.getType());
            }

            BuiltinClass destY = arguments.get(1);

            if (!destY.instanceOf(new FloatType())) {
                throw ErrorHolder.argumentRequiresType(2, "teleport", new FloatType(), destY.getType());
            }

            BuiltinClass destZ = arguments.get(2);

            if (!destZ.instanceOf(new FloatType())) {
                throw ErrorHolder.argumentRequiresType(3, "teleport", new FloatType(), destZ.getType());
            }

            this.boundClass.toEntity().teleport(destX.toFloat(), destY.toFloat(), destZ.toFloat());

            return new NullInstance();
        }
    }
}
