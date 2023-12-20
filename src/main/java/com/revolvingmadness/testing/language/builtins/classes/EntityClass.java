package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.*;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class EntityClass extends BuiltinClass {
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
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("Entity");
    }

    public class AddCommandTag implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'addCommandTag' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode commandTag = arguments.get(0).interpret(script);

            if (!commandTag.getType().equals(new IdentifierExpressionNode("string"))) {
                throw new TypeError("Argument 1 for function 'addCommandTag' requires type 'string' but got '" + commandTag.getType() + "'");
            }

            EntityClass.this.entity.addCommandTag(((StringExpressionNode) commandTag).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class DismountVehicle implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'dismountVehicle' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            EntityClass.this.entity.dismountVehicle();

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class GetBlockPos implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getBlockPos' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            BlockPos blockPos = EntityClass.this.entity.getBlockPos();

            return new Vec3dClass(blockPos.toCenterPos());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class GetBlockX implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getBlockX' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            int blockX = EntityClass.this.entity.getBlockX();

            return new IntegerExpressionNode(blockX);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class GetBlockY implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getBlockY' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            int blockY = EntityClass.this.entity.getBlockY();

            return new IntegerExpressionNode(blockY);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class GetBlockZ implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getBlockZ' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            int blockZ = EntityClass.this.entity.getBlockZ();

            return new IntegerExpressionNode(blockZ);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class GetX implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getX' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            double x = EntityClass.this.entity.getX();

            return new FloatExpressionNode(x);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class GetY implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getY' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            double y = EntityClass.this.entity.getY();

            return new FloatExpressionNode(y);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class GetZ implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getZ' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            double z = EntityClass.this.entity.getZ();

            return new FloatExpressionNode(z);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class HasVehicle implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'hasVehicle' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean hasVehicle = EntityClass.this.entity.hasVehicle();

            return new BooleanExpressionNode(hasVehicle);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsCrawling implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isCrawling' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isCrawling = EntityClass.this.entity.isCrawling();

            return new BooleanExpressionNode(isCrawling);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsDescending implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isDecending' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isDescending = EntityClass.this.entity.isDescending();

            return new BooleanExpressionNode(isDescending);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsFireImmune implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isFireImmune' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isFireImmune = EntityClass.this.entity.isFireImmune();

            return new BooleanExpressionNode(isFireImmune);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsFrozen implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isFrozen' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isFrozen = EntityClass.this.entity.isFrozen();

            return new BooleanExpressionNode(isFrozen);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsGlowing implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isGlowing' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isGlowing = EntityClass.this.entity.isGlowing();

            return new BooleanExpressionNode(isGlowing);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsInFluid implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isInFluid' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isInFluid = EntityClass.this.entity.isInFluid();

            return new BooleanExpressionNode(isInFluid);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsInLava implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isInLava' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isInLava = EntityClass.this.entity.isInLava();

            return new BooleanExpressionNode(isInLava);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsInsideWall implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isInsideWall' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isInsideWall = EntityClass.this.entity.isInsideWall();

            return new BooleanExpressionNode(isInsideWall);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsInvisible implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isInvisible' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isInvisible = EntityClass.this.entity.isInvisible();

            return new BooleanExpressionNode(isInvisible);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsInvulnerable implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isInvulnerable' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isInvulnerable = EntityClass.this.entity.isInvulnerable();

            return new BooleanExpressionNode(isInvulnerable);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsOnFire implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isOnFire' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isOnFire = EntityClass.this.entity.isOnFire();

            return new BooleanExpressionNode(isOnFire);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsOnGround implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isOnGround' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isOnGround = EntityClass.this.entity.isOnGround();

            return new BooleanExpressionNode(isOnGround);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsOnRail implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isOnRail' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isOnRail = EntityClass.this.entity.isOnRail();

            return new BooleanExpressionNode(isOnRail);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsSilent implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isSilent' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isSilent = EntityClass.this.entity.isSilent();

            return new BooleanExpressionNode(isSilent);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsSneaking implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isSneaking' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isSneaking = EntityClass.this.entity.isSneaking();

            return new BooleanExpressionNode(isSneaking);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsSprinting implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isSprinting' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isSprinting = EntityClass.this.entity.isSprinting();

            return new BooleanExpressionNode(isSprinting);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsSwimming implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isSwimming' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isSwimming = EntityClass.this.entity.isSwimming();

            return new BooleanExpressionNode(isSwimming);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsTouchingWater implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isTouchingWater' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isTouchingWater = EntityClass.this.entity.isTouchingWater();

            return new BooleanExpressionNode(isTouchingWater);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsTouchingWaterOrRain implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isTouchingWaterOrRain' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isTouchingWaterOrRain = EntityClass.this.entity.isTouchingWaterOrRain();

            return new BooleanExpressionNode(isTouchingWaterOrRain);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsWet implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isWet' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean isWet = EntityClass.this.entity.isWet();

            return new BooleanExpressionNode(isWet);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class Kill implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'kill' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            EntityClass.this.entity.kill();

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class RemoveAllPassengers implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'removeAllPassengers' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            EntityClass.this.entity.removeAllPassengers();

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class ResetPortalCooldown implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'resetPortalCooldown' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            EntityClass.this.entity.resetPortalCooldown();

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class SendMessage implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'sendMessage' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode message = arguments.get(0).interpret(script);

            if (!message.getType().equals(new IdentifierExpressionNode("string"))) {
                throw new TypeError("Argument 1 for function 'sendMessage' requires type 'string' but got '" + message.getType() + "'");
            }

            EntityClass.this.entity.sendMessage(Text.literal(((StringExpressionNode) message).value));

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class SetInvisible implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setInvisible' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode invisible = arguments.get(0).interpret(script);

            if (!invisible.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setInvisible' requires type 'boolean' but got '" + invisible.getType() + "'");
            }

            EntityClass.this.entity.setInvisible(((BooleanExpressionNode) invisible).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class SetInvulnerable implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setInvulnerable' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode invulnerable = arguments.get(0).interpret(script);

            if (!invulnerable.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setInvulnerable' requires type 'boolean' but got '" + invulnerable.getType() + "'");
            }

            EntityClass.this.entity.setInvulnerable(((BooleanExpressionNode) invulnerable).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class SetNoGravity implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setNoGravity' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode noGravity = arguments.get(0).interpret(script);

            if (!noGravity.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setNoGravity' requires type 'boolean' but got '" + noGravity.getType() + "'");
            }

            EntityClass.this.entity.setNoGravity(((BooleanExpressionNode) noGravity).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class SetOnFire implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setOnFire' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode onFire = arguments.get(0).interpret(script);

            if (!onFire.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setOnFire' requires type 'boolean' but got '" + onFire.getType() + "'");
            }

            EntityClass.this.entity.setOnFire(((BooleanExpressionNode) onFire).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class SetOnGround implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setOnGround' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode onGround = arguments.get(0).interpret(script);

            if (!onGround.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setOnGround' requires type 'boolean' but got '" + onGround.getType() + "'");
            }

            EntityClass.this.entity.setOnGround(((BooleanExpressionNode) onGround).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class SetPortalCooldown implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setPortalCooldown' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode portalCooldown = arguments.get(0).interpret(script);

            if (!portalCooldown.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setPortalCooldown' requires type 'boolean' but got '" + portalCooldown.getType() + "'");
            }

            EntityClass.this.entity.setPortalCooldown(((IntegerExpressionNode) portalCooldown).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class SetPos implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 3) {
                throw new SyntaxError("Function 'setPos' takes 3 arguments but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode x = arguments.get(0).interpret(script);

            if (!x.getType().equals(new IdentifierExpressionNode("float"))) {
                throw new TypeError("Argument 1 for function 'setPos' requires type 'float' but got '" + x.getType() + "'");
            }

            LiteralExpressionNode y = arguments.get(1).interpret(script);

            if (!y.getType().equals(new IdentifierExpressionNode("float"))) {
                throw new TypeError("Argument 1 for function 'setPos' requires type 'float' but got '" + y.getType() + "'");
            }

            LiteralExpressionNode z = arguments.get(2).interpret(script);

            if (!z.getType().equals(new IdentifierExpressionNode("float"))) {
                throw new TypeError("Argument 1 for function 'setPos' requires type 'float' but got '" + z.getType() + "'");
            }

            EntityClass.this.entity.setPos(((IntegerExpressionNode) x).value, ((IntegerExpressionNode) y).value, ((IntegerExpressionNode) z).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class SetSilent implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSilent' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode silent = arguments.get(0).interpret(script);

            if (!silent.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setSilent' requires type 'boolean' but got '" + silent.getType() + "'");
            }

            EntityClass.this.entity.setSilent(((BooleanExpressionNode) silent).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class SetSneaking implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSneaking' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode sneaking = arguments.get(0).interpret(script);

            if (!sneaking.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setSneaking' requires type 'boolean' but got '" + sneaking.getType() + "'");
            }

            EntityClass.this.entity.setSneaking(((BooleanExpressionNode) sneaking).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class SetSprinting implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSprinting' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode sprinting = arguments.get(0).interpret(script);

            if (!sprinting.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setSprinting' requires type 'boolean' but got '" + sprinting.getType() + "'");
            }

            EntityClass.this.entity.setSprinting(((BooleanExpressionNode) sprinting).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class SetSwimming implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSwimming' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode swimming = arguments.get(0).interpret(script);

            if (!swimming.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setSwimming' requires type 'boolean' but got '" + swimming.getType() + "'");
            }

            EntityClass.this.entity.setSwimming(((BooleanExpressionNode) swimming).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class ShouldDismountUnderwater implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'shouldDismountUnderwater' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            boolean shouldDismountUnderwater = EntityClass.this.entity.shouldDismountUnderwater();

            return new BooleanExpressionNode(shouldDismountUnderwater);
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class StopRiding implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'stopRiding' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            EntityClass.this.entity.stopRiding();

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class Teleport implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 3) {
                throw new SyntaxError("Function 'teleport' takes 3 arguments but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode destX = arguments.get(0).interpret(script);

            if (!destX.getType().equals(new IdentifierExpressionNode("float"))) {
                throw new TypeError("Argument 1 for function 'teleport' requires type 'float' but got '" + destX.getType() + "'");
            }

            LiteralExpressionNode destY = arguments.get(1).interpret(script);

            if (!destY.getType().equals(new IdentifierExpressionNode("float"))) {
                throw new TypeError("Argument 2 for function 'teleport' requires type 'float' but got '" + destY.getType() + "'");
            }

            LiteralExpressionNode destZ = arguments.get(2).interpret(script);

            if (!destZ.getType().equals(new IdentifierExpressionNode("float"))) {
                throw new TypeError("Argument 3 for function 'teleport' requires type 'float' but got '" + destZ.getType() + "'");
            }

            EntityClass.this.entity.teleport(((FloatExpressionNode) destX).value, ((FloatExpressionNode) destY).value, ((FloatExpressionNode) destZ).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }
}
