package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;

import java.util.Map;

public class DictionaryExpressionNode implements LiteralExpressionNode {
    public final Map<ExpressionNode, ExpressionNode> dictionary;

    public DictionaryExpressionNode(Map<ExpressionNode, ExpressionNode> dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || this.getClass() != otherObject.getClass())
            return false;

        DictionaryExpressionNode otherDictionaryExpression = (DictionaryExpressionNode) otherObject;

        return this.dictionary.equals(otherDictionaryExpression.dictionary);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("dictionary");
    }

    @Override
    public int hashCode() {
        return this.dictionary.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");

        this.dictionary.forEach((key, value) -> {
            stringBuilder.append(key.toString());
            stringBuilder.append(": ");
            stringBuilder.append(value.toString());
            stringBuilder.append(",\n");
        });

        if (this.dictionary.size() != 0) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
