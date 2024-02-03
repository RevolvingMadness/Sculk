package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.ListInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;

public class StringType extends BuiltinType {
    public StringType() {
        super("String");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "startsWith", new StartsWith());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "endsWith", new EndsWith());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "split", new Split());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "length", new Length());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "lowercase", new Lowercase());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "uppercase", new Uppercase());
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("init", 1, arguments.size());
        }

        BuiltinClass stringClass = arguments.get(0);

        if (!stringClass.instanceOf(new StringType())) {
            throw ErrorHolder.argumentRequiresType(1, "init", new StringType(), stringClass.getType());
        }

        return stringClass.toStringMethod();
    }

    private static class EndsWith extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("endsWith", 1, arguments.size());
            }

            BuiltinClass text = arguments.get(0);

            if (!text.instanceOf(new StringType())) {
                throw ErrorHolder.argumentRequiresType(1, "endsWith", new StringType(), text.getType());
            }

            return new BooleanInstance(this.boundClass.toString().endsWith(text.toString()));
        }
    }

    private static class Length extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("length", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toString().length());
        }
    }

    private static class Lowercase extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("lowercase", 0, arguments.size());
            }

            return new StringInstance(this.boundClass.toString().toLowerCase());
        }
    }

    private static class Split extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("splitter", 1, arguments.size());
            }

            BuiltinClass splitter = arguments.get(0);

            if (!splitter.instanceOf(new StringType())) {
                throw ErrorHolder.argumentRequiresType(1, "splitter", new StringType(), splitter.getType());
            }

            List<BuiltinClass> list = new ArrayList<>();

            String[] split = this.boundClass.toString().split(splitter.toString());

            for (String s : split) {
                list.add(new StringInstance(s));
            }

            return new ListInstance(list);
        }
    }

    private static class StartsWith extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("startsWith", 1, arguments.size());
            }

            BuiltinClass text = arguments.get(0);

            if (!text.instanceOf(new StringType())) {
                throw ErrorHolder.argumentRequiresType(1, "startsWith", new StringType(), text.getType());
            }

            return new BooleanInstance(this.boundClass.toString().startsWith(text.toString()));
        }
    }

    private static class Uppercase extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("uppercase", 0, arguments.size());
            }

            return new StringInstance(this.boundClass.toString().toUpperCase());
        }
    }
}
