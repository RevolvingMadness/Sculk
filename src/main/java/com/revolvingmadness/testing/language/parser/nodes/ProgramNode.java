package com.revolvingmadness.testing.language.parser.nodes;

import java.util.ArrayList;
import java.util.List;

public class ProgramNode extends Node {
    public List<StatementNode> statements;

    public ProgramNode() {
        this.statements = new ArrayList<>();
    }
}
