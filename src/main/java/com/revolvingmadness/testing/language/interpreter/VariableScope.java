package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
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

    public void assign(boolean isConstant, IdentifierExpressionNode name, LiteralExpressionNode value) {
        this.variables.add(new Variable(isConstant, name, value));
    }
}
