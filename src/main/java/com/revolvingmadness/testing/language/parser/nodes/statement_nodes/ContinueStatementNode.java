package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

public class ContinueStatementNode extends StatementNode {
    @Override
    public boolean equals(Object o) {
        return o instanceof ContinueStatementNode;
    }

    @Override
    public int hashCode() {
        return ContinueStatementNode.class.hashCode();
    }
}
