package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.BooleanClass;
import com.revolvingmadness.testing.language.builtins.classes.types.IntegerClass;
import com.revolvingmadness.testing.language.builtins.classes.types.NullClass;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;

public class PlayerEntityClass extends BaseClassExpressionNode {
    public final PlayerEntity playerEntity;

    public PlayerEntityClass(PlayerEntity playerEntity) {
        super(new LivingEntityClass(playerEntity));

        this.playerEntity = playerEntity;

        this.variableScope.declare(true, new IdentifierExpressionNode("addExperiencePoints"), new AddExperiencePoints());
        this.variableScope.declare(true, new IdentifierExpressionNode("addExperienceLevels"), new AddExperienceLevels());
        this.variableScope.declare(true, new IdentifierExpressionNode("isCreative"), new IsCreative());
        this.variableScope.declare(true, new IdentifierExpressionNode("isSpectator"), new IsSpectator());
    }

    @Override
    public String getType() {
        return "PlayerEntity";
    }

    public class AddExperienceLevels extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'addExperienceLevels' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode experienceLevels = arguments.get(0);

            if (!experienceLevels.getType().equals("Integer")) {
                throw new TypeError("Argument 1 for function 'addExperienceLevels' requires type 'int' but got '" + experienceLevels.getType() + "'");
            }

            PlayerEntityClass.this.playerEntity.addExperienceLevels(((IntegerClass) experienceLevels).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class AddExperiencePoints extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'addExperiencePoints' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode experience = arguments.get(0);

            if (!experience.getType().equals("Integer")) {
                throw new TypeError("Argument 1 for function 'addExperiencePoints' requires type 'int' but got '" + experience.getType() + "'");
            }

            PlayerEntityClass.this.playerEntity.addExperience(((IntegerClass) experience).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsCreative extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isCreative' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(PlayerEntityClass.this.playerEntity.isCreative());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class IsSpectator extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isSpectator' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(PlayerEntityClass.this.playerEntity.isSpectator());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }
}
