package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.ListInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.ObjectClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class ListClassType extends NBTBuiltinClassType {
    public static final ListClassType TYPE = new ListClassType();

    private ListClassType() {
        super("List");
        this.typeVariableScope.declare(List.of(TokenType.CONST), "length", new Length());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "contains", new Contains());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "append", new Append());
    }

    @Override
    public BuiltinClass fromNBTList(ListInstance list) {
        return list;
    }

    private static class Append extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("append", arguments, List.of(ObjectClassType.TYPE));

            BuiltinClass object = arguments.get(0);

            this.boundClass.toList().add(object);

            return new NullInstance();
        }
    }

    private static class Contains extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("contains", arguments, List.of(ObjectClassType.TYPE));

            BuiltinClass other = arguments.get(0);

            return new BooleanInstance(this.boundClass.toList().contains(other));
        }
    }

    private static class Length extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("length", arguments);

            return new IntegerInstance(this.boundClass.toList().size());
        }
    }
}
