package com.revolvingmadness.testing.language.parser.nodes;

import com.revolvingmadness.testing.language.interpreter.VariableTable;
import com.revolvingmadness.testing.language.parser.nodes.statement.StatementNode;

import java.util.ArrayList;
import java.util.List;

public class ScriptNode implements Node {
    public List<StatementNode> statements;
    public VariableTable variableTable;

    public ScriptNode() {
        this.statements = new ArrayList<>();
        this.variableTable = new VariableTable(this);
    }

    public void interpret() {
        this.statements.forEach(statement -> statement.interpret(this));
    }
}
