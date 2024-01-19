package com.revolvingmadness.sculk.language.parser.nodes.statement_nodes;

import net.minecraft.util.Identifier;

public class ImportStatementNode extends StatementNode {
    public final Identifier identifier;
    public final String importAs;

    public ImportStatementNode(Identifier identifier, String importAs) {
        this.identifier = identifier;
        this.importAs = importAs;
    }
}
