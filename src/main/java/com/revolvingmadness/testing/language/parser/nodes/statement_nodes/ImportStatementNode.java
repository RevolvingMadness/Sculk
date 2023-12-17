package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.backend.LangScript;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import net.minecraft.util.Identifier;

public class ImportStatementNode implements StatementNode {
    private final Identifier resource;

    public ImportStatementNode(Identifier resource) {
        this.resource = resource;
    }

    @Override
    public void interpret(ScriptNode script) {
        LangScript importedScript = script.scripts.get(this.resource);
        ScriptNode importedScriptNode = importedScript.scriptNode;

        importedScriptNode.statements.forEach(statement -> statement.interpret(script));
    }
}
