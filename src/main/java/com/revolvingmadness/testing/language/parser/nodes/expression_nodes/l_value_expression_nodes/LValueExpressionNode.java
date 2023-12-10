package com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes;

import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;

public interface LValueExpressionNode extends ExpressionNode {
    Variable getVariable(ScriptNode script);
}
