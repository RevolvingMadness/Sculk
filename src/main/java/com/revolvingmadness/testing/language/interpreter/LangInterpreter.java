package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;

public class LangInterpreter {
	public void interpret(ScriptNode script) {
		script.interpret();
	}
}
