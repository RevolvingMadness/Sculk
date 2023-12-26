package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.BaseMethodExpressionNode;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;

public class ResourceClass extends BaseClassExpressionNode {
    public final Identifier value;

    public ResourceClass(Identifier value) {
        this.value = value;
        this.variableScope.declare(true, "toString", new ToString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        ResourceClass that = (ResourceClass) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public String getType() {
        return "Resource";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    public class ToString extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("toString", 0, arguments.size());
            }

            return new StringClass(ResourceClass.this.value.toString());
        }
    }
}
