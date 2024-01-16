package com.revolvingmadness.sculk.backend;

import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.Lexer;
import com.revolvingmadness.sculk.language.lexer.Token;
import com.revolvingmadness.sculk.language.parser.Parser;
import com.revolvingmadness.sculk.language.parser.nodes.ScriptNode;
import net.minecraft.util.Identifier;

import java.util.List;

public class SculkScript {
    public final String contents;
    public final Identifier identifier;
    public final SculkScriptLoader loader;
    public boolean hasBeenInitialized;
    public boolean hasErrors;
    public Interpreter interpreter;
    public ScriptNode scriptNode;

    public SculkScript(Identifier identifier, List<String> contentsList, SculkScriptLoader loader) {
        this.identifier = identifier;
        this.contents = String.join("\n", contentsList);
        this.loader = loader;
        this.hasErrors = false;
        this.hasBeenInitialized = false;
    }

    public void initialize() {
        if (this.hasBeenInitialized) {
            return;
        }

        Lexer lexer = new Lexer(this.contents);
        List<Token> tokens = lexer.lex();
        Parser parser = new Parser(tokens);
        this.scriptNode = parser.parse();
        this.interpreter = new Interpreter(this.loader);
        this.hasBeenInitialized = true;
    }

    public void interpret() {
        this.interpreter.visitScript(this.scriptNode);
    }

    public void interpretWithInterpreter(Interpreter interpreter) {
        interpreter.visitScript(this.scriptNode);
    }

    public void reset() {
        this.interpreter = new Interpreter(this.loader);
    }
}
