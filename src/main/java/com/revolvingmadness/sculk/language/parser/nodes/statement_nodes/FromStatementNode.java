package com.revolvingmadness.sculk.language.parser.nodes.statement_nodes;

import net.minecraft.util.Identifier;

import java.util.List;

public class FromStatementNode extends StatementNode {
    public final Identifier identifier;
    public final List<String> variablesToImport;

    public FromStatementNode(Identifier identifier, List<String> variablesToImport) {
        this.identifier = identifier;
        this.variablesToImport = variablesToImport;
    }
}
