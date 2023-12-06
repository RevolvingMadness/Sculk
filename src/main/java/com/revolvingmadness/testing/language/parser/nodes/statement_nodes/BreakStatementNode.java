package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.interpreter.errors.Break;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;

public class BreakStatementNode implements StatementNode {
    @Override
    public void interpret(ScriptNode script) {
        throw new Break();
    }
}
