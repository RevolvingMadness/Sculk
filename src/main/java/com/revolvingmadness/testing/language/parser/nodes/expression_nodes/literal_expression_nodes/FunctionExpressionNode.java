package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes;

import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.StatementNode;

import java.util.List;
import java.util.Map;

public class FunctionExpressionNode implements LiteralExpressionNode {
    public final Map<IdentifierExpressionNode, IdentifierExpressionNode> arguments;
    public final List<StatementNode> body;
    public final IdentifierExpressionNode name;
    public final ExpressionNode returnType;

    public FunctionExpressionNode(IdentifierExpressionNode name, Map<IdentifierExpressionNode, IdentifierExpressionNode> arguments, ExpressionNode returnType, List<StatementNode> body) {
        this.name = name;
        this.arguments = arguments;
        this.returnType = returnType;
        this.body = body;
    }

    @Override
    public LiteralExpressionNode add(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '+' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode divide(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '/' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public BooleanExpressionNode equalTo(LiteralExpressionNode other) {
        if (other instanceof FunctionExpressionNode functionExpression) {
            return new BooleanExpressionNode(this.name.equals(functionExpression.name) && this.arguments.equals(functionExpression.arguments) && this.returnType.equals(functionExpression.returnType) && this.body.equals(functionExpression.body));
        }

        return new BooleanExpressionNode(false);
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;

        FunctionExpressionNode that = (FunctionExpressionNode) otherObject;

        if (!name.equals(that.name))
            return false;
        if (!arguments.equals(that.arguments))
            return false;
        if (!returnType.equals(that.returnType))
            return false;
        return body.equals(that.body);
    }

    @Override
    public LiteralExpressionNode exponentiate(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '^' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("function");
    }

    @Override
    public BooleanExpressionNode greaterThan(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '>' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public BooleanExpressionNode greaterThanOrEqualTo(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '>=' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + arguments.hashCode();
        result = 31 * result + returnType.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }

    @Override
    public LiteralExpressionNode interpret(ScriptNode script) {
        return this;
    }

    @Override
    public boolean isTruthy() {
        return true;
    }

    @Override
    public BooleanExpressionNode lessThan(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '<' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public BooleanExpressionNode lessThanOrEqualTo(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '<=' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode logicalNot() {
        throw new TypeError("Unsupported unary operator '!' for type '" + this.getType() + "'");
    }

    @Override
    public LiteralExpressionNode mod(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '%' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode multiply(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '*' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public LiteralExpressionNode negate() {
        throw new TypeError("Unsupported unary operator '-' for type '" + this.getType() + "'");
    }

    @Override
    public BooleanExpressionNode notEqualTo(LiteralExpressionNode other) {
        if (other instanceof FunctionExpressionNode functionExpression) {
            return new BooleanExpressionNode(!this.name.equals(functionExpression.name) || !this.arguments.equals(functionExpression.arguments) || !this.returnType.equals(functionExpression.returnType) || !this.body.equals(functionExpression.body));
        }

        return new BooleanExpressionNode(true);
    }

    @Override
    public LiteralExpressionNode subtract(LiteralExpressionNode other) {
        throw new TypeError("Unsupported binary operator '-' for types '" + this.getType() + "' and '" + other.getType() + "'");
    }

    @Override
    public String toString() {
        return this.name + "()";
    }
}
