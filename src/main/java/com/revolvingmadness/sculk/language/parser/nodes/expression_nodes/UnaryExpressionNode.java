package com.revolvingmadness.sculk.language.parser.nodes.expression_nodes;

import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.Objects;

public class UnaryExpressionNode extends ExpressionNode {
    public final ExpressionNode expression;
    public final TokenType operator;

    public UnaryExpressionNode(TokenType operator, ExpressionNode expression) {
        this.operator = operator;
        this.expression = expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        UnaryExpressionNode that = (UnaryExpressionNode) o;
        return this.operator == that.operator && Objects.equals(this.expression, that.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.operator, this.expression);
    }
}
