package com.revolvingmadness.testing.language.builtins.functions.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.types.StringClass;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;

import java.util.List;

public class TypeFunction extends BaseClassExpressionNode {
    @Override
    public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
        if (arguments.size() != 1) {
            throw new SyntaxError("Function 'type' requires 1 argument but got " + arguments.size() + " argument(s)");
        }

        BaseClassExpressionNode object = arguments.get(0);
        String type = object.getType();

        return new StringClass(type);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof TypeFunction;
    }

    @Override
    public int hashCode() {
        return TypeFunction.class.hashCode();
    }

    @Override
    public String getType() {
        return "Function";
    }
}
