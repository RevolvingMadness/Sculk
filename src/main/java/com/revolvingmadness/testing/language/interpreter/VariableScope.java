package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VariableScope {
    public final ScriptNode script;
    public final List<Variable> variables;

    public VariableScope(ScriptNode script) {
        this.script = script;
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

    public Variable getOrThrow(IdentifierExpressionNode name) {
        Optional<Variable> optionalVariable = this.getOptional(name);

        if (optionalVariable.isEmpty()) {
            throw new NameError("Variable '" + name + "' has not been declared");
        }

        return optionalVariable.get();
    }
}
