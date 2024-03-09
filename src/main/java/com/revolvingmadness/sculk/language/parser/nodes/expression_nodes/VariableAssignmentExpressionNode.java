package com.revolvingmadness.sculk.language.parser.nodes.expression_nodes;

import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.Objects;

public class VariableAssignmentExpressionNode extends ExpressionNode {
    public final ExpressionNode expression;
    public final TokenType operator;
    public final ExpressionNode value;

    public VariableAssignmentExpressionNode(ExpressionNode expression, TokenType operator, ExpressionNode value) {
        this.expression = expression;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariableAssignmentExpressionNode that = (VariableAssignmentExpressionNode) o;
        return Objects.equals(expression, that.expression) && operator == that.operator && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression, operator, value);
    }
}
