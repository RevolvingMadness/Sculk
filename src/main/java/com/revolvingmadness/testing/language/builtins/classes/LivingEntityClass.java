package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.BooleanClass;
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
        this.variableScope.declare(true, new IdentifierExpressionNode("equalTo"), new EqualTo());
        this.variableScope.declare(true, new IdentifierExpressionNode("notEqualTo"), new NotEqualTo());
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

    public class EqualTo extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'equalTo' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(LivingEntityClass.this.equals(o));
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

            return new BooleanClass(!LivingEntityClass.this.equals(o));
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class TiltScreen extends BaseClassExpressionNode {
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

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class WakeUp extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'wakeUp' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            LivingEntityClass.this.livingEntity.wakeUp();

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }
}
