package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.FloatInstance;
import com.revolvingmadness.sculk.language.errors.NumberFormatError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class FloatType extends BuiltinType {
    public static final FloatType TYPE = new FloatType();

    private FloatType() {
        super("Float", NumberType.TYPE);

        this.variableScope.declare(List.of(TokenType.CONST), "parseFloat", new ParseFloat());
    }

    private static class ParseFloat extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("parseFloat", arguments, List.of(StringType.TYPE));

            String stringClass = arguments.get(0).toString();

            double float_;

            try {
                float_ = Double.parseDouble(stringClass);
            } catch (NumberFormatException nfe) {
                throw new NumberFormatError(stringClass);
            }

            return new FloatInstance(float_);
        }
    }


}
