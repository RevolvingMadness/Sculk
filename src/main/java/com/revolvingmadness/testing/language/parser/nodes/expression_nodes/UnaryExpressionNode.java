package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

import com.revolvingmadness.testing.language.lexer.TokenType;

public class UnaryExpressionNode extends ExpressionNode {
    public final TokenType operator;
    public final ExpressionNode value;

    public UnaryExpressionNode(TokenType operator, ExpressionNode value) {
        this.operator = operator;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;

        UnaryExpressionNode that = (UnaryExpressionNode) o;

        if (this.operator != that.operator)
            return false;
        return this.value.equals(that.value);
    }

    @Override
    public int hashCode() {
        int result = this.operator.hashCode();
        result = 31 * result + this.value.hashCode();
        return result;
    }
}
