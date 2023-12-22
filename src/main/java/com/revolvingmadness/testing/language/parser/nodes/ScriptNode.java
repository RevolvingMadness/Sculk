package com.revolvingmadness.testing.language.parser.nodes;

import com.revolvingmadness.testing.backend.LangScript;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.StatementNode;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScriptNode implements Node {
    public final Map<Identifier, LangScript> scripts;
    public final List<StatementNode> statements;

    public ScriptNode(Map<Identifier, LangScript> scripts) {
        this.scripts = scripts;
        this.statements = new ArrayList<>();
    }
}
