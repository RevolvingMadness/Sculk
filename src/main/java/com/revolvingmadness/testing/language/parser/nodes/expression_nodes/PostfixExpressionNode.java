package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

import com.revolvingmadness.testing.language.lexer.TokenType;

import java.util.Objects;

public class PostfixExpressionNode extends ExpressionNode {
    public final ExpressionNode expression;
    public final TokenType operator;

    public PostfixExpressionNode(ExpressionNode expression, TokenType operator) {
        this.expression = expression;
        this.operator = operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        PostfixExpressionNode that = (PostfixExpressionNode) o;
        return Objects.equals(this.expression, that.expression) && this.operator == that.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.expression, this.operator);
    }
}
