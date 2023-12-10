package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes;

import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

public class PropertyExpressionNode implements LValueExpressionNode {
    public final ExpressionNode expression;
    public final IdentifierExpressionNode propertyName;

    public PropertyExpressionNode(ExpressionNode expression, IdentifierExpressionNode propertyName) {
        this.expression = expression;
        this.propertyName = propertyName;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;

        PropertyExpressionNode that = (PropertyExpressionNode) otherObject;

        if (!expression.equals(that.expression))
            return false;
        return propertyName.equals(that.propertyName);
    }

    @Override
    public int hashCode() {
        int result = expression.hashCode();
        result = 31 * result + propertyName.hashCode();
        return result;
    }

    @Override
    public LiteralExpressionNode interpret(ScriptNode script) {
        LiteralExpressionNode interpretedExpression = this.expression.interpret(script);

        return interpretedExpression.getProperty(this.propertyName).value;
    }

    @Override
    public Variable getVariable(ScriptNode script) {
        LiteralExpressionNode interpretedExpression = this.expression.interpret(script);

        return interpretedExpression.getProperty(this.propertyName);
    }
}
