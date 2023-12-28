package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.BaseMethodExpressionNode;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.interpreter.Interpreter;

import java.util.List;
import java.util.Objects;

public class StringClass extends BaseClassExpressionNode {
    public final String value;

    public StringClass(String value) {
        this.value = value;
        this.variableScope.declare(true, "toString", new ToString());
        this.variableScope.declare(true, "add", new Add());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        StringClass that = (StringClass) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public String getType() {
        return "String";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public String toString() {
        return this.value;
    }

    private static class Add extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("add", 1, arguments.size());
            }

            BaseClassExpressionNode other = arguments.get(0);

            StringClass thisString = (StringClass) this.boundClass.call(interpreter, "toString", List.of());
            StringClass otherString = (StringClass) other.call(interpreter, "toString", List.of());

            return new StringClass(thisString.value + otherString.value);
        }
    }

    public class ToString extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("toString", 0, arguments.size());
            }

            return new StringClass(StringClass.this.value);
        }
    }
}
