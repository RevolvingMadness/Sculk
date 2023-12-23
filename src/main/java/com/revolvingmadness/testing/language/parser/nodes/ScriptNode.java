package com.revolvingmadness.testing.language.parser.nodes;

import com.revolvingmadness.testing.backend.LangScript;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.StatementNode;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ScriptNode extends Node {
    public final Map<Identifier, LangScript> scripts;
    public final List<StatementNode> statements;

    public ScriptNode(Map<Identifier, LangScript> scripts) {
        this.scripts = scripts;
        this.statements = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        ScriptNode that = (ScriptNode) o;
        return Objects.equals(this.scripts, that.scripts) && Objects.equals(this.statements, that.statements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.scripts, this.statements);
    }
}
