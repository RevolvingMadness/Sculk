package com.revolvingmadness.testing.backend;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.language.lexer.LangLexer;
import com.revolvingmadness.testing.language.lexer.Token;
import net.minecraft.util.Identifier;

import java.util.List;

public class LangScript {
    public final Identifier identifier;
    public final List<Token> tokens;

    public LangScript(Identifier identifier, List<String> contentsList) {
        this.identifier = identifier;

        String contents = String.join("\n", contentsList);
        LangLexer lexer = new LangLexer(contents);
        this.tokens = lexer.lex();

        Testing.LOGGER.info("Loaded script '" + identifier + "' with tokens");
        this.tokens.forEach(token -> System.out.println(token.toString()));
    }
}
