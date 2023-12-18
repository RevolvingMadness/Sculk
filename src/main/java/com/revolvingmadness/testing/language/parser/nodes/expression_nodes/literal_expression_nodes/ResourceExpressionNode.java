package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import net.minecraft.util.Identifier;

public class ResourceExpressionNode implements LiteralExpressionNode {
    public final Identifier value;

    public ResourceExpressionNode(Identifier value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || this.getClass() != otherObject.getClass())
            return false;

        ResourceExpressionNode otherResourceExpression = (ResourceExpressionNode) otherObject;

        return this.value.equals(otherResourceExpression.value);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("resource");
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
