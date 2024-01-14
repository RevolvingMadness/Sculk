package com.revolvingmadness.sculk.language;

import com.revolvingmadness.sculk.backend.Logger;
import com.revolvingmadness.sculk.backend.SculkScript;
import com.revolvingmadness.sculk.backend.SculkScriptManager;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.errors.Error;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public class Event {
    public final BuiltinFunction function;
    public final Interpreter interpreter;
    public final SculkScript script;

    public Event(BuiltinFunction function) {
        this.script = SculkScriptManager.currentScript;
        this.interpreter = SculkScriptManager.currentScript.interpreter;
        this.function = function;
    }

    public BuiltinClass execute(List<BuiltinClass> arguments) {
        try {
            return this.function.call(this.interpreter, arguments);
        } catch (Error error) {
            Logger.scriptError(this.script, error);
            return new BooleanInstance(false);
        }
    }
}
