package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.errors.ValueError;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListExpressionNode implements LiteralExpressionNode {
    public final List<ExpressionNode> value;

    public ListExpressionNode(List<ExpressionNode> value) {
        this.value = value;
    }

    @Override
    public BooleanExpressionNode equalTo(LiteralExpressionNode other) {
        if (other instanceof ListExpressionNode listExpression) {
            return new BooleanExpressionNode(this.value.equals(listExpression.value));
        }

        return new BooleanExpressionNode(false);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("list");
    }

    @Override
    public LiteralExpressionNode multiply(LiteralExpressionNode other) {
        if (other instanceof IntegerExpressionNode integerExpression) {
            List<ExpressionNode> elements = new ArrayList<>();

            if (integerExpression.value < 0) {
                throw new ValueError("Can't multiply a list by a negative number");
            }

            for (int i = 0; i < integerExpression.value; i++) {
                elements.addAll(this.value);
            }

            return new ListExpressionNode(elements);
        }

        throw new TypeError("Unsupported binary operator '*' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public BooleanExpressionNode notEqualTo(LiteralExpressionNode other) {
        if (other instanceof ListExpressionNode listExpression) {
            return new BooleanExpressionNode(!this.value.equals(listExpression.value));
        }

        return new BooleanExpressionNode(true);
    }

    @Override
    public StringExpressionNode toStringType() {
        String listString = this.value.stream().map(Object::toString).collect(Collectors.joining(", "));
        return new StringExpressionNode("[" + listString + "]");
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;

        ListExpressionNode otherListExpression = (ListExpressionNode) otherObject;

        return value.equals(otherListExpression.value);
    }

    @Override
    public String toString() {
        return "[" + this.value.stream().map(Object::toString).collect(Collectors.joining(", ")) + "]";
    }
}
