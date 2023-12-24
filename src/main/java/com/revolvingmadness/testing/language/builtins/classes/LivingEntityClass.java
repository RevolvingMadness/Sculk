package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.FloatClass;
import com.revolvingmadness.testing.language.builtins.classes.types.NullClass;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import net.minecraft.entity.LivingEntity;

import java.util.List;
import java.util.Objects;

public class LivingEntityClass extends BaseClassExpressionNode {
    public final LivingEntity livingEntity;

    public LivingEntityClass(LivingEntity livingEntity) {
        super(new EntityClass(livingEntity));
        this.livingEntity = livingEntity;

        this.variableScope.declare(true, new IdentifierExpressionNode("tiltScreen"), new TiltScreen());
        this.variableScope.declare(true, new IdentifierExpressionNode("wakeUp"), new WakeUp());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        LivingEntityClass that = (LivingEntityClass) o;
        return Objects.equals(this.livingEntity, that.livingEntity);
    }

    @Override
    public String getType() {
        return "LivingEntity";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.livingEntity);
    }

    public class TiltScreen extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 2) {
                throw new SyntaxError("Function 'tiltScreen' takes 2 arguments but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode deltaX = arguments.get(0);

            if (!deltaX.getType().equals("Float")) {
                throw new TypeError("Argument 1 for function 'tiltScreen' requires type 'Float' but got '" + deltaX.getType() + "'");
            }

            BaseClassExpressionNode deltaZ = arguments.get(1);

            if (!deltaZ.getType().equals("Float")) {
                throw new TypeError("Argument 2 for function 'tiltScreen' requires type 'Float' but got '" + deltaZ.getType() + "'");
            }

            LivingEntityClass.this.livingEntity.tiltScreen(((FloatClass) deltaZ).value, ((FloatClass) deltaZ).value);

            return new NullClass();
        }
    }

    public class WakeUp extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'wakeUp' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            LivingEntityClass.this.livingEntity.wakeUp();

            return new NullClass();
        }
    }
}
