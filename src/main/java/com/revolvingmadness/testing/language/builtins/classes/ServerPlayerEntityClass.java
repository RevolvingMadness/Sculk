package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.BooleanClass;
import com.revolvingmadness.testing.language.builtins.classes.types.IntegerClass;
import com.revolvingmadness.testing.language.builtins.classes.types.NullClass;
import com.revolvingmadness.testing.language.builtins.classes.types.StringClass;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;

import java.util.List;
import java.util.Objects;

public class ServerPlayerEntityClass extends BaseClassExpressionNode {
    public final ServerPlayerEntity serverPlayerEntity;

    public ServerPlayerEntityClass(ServerPlayerEntity serverPlayerEntity) {
        super(new PlayerEntityClass(serverPlayerEntity));

        this.serverPlayerEntity = serverPlayerEntity;

        this.variableScope.declare(true, "changeGameMode", new ChangeGameMode());
        this.variableScope.declare(true, "dropSelectedItem", new DropSelectedItem());
        this.variableScope.declare(true, "getIp", new GetIp());
        this.variableScope.declare(true, "getViewDistance", new GetViewDistance());
        this.variableScope.declare(true, "setExperienceLevels", new SetExperienceLevels());
        this.variableScope.declare(true, "setExperiencePoints", new SetExperiencePoints());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        ServerPlayerEntityClass that = (ServerPlayerEntityClass) o;
        return Objects.equals(this.serverPlayerEntity, that.serverPlayerEntity);
    }

    @Override
    public String getType() {
        return "ServerPlayerEntity";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.serverPlayerEntity);
    }

    public class ChangeGameMode extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("changeGameMode", 1, arguments.size());
            }

            BaseClassExpressionNode gameMode = arguments.get(0);

            if (!gameMode.getType().equals("String")) {
                throw ErrorHolder.argumentRequiresType(1, "changeGameMode", "String", gameMode.getType());
            }

            GameMode gameMode1 = GameMode.byName(((StringClass) gameMode).value, null);

            if (gameMode1 == null) {
                throw ErrorHolder.gamemodeDoesNotExist(((StringClass) gameMode).value);
            }

            ServerPlayerEntityClass.this.serverPlayerEntity.changeGameMode(gameMode1);

            return new NullClass();
        }
    }

    public class DropSelectedItem extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("dropSelectedItem", 1, arguments.size());
            }

            BaseClassExpressionNode entireStack = arguments.get(0);

            if (!entireStack.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "dropSelectedItem", "Boolean", entireStack.getType());
            }

            ServerPlayerEntityClass.this.serverPlayerEntity.dropSelectedItem(((BooleanClass) entireStack).value);

            return new NullClass();
        }
    }

    public class GetIp extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getIp", 0, arguments.size());
            }

            return new StringClass(ServerPlayerEntityClass.this.serverPlayerEntity.getIp());
        }
    }

    public class GetViewDistance extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getViewDistance", 0, arguments.size());
            }

            return new IntegerClass(ServerPlayerEntityClass.this.serverPlayerEntity.getViewDistance());
        }
    }

    public class SetExperienceLevels extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setExperienceLevels", 1, arguments.size());
            }

            BaseClassExpressionNode experienceLevel = arguments.get(0);

            if (!experienceLevel.getType().equals("Integer")) {
                throw ErrorHolder.argumentRequiresType(1, "setExperienceLevels", "Integer", experienceLevel.getType());
            }

            ServerPlayerEntityClass.this.serverPlayerEntity.setExperienceLevel(((IntegerClass) experienceLevel).value);

            return new NullClass();
        }
    }

    public class SetExperiencePoints extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setExperiencePoints", 1, arguments.size());
            }

            BaseClassExpressionNode experiencePoints = arguments.get(0);

            if (!experiencePoints.getType().equals("Integer")) {
                throw ErrorHolder.argumentRequiresType(1, "setExperiencePoints", "Integer", experiencePoints.getType());
            }

            ServerPlayerEntityClass.this.serverPlayerEntity.setExperiencePoints(((IntegerClass) experiencePoints).value);

            return new NullClass();
        }
    }
}
