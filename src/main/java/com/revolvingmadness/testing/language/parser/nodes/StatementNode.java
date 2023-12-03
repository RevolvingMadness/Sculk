package com.revolvingmadness.testing.language.parser.nodes;

public interface StatementNode extends Node {
    void interpret(ScriptNode script);
}
