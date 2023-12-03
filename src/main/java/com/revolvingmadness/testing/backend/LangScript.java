package com.revolvingmadness.testing.backend;

import com.revolvingmadness.testing.language.lexer.LangLexer;
import com.revolvingmadness.testing.language.lexer.Token;
import com.revolvingmadness.testing.language.parser.LangParser;
import com.revolvingmadness.testing.language.parser.nodes.ProgramNode;
import net.minecraft.util.Identifier;

import java.util.List;

public class LangScript {
    public final Identifier identifier;
    public final ProgramNode program;

    public LangScript(Identifier identifier, List<String> contentsList) {
        this.identifier = identifier;

        String contents = String.join("\n", contentsList);
        LangLexer lexer = new LangLexer(contents);
        List<Token> tokens = lexer.lex();
        LangParser parser = new LangParser(tokens);
        this.program = parser.parse();
    }
}
