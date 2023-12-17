package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.FloatExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.NullExpressionNode;
import net.minecraft.entity.LivingEntity;

import java.util.List;

public class LivingEntityClass implements LiteralExpressionNode {
    public final EntityClass entity;
    public final LivingEntity livingEntity;
    public final VariableScope variableScope;

    public LivingEntityClass(LivingEntity livingEntity) {
        this.livingEntity = livingEntity;
        this.variableScope = new VariableScope();

        this.entity = new EntityClass(livingEntity);
        this.variableScope.inherit(this.entity.variableScope);

        this.variableScope.declare(true, new IdentifierExpressionNode("tiltScreen"), new TiltScreen());
    }

    @Override
    public Variable getProperty(IdentifierExpressionNode propertyName) {
        return this.variableScope.getOrThrow(propertyName);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("LivingEntity");
    }

    public class TiltScreen implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 2) {
                throw new SyntaxError("Function 'tiltScreen' takes 2 arguments but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode deltaX = arguments.get(0).interpret(script);

            if (!deltaX.getType().equals(new IdentifierExpressionNode("float"))) {
                throw new TypeError("Argument 1 for function 'tiltScreen' requires type 'float' but got '" + deltaX.getType() + "'");
            }

            LiteralExpressionNode deltaZ = arguments.get(1).interpret(script);

            if (!deltaZ.getType().equals(new IdentifierExpressionNode("float"))) {
                throw new TypeError("Argument 2 for function 'tiltScreen' requires type 'float' but got '" + deltaZ.getType() + "'");
            }

            LivingEntityClass.this.livingEntity.tiltScreen(((FloatExpressionNode) deltaZ).value, ((FloatExpressionNode) deltaZ).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class WakeUp implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'wakeUp' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            LivingEntityClass.this.livingEntity.wakeUp();

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }
}
