package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.errors.NumberFormatError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class IntegerClassType extends BuiltinClassType {
    public static final IntegerClassType TYPE = new IntegerClassType();

    private IntegerClassType() {
        super("Integer", FloatClassType.TYPE);

        this.variableScope.declare(List.of(TokenType.CONST), "parseInteger", new ParseInteger());
    }

    private static class ParseInteger extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("parseInteger", arguments, List.of(StringClassType.TYPE));

            String stringClass = arguments.get(0).toString();

            long integer;

            try {
                integer = Long.parseLong(stringClass);
            } catch (NumberFormatException nfe) {
                throw new NumberFormatError(stringClass);
            }

            return new IntegerInstance(integer);
        }
    }


}
