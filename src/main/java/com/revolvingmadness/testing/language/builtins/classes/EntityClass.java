package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.*;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Objects;

public class EntityClass extends BaseClassExpressionNode {
    public final Entity entity;

    public EntityClass(Entity entity) {
        this.entity = entity;

        this.variableScope.declare(true, "addCommandTag", new AddCommandTag());
        this.variableScope.declare(true, "dismountVehicle", new DismountVehicle());
        this.variableScope.declare(true, "getBlockPos", new GetBlockPos());
        this.variableScope.declare(true, "getBlockX", new GetBlockX());
        this.variableScope.declare(true, "getBlockY", new GetBlockY());
        this.variableScope.declare(true, "getBlockZ", new GetBlockZ());
        this.variableScope.declare(true, "getX", new GetX());
        this.variableScope.declare(true, "getY", new GetY());
        this.variableScope.declare(true, "getZ", new GetZ());
        this.variableScope.declare(true, "hasVehicle", new HasVehicle());
        this.variableScope.declare(true, "isCrawling", new IsCrawling());
        this.variableScope.declare(true, "isFrozen", new IsFrozen());
        this.variableScope.declare(true, "isGlowing", new IsGlowing());
        this.variableScope.declare(true, "isFireImmune", new IsFireImmune());
        this.variableScope.declare(true, "isDescending", new IsDescending());
        this.variableScope.declare(true, "isInFluid", new IsInFluid());
        this.variableScope.declare(true, "isInLava", new IsInLava());
        this.variableScope.declare(true, "isInsideWall", new IsInsideWall());
        this.variableScope.declare(true, "isInvisible", new IsInvisible());
        this.variableScope.declare(true, "isInvulnerable", new IsInvulnerable());
        this.variableScope.declare(true, "isOnFire", new IsOnFire());
        this.variableScope.declare(true, "isOnGround", new IsOnGround());
        this.variableScope.declare(true, "isOnRail", new IsOnRail());
        this.variableScope.declare(true, "isSilent", new IsSilent());
        this.variableScope.declare(true, "isSneaking", new IsSneaking());
        this.variableScope.declare(true, "isSprinting", new IsSprinting());
        this.variableScope.declare(true, "isSwimming", new IsSwimming());
        this.variableScope.declare(true, "isTouchingWater", new IsTouchingWater());
        this.variableScope.declare(true, "isTouchingWaterOrRain", new IsTouchingWaterOrRain());
        this.variableScope.declare(true, "isWet", new IsWet());
        this.variableScope.declare(true, "kill", new Kill());
        this.variableScope.declare(true, "removeAllPassengers", new RemoveAllPassengers());
        this.variableScope.declare(true, "resetPortalCooldown", new ResetPortalCooldown());
        this.variableScope.declare(true, "sendMessage", new SendMessage());
        this.variableScope.declare(true, "setInvisible", new SetInvisible());
        this.variableScope.declare(true, "setInvulnerable", new SetInvulnerable());
        this.variableScope.declare(true, "setNoGravity", new SetNoGravity());
        this.variableScope.declare(true, "setOnFire", new SetOnFire());
        this.variableScope.declare(true, "setOnGround", new SetOnGround());
        this.variableScope.declare(true, "setPortalCooldown", new SetPortalCooldown());
        this.variableScope.declare(true, "setPos", new SetPos());
        this.variableScope.declare(true, "setSilent", new SetSilent());
        this.variableScope.declare(true, "setSneaking", new SetSneaking());
        this.variableScope.declare(true, "setSprinting", new SetSprinting());
        this.variableScope.declare(true, "setSwimming", new SetSwimming());
        this.variableScope.declare(true, "shouldDismountUnderwater", new ShouldDismountUnderwater());
        this.variableScope.declare(true, "stopRiding", new StopRiding());
        this.variableScope.declare(true, "teleport", new Teleport());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        EntityClass that = (EntityClass) o;
        return Objects.equals(this.entity, that.entity);
    }

    @Override
    public String getType() {
        return "Entity";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.entity);
    }

    public class AddCommandTag extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("addCommandTag", 1, arguments.size());
            }

            BaseClassExpressionNode commandTag = arguments.get(0);

            if (!commandTag.getType().equals("String")) {
                throw ErrorHolder.argumentRequiresType(1, "addCommandTag", "String", commandTag.getType());
            }

            EntityClass.this.entity.addCommandTag(((StringClass) commandTag).value);

            return new NullClass();
        }
    }

    public class DismountVehicle extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("dismountVehicle", 0, arguments.size());
            }

            EntityClass.this.entity.dismountVehicle();

            return new NullClass();
        }
    }

    public class GetBlockPos extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getBlockPos", 0, arguments.size());
            }

            BlockPos blockPos = EntityClass.this.entity.getBlockPos();

            return new Vec3dClass(blockPos.toCenterPos());
        }
    }

    public class GetBlockX extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getBlockX", 0, arguments.size());
            }

            int blockX = EntityClass.this.entity.getBlockX();

            return new IntegerClass(blockX);
        }
    }

    public class GetBlockY extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getBlockY", 0, arguments.size());
            }

            int blockY = EntityClass.this.entity.getBlockY();

            return new IntegerClass(blockY);
        }
    }

    public class GetBlockZ extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getBlockZ", 0, arguments.size());
            }

            int blockZ = EntityClass.this.entity.getBlockZ();

            return new IntegerClass(blockZ);
        }
    }

    public class GetX extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getX", 0, arguments.size());
            }

            double x = EntityClass.this.entity.getX();

            return new FloatClass(x);
        }
    }

    public class GetY extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getY", 0, arguments.size());
            }

            double y = EntityClass.this.entity.getY();

            return new FloatClass(y);
        }
    }

    public class GetZ extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getZ", 0, arguments.size());
            }

            double z = EntityClass.this.entity.getZ();

            return new FloatClass(z);
        }
    }

    public class HasVehicle extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("hasVehicle", 0, arguments.size());
            }

            boolean hasVehicle = EntityClass.this.entity.hasVehicle();

            return new BooleanClass(hasVehicle);
        }
    }

    public class IsCrawling extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isCrawling", 0, arguments.size());
            }

            boolean isCrawling = EntityClass.this.entity.isCrawling();

            return new BooleanClass(isCrawling);
        }
    }

    public class IsDescending extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isDecending", 0, arguments.size());
            }

            boolean isDescending = EntityClass.this.entity.isDescending();

            return new BooleanClass(isDescending);
        }
    }

    public class IsFireImmune extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isFireImmune", 0, arguments.size());
            }

            boolean isFireImmune = EntityClass.this.entity.isFireImmune();

            return new BooleanClass(isFireImmune);
        }
    }

    public class IsFrozen extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isFrozen", 0, arguments.size());
            }

            boolean isFrozen = EntityClass.this.entity.isFrozen();

            return new BooleanClass(isFrozen);
        }
    }

    public class IsGlowing extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isGlowing", 0, arguments.size());
            }

            boolean isGlowing = EntityClass.this.entity.isGlowing();

            return new BooleanClass(isGlowing);
        }
    }

    public class IsInFluid extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isInFluid", 0, arguments.size());
            }

            boolean isInFluid = EntityClass.this.entity.isInFluid();

            return new BooleanClass(isInFluid);
        }
    }

    public class IsInLava extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isInLava", 0, arguments.size());
            }

            boolean isInLava = EntityClass.this.entity.isInLava();

            return new BooleanClass(isInLava);
        }
    }

    public class IsInsideWall extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isInsideWall", 0, arguments.size());
            }

            boolean isInsideWall = EntityClass.this.entity.isInsideWall();

            return new BooleanClass(isInsideWall);
        }
    }

    public class IsInvisible extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isInvisible", 0, arguments.size());
            }

            boolean isInvisible = EntityClass.this.entity.isInvisible();

            return new BooleanClass(isInvisible);
        }
    }

    public class IsInvulnerable extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isInvulnerable", 0, arguments.size());
            }

            boolean isInvulnerable = EntityClass.this.entity.isInvulnerable();

            return new BooleanClass(isInvulnerable);
        }
    }

    public class IsOnFire extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isOnFire", 0, arguments.size());
            }

            boolean isOnFire = EntityClass.this.entity.isOnFire();

            return new BooleanClass(isOnFire);
        }
    }

    public class IsOnGround extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isOnGround", 0, arguments.size());
            }

            boolean isOnGround = EntityClass.this.entity.isOnGround();

            return new BooleanClass(isOnGround);
        }
    }

    public class IsOnRail extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isOnRail", 0, arguments.size());
            }

            boolean isOnRail = EntityClass.this.entity.isOnRail();

            return new BooleanClass(isOnRail);
        }
    }

    public class IsSilent extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isSilent", 0, arguments.size());
            }

            boolean isSilent = EntityClass.this.entity.isSilent();

            return new BooleanClass(isSilent);
        }
    }

    public class IsSneaking extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isSneaking", 0, arguments.size());
            }

            boolean isSneaking = EntityClass.this.entity.isSneaking();

            return new BooleanClass(isSneaking);
        }
    }

    public class IsSprinting extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isSprinting", 0, arguments.size());
            }

            boolean isSprinting = EntityClass.this.entity.isSprinting();

            return new BooleanClass(isSprinting);
        }
    }

    public class IsSwimming extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isSwimming", 0, arguments.size());
            }

            boolean isSwimming = EntityClass.this.entity.isSwimming();

            return new BooleanClass(isSwimming);
        }
    }

    public class IsTouchingWater extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isTouchingWater", 0, arguments.size());
            }

            boolean isTouchingWater = EntityClass.this.entity.isTouchingWater();

            return new BooleanClass(isTouchingWater);
        }
    }

    public class IsTouchingWaterOrRain extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isTouchingWaterOrRain", 0, arguments.size());
            }

            boolean isTouchingWaterOrRain = EntityClass.this.entity.isTouchingWaterOrRain();

            return new BooleanClass(isTouchingWaterOrRain);
        }
    }

    public class IsWet extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isWet", 0, arguments.size());
            }

            boolean isWet = EntityClass.this.entity.isWet();

            return new BooleanClass(isWet);
        }
    }

    public class Kill extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("kill", 0, arguments.size());
            }

            EntityClass.this.entity.kill();

            return new NullClass();
        }
    }

    public class RemoveAllPassengers extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("removeAllPassengers", 0, arguments.size());
            }

            EntityClass.this.entity.removeAllPassengers();

            return new NullClass();
        }
    }

    public class ResetPortalCooldown extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("resetPortalCooldown", 0, arguments.size());
            }

            EntityClass.this.entity.resetPortalCooldown();

            return new NullClass();
        }
    }

    public class SendMessage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("sendMessage", 1, arguments.size());
            }

            BaseClassExpressionNode message = arguments.get(0);

            if (!message.getType().equals("String")) {
                throw ErrorHolder.argumentRequiresType(1, "sendMessage", "String", message.getType());
            }

            EntityClass.this.entity.sendMessage(Text.literal(((StringClass) message).value));

            return new NullClass();
        }
    }

    public class SetInvisible extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setInvisible", 1, arguments.size());
            }

            BaseClassExpressionNode invisible = arguments.get(0);

            if (!invisible.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setInvisible", "Boolean", invisible.getType());
            }

            EntityClass.this.entity.setInvisible(((BooleanClass) invisible).value);

            return new NullClass();
        }
    }

    public class SetInvulnerable extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setInvulnerable", 1, arguments.size());
            }

            BaseClassExpressionNode invulnerable = arguments.get(0);

            if (!invulnerable.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setInvulnerable", "Boolean", invulnerable.getType());
            }

            EntityClass.this.entity.setInvulnerable(((BooleanClass) invulnerable).value);

            return new NullClass();
        }
    }

    public class SetNoGravity extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setNoGravity", 1, arguments.size());
            }

            BaseClassExpressionNode noGravity = arguments.get(0);

            if (!noGravity.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setNoGravity", "Boolean", noGravity.getType());
            }

            EntityClass.this.entity.setNoGravity(((BooleanClass) noGravity).value);

            return new NullClass();
        }
    }

    public class SetOnFire extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setOnFire", 1, arguments.size());
            }

            BaseClassExpressionNode onFire = arguments.get(0);

            if (!onFire.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setOnFire", "Boolean", onFire.getType());
            }

            EntityClass.this.entity.setOnFire(((BooleanClass) onFire).value);

            return new NullClass();
        }
    }

    public class SetOnGround extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setOnGround", 1, arguments.size());
            }

            BaseClassExpressionNode onGround = arguments.get(0);

            if (!onGround.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setOnGround", "Boolean", onGround.getType());
            }

            EntityClass.this.entity.setOnGround(((BooleanClass) onGround).value);

            return new NullClass();
        }
    }

    public class SetPortalCooldown extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setPortalCooldown", 1, arguments.size());
            }

            BaseClassExpressionNode portalCooldown = arguments.get(0);

            if (!portalCooldown.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setPortalCooldown", "Boolean", portalCooldown.getType());
            }

            EntityClass.this.entity.setPortalCooldown(((IntegerClass) portalCooldown).value);

            return new NullClass();
        }
    }

    public class SetPos extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 3) {
                throw ErrorHolder.invalidArgumentCount("setPos", 3, arguments.size());
            }

            BaseClassExpressionNode x = arguments.get(0);

            if (!x.getType().equals("Float")) {
                throw ErrorHolder.argumentRequiresType(1, "setPos", "Float", x.getType());
            }

            BaseClassExpressionNode y = arguments.get(1);

            if (!y.getType().equals("Float")) {
                throw ErrorHolder.argumentRequiresType(1, "setPos", "Float", y.getType());
            }

            BaseClassExpressionNode z = arguments.get(2);

            if (!z.getType().equals("Float")) {
                throw ErrorHolder.argumentRequiresType(1, "setPos", "Float", z.getType());
            }

            EntityClass.this.entity.setPos(((IntegerClass) x).value, ((IntegerClass) y).value, ((IntegerClass) z).value);

            return new NullClass();
        }
    }

    public class SetSilent extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSilent", 1, arguments.size());
            }

            BaseClassExpressionNode silent = arguments.get(0);

            if (!silent.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setSilent", "Boolean", silent.getType());
            }

            EntityClass.this.entity.setSilent(((BooleanClass) silent).value);

            return new NullClass();
        }
    }

    public class SetSneaking extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSneaking", 1, arguments.size());
            }

            BaseClassExpressionNode sneaking = arguments.get(0);

            if (!sneaking.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setSneaking", "Boolean", sneaking.getType());
            }

            EntityClass.this.entity.setSneaking(((BooleanClass) sneaking).value);

            return new NullClass();
        }
    }

    public class SetSprinting extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSprinting", 1, arguments.size());
            }

            BaseClassExpressionNode sprinting = arguments.get(0);

            if (!sprinting.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setSprinting", "Boolean", sprinting.getType());
            }

            EntityClass.this.entity.setSprinting(((BooleanClass) sprinting).value);

            return new NullClass();
        }
    }

    public class SetSwimming extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSwimming", 1, arguments.size());
            }

            BaseClassExpressionNode swimming = arguments.get(0);

            if (!swimming.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setSwimming", "Boolean", swimming.getType());
            }

            EntityClass.this.entity.setSwimming(((BooleanClass) swimming).value);

            return new NullClass();
        }
    }

    public class ShouldDismountUnderwater extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("shouldDismountUnderwater", 0, arguments.size());
            }

            boolean shouldDismountUnderwater = EntityClass.this.entity.shouldDismountUnderwater();

            return new BooleanClass(shouldDismountUnderwater);
        }
    }

    public class StopRiding extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("stopRiding", 0, arguments.size());
            }

            EntityClass.this.entity.stopRiding();

            return new NullClass();
        }
    }

    public class Teleport extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 3) {
                throw ErrorHolder.invalidArgumentCount("teleport", 3, arguments.size());
            }

            BaseClassExpressionNode destX = arguments.get(0);

            if (!destX.getType().equals("Float")) {
                throw ErrorHolder.argumentRequiresType(1, "teleport", "Float", destX.getType());
            }

            BaseClassExpressionNode destY = arguments.get(1);

            if (!destY.getType().equals("Float")) {
                throw ErrorHolder.argumentRequiresType(2, "teleport", "Float", destY.getType());
            }

            BaseClassExpressionNode destZ = arguments.get(2);

            if (!destZ.getType().equals("Float")) {
                throw ErrorHolder.argumentRequiresType(3, "teleport", "Float", destZ.getType());
            }

            EntityClass.this.entity.teleport(((FloatClass) destX).value, ((FloatClass) destY).value, ((FloatClass) destZ).value);

            return new NullClass();
        }
    }
}
