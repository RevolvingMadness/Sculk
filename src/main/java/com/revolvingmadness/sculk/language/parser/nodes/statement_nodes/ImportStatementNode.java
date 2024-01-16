package com.revolvingmadness.sculk.language.parser.nodes.statement_nodes;

import net.minecraft.util.Identifier;

public class ImportStatementNode extends StatementNode {
    public final Identifier identifier;

    public ImportStatementNode(Identifier identifier) {
        this.identifier = identifier;
    }
}
