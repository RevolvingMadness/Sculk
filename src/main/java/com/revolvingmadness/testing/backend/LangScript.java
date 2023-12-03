package com.revolvingmadness.testing.backend;

import com.revolvingmadness.testing.language.lexer.LangLexer;
import com.revolvingmadness.testing.language.lexer.Token;
import com.revolvingmadness.testing.language.parser.LangParser;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import net.minecraft.util.Identifier;

import java.util.List;

public class LangScript {
    public final String contents;
    public final Identifier identifier;
    public boolean hasErrors;
    public ScriptNode scriptNode;

    public LangScript(Identifier identifier, List<String> contentsList) {
        this.identifier = identifier;
        this.contents = String.join("\n", contentsList);
        this.hasErrors = false;
    }

    public void initialize() {
        LangLexer lexer = new LangLexer(contents);
        List<Token> tokens = lexer.lex();
        LangParser parser = new LangParser(tokens);
        this.scriptNode = parser.parse();
    }
}
