package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import net.minecraft.util.Identifier;

import java.util.Objects;

public class ResourceExpressionNode extends LiteralExpressionNode {
    public final Identifier value;

    public ResourceExpressionNode(Identifier value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        ResourceExpressionNode that = (ResourceExpressionNode) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }
}
