package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

public class Variable {
    public final boolean isConstant;
    public final IdentifierExpressionNode name;
    public BaseClassExpressionNode value;

    public Variable(boolean isConstant, IdentifierExpressionNode name, BaseClassExpressionNode value) {
        this.isConstant = isConstant;
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;

        Variable variable = (Variable) o;

        if (!this.name.equals(variable.name))
            return false;
        return this.value.equals(variable.value);
    }

    @Override
    public int hashCode() {
        int result = this.name.hashCode();
        result = 31 * result + this.value.hashCode();
        return result;
    }
}
