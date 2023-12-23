package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

import java.util.List;

public class NullClass extends BaseClassExpressionNode {
    public NullClass() {
        this.variableScope.declare(true, new IdentifierExpressionNode("toString"), new ToString());
    }

    @Override
    public String getType() {
        return "Null";
    }

    @Override
    public String toString() {
        return "null";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof NullClass;
    }

    @Override
    public int hashCode() {
        return NullClass.class.hashCode();
    }

    private static class ToString extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'toString' requires 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new StringClass("null");
        }

        @Override
        public String getType() {
            return "Function";
        }
    }
}
