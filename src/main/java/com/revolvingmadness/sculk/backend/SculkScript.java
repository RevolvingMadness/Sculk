package com.revolvingmadness.sculk.backend;

import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.Lexer;
import com.revolvingmadness.sculk.language.lexer.Token;
import com.revolvingmadness.sculk.language.parser.Parser;
import com.revolvingmadness.sculk.language.parser.nodes.ScriptNode;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;

public class SculkScript {
    public final String contents;
    public final Identifier identifier;
    public final SculkScriptLoader loader;
    public boolean hasBeenInitialized;
    public boolean hasErrors;
    public Interpreter interpreter;
    public boolean isBeingImported;
    public ScriptNode scriptNode;

    public SculkScript(Identifier identifier, List<String> contentsList, SculkScriptLoader loader) {
        this.identifier = identifier;
        this.contents = String.join("\n", contentsList);
        this.loader = loader;
        this.hasErrors = false;
        this.hasBeenInitialized = false;
        this.isBeingImported = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        SculkScript that = (SculkScript) o;
        return this.hasBeenInitialized == that.hasBeenInitialized && this.hasErrors == that.hasErrors && this.isBeingImported == that.isBeingImported && Objects.equals(this.contents, that.contents) && Objects.equals(this.identifier, that.identifier) && Objects.equals(this.loader, that.loader) && Objects.equals(this.interpreter, that.interpreter) && Objects.equals(this.scriptNode, that.scriptNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.contents, this.identifier, this.loader, this.hasBeenInitialized, this.hasErrors, this.interpreter, this.isBeingImported, this.scriptNode);
    }

    public void import_(Interpreter interpreter) {
        if (this.isBeingImported) {
            return;
        }

        this.isBeingImported = true;
        interpreter.visitScript(this.scriptNode);
        this.isBeingImported = false;
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

    public void reset() {
        this.interpreter = new Interpreter(this.loader);
    }
}
