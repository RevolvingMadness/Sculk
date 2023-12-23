package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.*;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Objects;

public class EntityClass extends BaseClassExpressionNode {
    public final Entity entity;

    public EntityClass(Entity entity) {
        this.entity = entity;

        this.variableScope.declare(true, new IdentifierExpressionNode("addCommandTag"), new AddCommandTag());
        this.variableScope.declare(true, new IdentifierExpressionNode("dismountVehicle"), new DismountVehicle());
        this.variableScope.declare(true, new IdentifierExpressionNode("getBlockPos"), new GetBlockPos());
        this.variableScope.declare(true, new IdentifierExpressionNode("getBlockX"), new GetBlockX());
        this.variableScope.declare(true, new IdentifierExpressionNode("getBlockY"), new GetBlockY());
        this.variableScope.declare(true, new IdentifierExpressionNode("getBlockZ"), new GetBlockZ());
        this.variableScope.declare(true, new IdentifierExpressionNode("getX"), new GetX());
        this.variableScope.declare(true, new IdentifierExpressionNode("getY"), new GetY());
        this.variableScope.declare(true, new IdentifierExpressionNode("getZ"), new GetZ());
        this.variableScope.declare(true, new IdentifierExpressionNode("hasVehicle"), new HasVehicle());
        this.variableScope.declare(true, new IdentifierExpressionNode("isCrawling"), new IsCrawling());
        this.variableScope.declare(true, new IdentifierExpressionNode("isFrozen"), new IsFrozen());
        this.variableScope.declare(true, new IdentifierExpressionNode("isGlowing"), new IsGlowing());
        this.variableScope.declare(true, new IdentifierExpressionNode("isFireImmune"), new IsFireImmune());
        this.variableScope.declare(true, new IdentifierExpressionNode("isDescending"), new IsDescending());
        this.variableScope.declare(true, new IdentifierExpressionNode("isInFluid"), new IsInFluid());
        this.variableScope.declare(true, new IdentifierExpressionNode("isInLava"), new IsInLava());
        this.variableScope.declare(true, new IdentifierExpressionNode("isInsideWall"), new IsInsideWall());
        this.variableScope.declare(true, new IdentifierExpressionNode("isInvisible"), new IsInvisible());
        this.variableScope.declare(true, new IdentifierExpressionNode("isInvulnerable"), new IsInvulnerable());
        this.variableScope.declare(true, new IdentifierExpressionNode("isOnFire"), new IsOnFire());
        this.variableScope.declare(true, new IdentifierExpressionNode("isOnGround"), new IsOnGround());
        this.variableScope.declare(true, new IdentifierExpressionNode("isOnRail"), new IsOnRail());
        this.variableScope.declare(true, new IdentifierExpressionNode("isSilent"), new IsSilent());
        this.variableScope.declare(true, new IdentifierExpressionNode("isSneaking"), new IsSneaking());
        this.variableScope.declare(true, new IdentifierExpressionNode("isSprinting"), new IsSprinting());
        this.variableScope.declare(true, new IdentifierExpressionNode("isSwimming"), new IsSwimming());
        this.variableScope.declare(true, new IdentifierExpressionNode("isTouchingWater"), new IsTouchingWater());
        this.variableScope.declare(true, new IdentifierExpressionNode("isTouchingWaterOrRain"), new IsTouchingWaterOrRain());
        this.variableScope.declare(true, new IdentifierExpressionNode("isWet"), new IsWet());
        this.variableScope.declare(true, new IdentifierExpressionNode("kill"), new Kill());
        this.variableScope.declare(true, new IdentifierExpressionNode("removeAllPassengers"), new RemoveAllPassengers());
        this.variableScope.declare(true, new IdentifierExpressionNode("resetPortalCooldown"), new ResetPortalCooldown());
        this.variableScope.declare(true, new IdentifierExpressionNode("sendMessage"), new SendMessage());
        this.variableScope.declare(true, new IdentifierExpressionNode("setInvisible"), new SetInvisible());
        this.variableScope.declare(true, new IdentifierExpressionNode("setInvulnerable"), new SetInvulnerable());
        this.variableScope.declare(true, new IdentifierExpressionNode("setNoGravity"), new SetNoGravity());
        this.variableScope.declare(true, new IdentifierExpressionNode("setOnFire"), new SetOnFire());
        this.variableScope.declare(true, new IdentifierExpressionNode("setOnGround"), new SetOnGround());
        this.variableScope.declare(true, new IdentifierExpressionNode("setPortalCooldown"), new SetPortalCooldown());
        this.variableScope.declare(true, new IdentifierExpressionNode("setPos"), new SetPos());
        this.variableScope.declare(true, new IdentifierExpressionNode("setSilent"), new SetSilent());
        this.variableScope.declare(true, new IdentifierExpressionNode("setSneaking"), new SetSneaking());
        this.variableScope.declare(true, new IdentifierExpressionNode("setSprinting"), new SetSprinting());
        this.variableScope.declare(true, new IdentifierExpressionNode("setSwimming"), new SetSwimming());
        this.variableScope.declare(true, new IdentifierExpressionNode("shouldDismountUnderwater"), new ShouldDismountUnderwater());
        this.variableScope.declare(true, new IdentifierExpressionNode("stopRiding"), new StopRiding());
        this.variableScope.declare(true, new IdentifierExpressionNode("teleport"), new Teleport());
        this.variableScope.declare(true, new IdentifierExpressionNode("equalTo"), new EqualTo());
        this.variableScope.declare(true, new IdentifierExpressionNode("notEqualTo"), new NotEqualTo());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntityClass that = (EntityClass) o;
        return Objects.equals(this.entity, that.entity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.entity);
    }

    @Override
    public String getType() {
        return "Entity";
    }

    public class EqualTo extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'equalTo' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(EntityClass.this.equals(o));
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class NotEqualTo extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'notEqualTo' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(!EntityClass.this.equals(o));
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class AddCommandTag extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'addCommandTag' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode commandTag = arguments.get(0);

            if (!commandTag.getType().equals("String")) {
                throw new TypeError("Argument 1 for function 'addCommandTag' requires type 'String' but got '" + commandTag.getType() + "'");
            }

            EntityClass.this.entity.addCommandTag(((StringClass) commandTag).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class DismountVehicle extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'dismountVehicle' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            EntityClass.this.entity.dismountVehicle();

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class GetBlockPos extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getBlockPos' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            BlockPos blockPos = EntityClass.this.entity.getBlockPos();

            return new Vec3dClass(blockPos.toCenterPos());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class GetBlockX extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getBlockX' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            int blockX = EntityClass.this.entity.getBlockX();

            return new IntegerClass(blockX);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class GetBlockY extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getBlockY' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            int blockY = EntityClass.this.entity.getBlockY();

            return new IntegerClass(blockY);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class GetBlockZ extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getBlockZ' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            int blockZ = EntityClass.this.entity.getBlockZ();

            return new IntegerClass(blockZ);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class GetX extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getX' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            double x = EntityClass.this.entity.getX();

            return new FloatClass(x);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class GetY extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getY' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            double y = EntityClass.this.entity.getY();

            return new FloatClass(y);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class GetZ extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getZ' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            double z = EntityClass.this.entity.getZ();

            return new FloatClass(z);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class HasVehicle extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'hasVehicle' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean hasVehicle = EntityClass.this.entity.hasVehicle();

            return new BooleanClass(hasVehicle);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsCrawling extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isCrawling' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isCrawling = EntityClass.this.entity.isCrawling();

            return new BooleanClass(isCrawling);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsDescending extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isDecending' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isDescending = EntityClass.this.entity.isDescending();

            return new BooleanClass(isDescending);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsFireImmune extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isFireImmune' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isFireImmune = EntityClass.this.entity.isFireImmune();

            return new BooleanClass(isFireImmune);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsFrozen extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isFrozen' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isFrozen = EntityClass.this.entity.isFrozen();

            return new BooleanClass(isFrozen);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsGlowing extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isGlowing' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isGlowing = EntityClass.this.entity.isGlowing();

            return new BooleanClass(isGlowing);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsInFluid extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isInFluid' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isInFluid = EntityClass.this.entity.isInFluid();

            return new BooleanClass(isInFluid);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsInLava extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isInLava' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isInLava = EntityClass.this.entity.isInLava();

            return new BooleanClass(isInLava);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsInsideWall extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isInsideWall' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isInsideWall = EntityClass.this.entity.isInsideWall();

            return new BooleanClass(isInsideWall);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsInvisible extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isInvisible' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isInvisible = EntityClass.this.entity.isInvisible();

            return new BooleanClass(isInvisible);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsInvulnerable extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isInvulnerable' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isInvulnerable = EntityClass.this.entity.isInvulnerable();

            return new BooleanClass(isInvulnerable);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsOnFire extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isOnFire' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isOnFire = EntityClass.this.entity.isOnFire();

            return new BooleanClass(isOnFire);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsOnGround extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isOnGround' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isOnGround = EntityClass.this.entity.isOnGround();

            return new BooleanClass(isOnGround);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsOnRail extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isOnRail' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isOnRail = EntityClass.this.entity.isOnRail();

            return new BooleanClass(isOnRail);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsSilent extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isSilent' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isSilent = EntityClass.this.entity.isSilent();

            return new BooleanClass(isSilent);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsSneaking extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isSneaking' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isSneaking = EntityClass.this.entity.isSneaking();

            return new BooleanClass(isSneaking);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsSprinting extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isSprinting' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isSprinting = EntityClass.this.entity.isSprinting();

            return new BooleanClass(isSprinting);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsSwimming extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isSwimming' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isSwimming = EntityClass.this.entity.isSwimming();

            return new BooleanClass(isSwimming);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsTouchingWater extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isTouchingWater' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isTouchingWater = EntityClass.this.entity.isTouchingWater();

            return new BooleanClass(isTouchingWater);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsTouchingWaterOrRain extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isTouchingWaterOrRain' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isTouchingWaterOrRain = EntityClass.this.entity.isTouchingWaterOrRain();

            return new BooleanClass(isTouchingWaterOrRain);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsWet extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isWet' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isWet = EntityClass.this.entity.isWet();

            return new BooleanClass(isWet);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Kill extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'kill' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            EntityClass.this.entity.kill();

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class RemoveAllPassengers extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'removeAllPassengers' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            EntityClass.this.entity.removeAllPassengers();

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class ResetPortalCooldown extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'resetPortalCooldown' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            EntityClass.this.entity.resetPortalCooldown();

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SendMessage extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'sendMessage' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode message = arguments.get(0);

            if (!message.getType().equals("String")) {
                throw new TypeError("Argument 1 for function 'sendMessage' requires type 'String' but got '" + message.getType() + "'");
            }

            EntityClass.this.entity.sendMessage(Text.literal(((StringClass) message).value));

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SetInvisible extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setInvisible' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode invisible = arguments.get(0);

            if (!invisible.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setInvisible' requires type 'Boolean' but got '" + invisible.getType() + "'");
            }

            EntityClass.this.entity.setInvisible(((BooleanClass) invisible).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SetInvulnerable extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setInvulnerable' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode invulnerable = arguments.get(0);

            if (!invulnerable.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setInvulnerable' requires type 'Boolean' but got '" + invulnerable.getType() + "'");
            }

            EntityClass.this.entity.setInvulnerable(((BooleanClass) invulnerable).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SetNoGravity extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setNoGravity' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode noGravity = arguments.get(0);

            if (!noGravity.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setNoGravity' requires type 'Boolean' but got '" + noGravity.getType() + "'");
            }

            EntityClass.this.entity.setNoGravity(((BooleanClass) noGravity).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SetOnFire extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setOnFire' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode onFire = arguments.get(0);

            if (!onFire.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setOnFire' requires type 'Boolean' but got '" + onFire.getType() + "'");
            }

            EntityClass.this.entity.setOnFire(((BooleanClass) onFire).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SetOnGround extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setOnGround' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode onGround = arguments.get(0);

            if (!onGround.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setOnGround' requires type 'Boolean' but got '" + onGround.getType() + "'");
            }

            EntityClass.this.entity.setOnGround(((BooleanClass) onGround).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SetPortalCooldown extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setPortalCooldown' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode portalCooldown = arguments.get(0);

            if (!portalCooldown.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setPortalCooldown' requires type 'Boolean' but got '" + portalCooldown.getType() + "'");
            }

            EntityClass.this.entity.setPortalCooldown(((IntegerClass) portalCooldown).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SetPos extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 3) {
                throw new SyntaxError("Function 'setPos' takes 3 arguments but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode x = arguments.get(0);

            if (!x.getType().equals("Float")) {
                throw new TypeError("Argument 1 for function 'setPos' requires type 'Float' but got '" + x.getType() + "'");
            }

            BaseClassExpressionNode y = arguments.get(1);

            if (!y.getType().equals("Float")) {
                throw new TypeError("Argument 1 for function 'setPos' requires type 'Float' but got '" + y.getType() + "'");
            }

            BaseClassExpressionNode z = arguments.get(2);

            if (!z.getType().equals("Float")) {
                throw new TypeError("Argument 1 for function 'setPos' requires type 'Float' but got '" + z.getType() + "'");
            }

            EntityClass.this.entity.setPos(((IntegerClass) x).value, ((IntegerClass) y).value, ((IntegerClass) z).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SetSilent extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSilent' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode silent = arguments.get(0);

            if (!silent.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setSilent' requires type 'Boolean' but got '" + silent.getType() + "'");
            }

            EntityClass.this.entity.setSilent(((BooleanClass) silent).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SetSneaking extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSneaking' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode sneaking = arguments.get(0);

            if (!sneaking.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setSneaking' requires type 'Boolean' but got '" + sneaking.getType() + "'");
            }

            EntityClass.this.entity.setSneaking(((BooleanClass) sneaking).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SetSprinting extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSprinting' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode sprinting = arguments.get(0);

            if (!sprinting.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setSprinting' requires type 'Boolean' but got '" + sprinting.getType() + "'");
            }

            EntityClass.this.entity.setSprinting(((BooleanClass) sprinting).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SetSwimming extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSwimming' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode swimming = arguments.get(0);

            if (!swimming.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setSwimming' requires type 'Boolean' but got '" + swimming.getType() + "'");
            }

            EntityClass.this.entity.setSwimming(((BooleanClass) swimming).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class ShouldDismountUnderwater extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'shouldDismountUnderwater' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean shouldDismountUnderwater = EntityClass.this.entity.shouldDismountUnderwater();

            return new BooleanClass(shouldDismountUnderwater);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class StopRiding extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'stopRiding' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            EntityClass.this.entity.stopRiding();

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Teleport extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 3) {
                throw new SyntaxError("Function 'teleport' takes 3 arguments but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode destX = arguments.get(0);

            if (!destX.getType().equals("Float")) {
                throw new TypeError("Argument 1 for function 'teleport' requires type 'Float' but got '" + destX.getType() + "'");
            }

            BaseClassExpressionNode destY = arguments.get(1);

            if (!destY.getType().equals("Float")) {
                throw new TypeError("Argument 2 for function 'teleport' requires type 'Float' but got '" + destY.getType() + "'");
            }

            BaseClassExpressionNode destZ = arguments.get(2);

            if (!destZ.getType().equals("Float")) {
                throw new TypeError("Argument 3 for function 'teleport' requires type 'Float' but got '" + destZ.getType() + "'");
            }

            EntityClass.this.entity.teleport(((FloatClass) destX).value, ((FloatClass) destY).value, ((FloatClass) destZ).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }
}
