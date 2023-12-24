package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.BaseFunctionExpressionNode;
import com.revolvingmadness.testing.language.errors.SyntaxError;
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

    public static class ToString extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'toString' requires 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new StringClass("null");
        }
    }
}
