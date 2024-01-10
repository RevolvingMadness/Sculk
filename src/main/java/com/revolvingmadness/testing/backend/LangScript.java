package com.revolvingmadness.testing.backend;

import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.lexer.LangLexer;
import com.revolvingmadness.testing.language.lexer.Token;
import com.revolvingmadness.testing.language.parser.LangParser;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import net.minecraft.util.Identifier;

import java.util.List;

public class LangScript {
    public final String contents;
    public final Identifier identifier;
    public boolean hasBeenInitialized;
    public boolean hasErrors;
    public Interpreter interpreter;
    public ScriptNode scriptNode;

    public LangScript(Identifier identifier, List<String> contentsList) {
        this.identifier = identifier;
        this.contents = String.join("\n", contentsList);
        this.hasErrors = false;
        this.hasBeenInitialized = false;
    }

    public void initialize() {
        if (this.hasBeenInitialized) {
            return;
        }

        LangLexer lexer = new LangLexer(this.contents);
        List<Token> tokens = lexer.lex();
        LangParser parser = new LangParser(tokens);
        this.scriptNode = parser.parse();
        this.interpreter = new Interpreter();
        this.hasBeenInitialized = true;
    }

    public void interpret() {
        this.interpreter.visitScript(this.scriptNode);
    }
}
