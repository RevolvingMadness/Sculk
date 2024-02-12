package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
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
        super("Integer");

        this.variableScope.declare(List.of(TokenType.CONST), "parseInteger", new ParseInteger());
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("init", 1, arguments.size());
        }

        BuiltinClass integerClass = arguments.get(0);

        if (!integerClass.instanceOf(IntegerType.TYPE)) {
            throw ErrorHolder.argumentRequiresType(1, "init", IntegerType.TYPE, integerClass.getType());
        }

        return new IntegerInstance(integerClass.toInteger());
    }

    private static class ParseInteger extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("parseInteger", 1, arguments.size());
            }

            BuiltinClass stringClass = arguments.get(0);

            if (!stringClass.instanceOf(StringType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "parseInteger", StringType.TYPE, stringClass.getType());
            }

            long integer;

            try {
                integer = Long.parseLong(stringClass.toString());
            } catch (NumberFormatException nfe) {
                throw new NumberFormatError(stringClass.toString());
            }

            return new IntegerInstance(integer);
        }
    }


}
