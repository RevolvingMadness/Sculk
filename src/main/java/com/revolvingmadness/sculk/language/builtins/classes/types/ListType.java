package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.ListInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class ListType extends BuiltinType {
    public ListType() {
        super("List");
        this.typeVariableScope.declare(List.of(TokenType.CONST), "length", new Length());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "contains", new Contains());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "append", new Append());
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("init", 1, arguments.size());
        }

        BuiltinClass listClass = arguments.get(0);

        if (!listClass.instanceOf(new ListType())) {
            throw ErrorHolder.argumentRequiresType(1, "init", new ListType(), listClass.getType());
        }

        return new ListInstance(listClass.toList());
    }

    private static class Contains extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("contains", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            return new BooleanInstance(this.boundClass.toList().contains(other));
        }
    }

    private static class Append extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("append", 1, arguments.size());
            }

            BuiltinClass object = arguments.get(0);

            this.boundClass.toList().add(object);

            return new NullInstance();
        }
    }

    private static class Length extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("length", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toList().size());
        }
    }
}
