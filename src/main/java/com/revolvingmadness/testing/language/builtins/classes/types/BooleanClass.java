package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.BaseFunctionExpressionNode;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.interpreter.Interpreter;

import java.util.List;
import java.util.Objects;

public class BooleanClass extends BaseClassExpressionNode {
    public final Boolean value;

    public BooleanClass(Boolean value) {
        this.value = value;
        this.variableScope.declare(true, "toString", new ToString());
        this.variableScope.declare(true, "logicalNot", new LogicalNot());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        BooleanClass that = (BooleanClass) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public String getType() {
        return "Boolean";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    public class LogicalNot extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("logicalNot", 0, arguments.size());
            }

            return new BooleanClass(!BooleanClass.this.value);
        }
    }

    public class ToString extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("toString", 0, arguments.size());
            }

            return new StringClass(BooleanClass.this.value.toString());
        }
    }
}
