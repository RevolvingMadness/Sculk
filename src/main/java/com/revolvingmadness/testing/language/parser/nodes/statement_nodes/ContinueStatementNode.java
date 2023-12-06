package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.interpreter.errors.Continue;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;

public class ContinueStatementNode implements StatementNode {
    @Override
    public void interpret(ScriptNode script) {
        throw new Continue();
    }
}
