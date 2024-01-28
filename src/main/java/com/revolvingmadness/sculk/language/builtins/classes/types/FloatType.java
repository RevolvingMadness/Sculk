package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.FloatInstance;
import com.revolvingmadness.sculk.language.errors.NumberFormatError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class FloatType extends BuiltinType {
    public FloatType() {
        super("Float", new IntegerType());

        this.variableScope.declare(List.of(TokenType.CONST), "parseFloat", new ParseFloat());
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("init", 1, arguments.size());
        }

        BuiltinClass floatClass = arguments.get(0);

        if (!floatClass.instanceOf(new FloatType())) {
            throw ErrorHolder.argumentRequiresType(1, "init", new FloatType(), floatClass.getType());
        }

        return new FloatInstance(floatClass.toFloat());
    }

    private static class ParseFloat extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("parseFloat", 1, arguments.size());
            }

            BuiltinClass stringClass = arguments.get(0);

            if (!stringClass.instanceOf(new StringType())) {
                throw ErrorHolder.argumentRequiresType(1, "parseFloat", new StringType(), stringClass.getType());
            }

            double float_;

            try {
                float_ = Double.parseDouble(stringClass.toString());
            } catch (NumberFormatException nfe) {
                throw new NumberFormatError(stringClass.toString());
            }

            return new FloatInstance(float_);
        }
    }
}
