package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

import java.util.List;

public class FunctionSignature {
    public final List<IdentifierExpressionNode> arguments;
    public final IdentifierExpressionNode name;

    public FunctionSignature(IdentifierExpressionNode name, List<IdentifierExpressionNode> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;

        FunctionSignature functionSignature = (FunctionSignature) otherObject;

        if (!name.equals(functionSignature.name))
            return false;
        return arguments.equals(functionSignature.arguments);
    }

    @Override
    public int hashCode() {
        int result = arguments.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
