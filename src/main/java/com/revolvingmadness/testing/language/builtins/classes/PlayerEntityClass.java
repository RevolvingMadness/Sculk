package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.BooleanClass;
import com.revolvingmadness.testing.language.builtins.classes.types.IntegerClass;
import com.revolvingmadness.testing.language.builtins.classes.types.NullClass;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;
import java.util.Objects;

public class PlayerEntityClass extends BaseClassExpressionNode {
    public final PlayerEntity playerEntity;

    public PlayerEntityClass(PlayerEntity playerEntity) {
        super(new LivingEntityClass(playerEntity));

        this.playerEntity = playerEntity;

        this.variableScope.declare(true, "addExperiencePoints", new AddExperiencePoints());
        this.variableScope.declare(true, "addExperienceLevels", new AddExperienceLevels());
        this.variableScope.declare(true, "isCreative", new IsCreative());
        this.variableScope.declare(true, "isSpectator", new IsSpectator());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        PlayerEntityClass that = (PlayerEntityClass) o;
        return Objects.equals(this.playerEntity, that.playerEntity);
    }

    @Override
    public String getType() {
        return "PlayerEntity";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.playerEntity);
    }

    public class AddExperienceLevels extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("addExperienceLevels", 1, arguments.size());
            }

            BaseClassExpressionNode experienceLevels = arguments.get(0);

            if (!experienceLevels.getType().equals("Integer")) {
                throw ErrorHolder.argumentRequiresType(1, "addExperienceLevels", "Integer", experienceLevels.getType());
            }

            PlayerEntityClass.this.playerEntity.addExperienceLevels(((IntegerClass) experienceLevels).value);

            return new NullClass();
        }
    }

    public class AddExperiencePoints extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("addExperiencePoints", 1, arguments.size());
            }

            BaseClassExpressionNode experience = arguments.get(0);

            if (!experience.getType().equals("Integer")) {
                throw ErrorHolder.argumentRequiresType(1, "addExperiencePoints", "Integer", experience.getType());
            }

            PlayerEntityClass.this.playerEntity.addExperience(((IntegerClass) experience).value);

            return new NullClass();
        }
    }

    public class IsCreative extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isCreative", 0, arguments.size());
            }

            return new BooleanClass(PlayerEntityClass.this.playerEntity.isCreative());
        }
    }

    public class IsSpectator extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isSpectator", 0, arguments.size());
            }

            return new BooleanClass(PlayerEntityClass.this.playerEntity.isSpectator());
        }
    }
}
