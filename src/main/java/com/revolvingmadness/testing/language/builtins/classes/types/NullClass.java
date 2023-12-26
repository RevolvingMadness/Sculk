package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.BaseMethodExpressionNode;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.interpreter.Interpreter;

import java.util.List;

public class NullClass extends BaseClassExpressionNode {
    public NullClass() {
        this.variableScope.declare(true, "toString", new ToString());
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof NullClass;
    }

    @Override
    public String getType() {
        return "Null";
    }

    @Override
    public int hashCode() {
        return NullClass.class.hashCode();
    }

    @Override
    public String toString() {
        return "null";
    }

    public static class ToString extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("toString", 0, arguments.size());
            }

            return new StringClass("null");
        }
    }
}
