package com.revolvingmadness.testing.language.parser.nodes.expression_nodes;

import com.revolvingmadness.testing.language.interpreter.errors.InterpreterError;
import com.revolvingmadness.testing.language.lexer.TokenType;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

import java.util.Objects;

public class UnaryExpressionNode implements ExpressionNode {
    public final TokenType operator;
    public final ExpressionNode value;

    public UnaryExpressionNode(TokenType operator, ExpressionNode value) {
        this.operator = operator;
        this.value = value;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || this.getClass() != otherObject.getClass())
            return false;

        UnaryExpressionNode otherUnaryExpression = (UnaryExpressionNode) otherObject;

        return this.operator == otherUnaryExpression.operator && Objects.equals(this.value, otherUnaryExpression.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.operator, this.value);
    }

    @Override
    public LiteralExpressionNode interpret(ScriptNode script) {
        LiteralExpressionNode interpretedValue = this.value.interpret(script);

        return switch (this.operator) {
            case EXCLAMATION_MARK -> interpretedValue.logicalNot();
            case HYPHEN -> interpretedValue.negate();
            default -> throw new InterpreterError("Unknown unary operator '" + this.operator + "'");
        };
    }
}
