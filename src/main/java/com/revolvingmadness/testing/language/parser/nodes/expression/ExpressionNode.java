package com.revolvingmadness.testing.language.parser.nodes.expression;

import com.revolvingmadness.testing.language.parser.nodes.Node;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;

public interface ExpressionNode extends Node {
    LiteralExpressionNode interpret(ScriptNode script);
}
