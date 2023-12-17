package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.BooleanExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.IntegerExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.NullExpressionNode;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;

public class PlayerEntityClass implements LiteralExpressionNode {
    public final LivingEntityClass livingEntity;
    public final PlayerEntity playerEntity;
    public final VariableScope variableScope;

    public PlayerEntityClass(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
        this.variableScope = new VariableScope();

        this.livingEntity = new LivingEntityClass(playerEntity);
        this.variableScope.inherit(this.livingEntity.variableScope);

        this.variableScope.declare(true, new IdentifierExpressionNode("addExperiencePoints"), new AddExperiencePoints());
        this.variableScope.declare(true, new IdentifierExpressionNode("addExperienceLevels"), new AddExperienceLevels());
        this.variableScope.declare(true, new IdentifierExpressionNode("isCreative"), new IsCreative());
        this.variableScope.declare(true, new IdentifierExpressionNode("isSpectator"), new IsSpectator());
    }

    @Override
    public Variable getProperty(IdentifierExpressionNode propertyName) {
        return this.variableScope.getOrThrow(propertyName);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("PlayerEntity");
    }

    public class AddExperienceLevels implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'addExperienceLevels' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode experienceLevels = arguments.get(0).interpret(script);

            if (!experienceLevels.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'addExperienceLevels' requires type 'int' but got '" + experienceLevels.getType() + "'");
            }

            PlayerEntityClass.this.playerEntity.addExperienceLevels(((IntegerExpressionNode) experienceLevels).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class AddExperiencePoints implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'addExperiencePoints' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode experience = arguments.get(0).interpret(script);

            if (!experience.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'addExperiencePoints' requires type 'int' but got '" + experience.getType() + "'");
            }

            PlayerEntityClass.this.playerEntity.addExperience(((IntegerExpressionNode) experience).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsCreative implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isCreative' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(PlayerEntityClass.this.playerEntity.isCreative());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class IsSpectator implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isSpectator' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(PlayerEntityClass.this.playerEntity.isSpectator());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }
}
