package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.keybinds.KeybindHelper;
import com.revolvingmadness.sculk.language.Event;
import com.revolvingmadness.sculk.language.ScriptTag;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.FunctionClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerClassType;
import com.revolvingmadness.sculk.language.errors.ValueError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class KeybindsClassType extends BuiltinClassType {
    public static final KeybindsClassType TYPE = new KeybindsClassType();

    private KeybindsClassType() {
        super("Keybinds");

        this.variableScope.declare(List.of(TokenType.CONST), "register", new Register());
    }

    private static class Register extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("register", arguments, List.of(IntegerClassType.TYPE, FunctionClassType.TYPE));

            if (interpreter.scriptTag != ScriptTag.LOAD && interpreter.scriptTag != ScriptTag.START) {
                throw new ValueError("Can only register keybinds in 'start' and 'load' scripts");
            }

            long code = arguments.get(0).toInteger();

            if (code < 32 || code > 348) {
                throw new ValueError("Invalid keycode '" + code + "'");
            }

            KeybindHelper.registerEvent((int) code, new Event(arguments.get(1).toFunction()));

            return new NullInstance();
        }
    }
}
