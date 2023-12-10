package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.interpreter.errors.ValueError;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.PropertyExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VariableScope {
    public final List<Variable> variables;

    public VariableScope() {
        this.variables = new ArrayList<>();
    }

    public Optional<Variable> getOptional(IdentifierExpressionNode name) {
        for (Variable variable : this.variables) {
            if (variable.name.equals(name)) {
                return Optional.of(variable);
            }
        }

        return Optional.empty();
    }

    public void declare(boolean isConstant, IdentifierExpressionNode name, LiteralExpressionNode value) {
        this.variables.add(new Variable(isConstant, name, value));
    }

    public void assign(LiteralExpressionNode expression, LiteralExpressionNode value) {
        if (expression instanceof IdentifierExpressionNode identifierExpression) {
            Optional<Variable> optionalVariable = this.getOptional(identifierExpression);

            if (optionalVariable.isPresent()) {
                Variable variable = optionalVariable.get();

                if (variable.isConstant) {
                    throw new ValueError("Cannot assign value to variable '" + identifierExpression + "' because it is a constant");
                }

                variable.value = value;
                return;
            }

            throw new NameError("Variable '" + identifierExpression + "' has not been declared");
        } else if (expression instanceof PropertyExpressionNode propertyExpression) {
            expression.set(propertyExpression.propertyName, value);
        } else {
            throw new SyntaxError("Invalid assignment target");
        }
    }
}
