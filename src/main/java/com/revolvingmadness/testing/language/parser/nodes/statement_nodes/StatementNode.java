package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.parser.nodes.Node;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;

public interface StatementNode extends Node {
    void interpret(ScriptNode script);
}
