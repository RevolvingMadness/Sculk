package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.ListInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.errors.ValueError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;

public class StringType extends BuiltinType {
    public static final StringType TYPE = new StringType();

    private StringType() {
        super("String");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "startsWith", new StartsWith());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "endsWith", new EndsWith());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "split", new Split());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "length", new Length());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "lowercase", new Lowercase());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "uppercase", new Uppercase());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "fromUnicode", new FromUnicode());
    }

    private static class EndsWith extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("endsWith", arguments, List.of(StringType.TYPE));

            String text = arguments.get(0).toString();

            return new BooleanInstance(this.boundClass.toString().endsWith(text));
        }
    }

    private static class FromUnicode extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("fromUnicode", arguments, List.of(IntegerType.TYPE));

            long unicode = arguments.get(0).toInteger();

            if (unicode < 0 || unicode > 9999) {
                throw new ValueError("Invalid unicode '" + unicode + "'");
            }

            return new StringInstance(String.valueOf((char) unicode));
        }
    }

    private static class Length extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("length", arguments);

            return new IntegerInstance(this.boundClass.toString().length());
        }
    }

    private static class Lowercase extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("lowercase", arguments);

            return new StringInstance(this.boundClass.toString().toLowerCase());
        }
    }

    private static class Split extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("split", arguments, List.of(StringType.TYPE));

            String splitter = arguments.get(0).toString();

            List<BuiltinClass> list = new ArrayList<>();

            String[] split = this.boundClass.toString().split(splitter);

            for (String s : split) {
                list.add(new StringInstance(s));
            }

            return new ListInstance(list);
        }
    }

    private static class StartsWith extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("startsWith", arguments, List.of(StringType.TYPE));

            String text = arguments.get(0).toString();

            return new BooleanInstance(this.boundClass.toString().startsWith(text));
        }
    }

    private static class Uppercase extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("uppercase", arguments);

            return new StringInstance(this.boundClass.toString().toUpperCase());
        }
    }
}
