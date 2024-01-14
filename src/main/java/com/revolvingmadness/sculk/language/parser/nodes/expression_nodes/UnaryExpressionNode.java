package com.revolvingmadness.sculk.language.parser.nodes.expression_nodes;

import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.Objects;

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
        return this.operator == that.operator && Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.operator, this.value);
    }
}
