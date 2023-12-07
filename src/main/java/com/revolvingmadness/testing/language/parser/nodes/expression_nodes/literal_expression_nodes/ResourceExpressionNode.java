package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import net.minecraft.util.Identifier;

public class ResourceExpressionNode implements LiteralExpressionNode {
    public final Identifier value;

    public ResourceExpressionNode(Identifier value) {
        this.value = value;
    }

    @Override
    public BooleanExpressionNode equalTo(LiteralExpressionNode other) {
        if (other instanceof ResourceExpressionNode resourceExpression) {
            return new BooleanExpressionNode(this.value.equals(resourceExpression.value));
        }

        return new BooleanExpressionNode(false);
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;

        ResourceExpressionNode otherResourceExpression = (ResourceExpressionNode) otherObject;

        return value.equals(otherResourceExpression.value);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("resource");
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public BooleanExpressionNode notEqualTo(LiteralExpressionNode other) {
        if (other instanceof ResourceExpressionNode resourceExpression) {
            return new BooleanExpressionNode(!this.value.equals(resourceExpression.value));
        }

        return new BooleanExpressionNode(true);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public StringExpressionNode toStringType() {
        return new StringExpressionNode(this.value.toString());
    }
}
