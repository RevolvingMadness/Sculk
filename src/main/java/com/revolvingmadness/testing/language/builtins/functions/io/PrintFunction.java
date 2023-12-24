package com.revolvingmadness.testing.language.builtins.functions.io;

import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.types.NullClass;
import com.revolvingmadness.testing.language.builtins.classes.types.StringClass;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;

import java.util.List;

public class PrintFunction extends BaseClassExpressionNode {
    @Override
    public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
        if (arguments.size() != 1) {
            throw new SyntaxError("Function 'print' requires 1 argument but got " + arguments.size() + " argument(s)");
        }

        BaseClassExpressionNode value = arguments.get(0);
        BaseClassExpressionNode toStringResult = value.call(interpreter, "toString", List.of());

        Logger.broadcast(((StringClass) toStringResult).value, true);

        return new NullClass();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof PrintFunction;
    }

    @Override
    public String getType() {
        return "Function";
    }

    @Override
    public int hashCode() {
        return PrintFunction.class.hashCode();
    }
}
