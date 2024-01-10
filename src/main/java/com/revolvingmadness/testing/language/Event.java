package com.revolvingmadness.testing.language;

import com.revolvingmadness.testing.backend.LangScript;
import com.revolvingmadness.testing.backend.LangScriptManager;
import com.revolvingmadness.testing.backend.Logger;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.testing.language.errors.Error;
import com.revolvingmadness.testing.language.interpreter.Interpreter;

import java.util.List;

public class Event {
    public final BuiltinFunction function;
    public final Interpreter interpreter;
    public final LangScript script;

    public Event(BuiltinFunction function) {
        this.script = LangScriptManager.currentScript;
        this.interpreter = LangScriptManager.currentScript.interpreter;
        this.function = function;
    }

    public void execute(List<BuiltinClass> arguments) {
        try {
            this.function.call(this.interpreter, arguments);
        } catch (Error error) {
            Logger.scriptError(this.script, error);
        }
    }
}
