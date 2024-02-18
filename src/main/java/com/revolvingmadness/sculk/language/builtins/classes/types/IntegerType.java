package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.sculk.language.errors.NumberFormatError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class IntegerType extends BuiltinType {
    public static final IntegerType TYPE = new IntegerType();

    private IntegerType() {
        super("Integer", FloatType.TYPE);

        this.variableScope.declare(List.of(TokenType.CONST), "parseInteger", new ParseInteger());
    }

    private static class ParseInteger extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("parseInteger", arguments, List.of(StringType.TYPE));

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
