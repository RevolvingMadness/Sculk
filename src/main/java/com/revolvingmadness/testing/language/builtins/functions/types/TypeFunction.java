package com.revolvingmadness.testing.language.builtins.functions.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.BaseFunctionExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.types.StringClass;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.interpreter.Interpreter;

import java.util.List;

public class TypeFunction extends BaseFunctionExpressionNode {
    @Override
    public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("type", 1, arguments.size());
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
}
