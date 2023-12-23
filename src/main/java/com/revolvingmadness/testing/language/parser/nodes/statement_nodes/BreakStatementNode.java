package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

public class BreakStatementNode extends StatementNode {
    @Override
    public boolean equals(Object o) {
        return o instanceof BreakStatementNode;
    }

    @Override
    public int hashCode() {
        return BreakStatementNode.class.hashCode();
    }
}
