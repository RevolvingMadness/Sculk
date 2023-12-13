package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

public class Variable {
    public final boolean isConstant;
    public final IdentifierExpressionNode name;
    public LiteralExpressionNode value;

    public Variable(boolean isConstant, IdentifierExpressionNode name, LiteralExpressionNode value) {
        this.isConstant = isConstant;
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Variable variable = (Variable) o;

        if (!name.equals(variable.name))
            return false;
        return value.equals(variable.value);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
