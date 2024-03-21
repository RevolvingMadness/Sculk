package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.DictionaryInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.errors.NBTDeserializationError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class ObjectClassType extends BuiltinClassType {
    public static final ObjectClassType TYPE = new ObjectClassType();

    private ObjectClassType() {
        super("Object", null);

        this.typeVariableScope.declare(List.of(TokenType.CONST), "init", new Init());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "toString", new ToString());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "equals", new Equals());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "fromNBT", new FromNBT());
    }

    private static class Equals extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("equals", arguments, List.of(ObjectClassType.TYPE));

            BuiltinClass object = arguments.get(0);

            return new BooleanInstance(this.boundClass.equals(object));
        }
    }

    private static class FromNBT extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("fromNBT", arguments, List.of(ObjectClassType.TYPE));

            BuiltinClass object = arguments.get(0);

            if (!(object instanceof NBTBuiltinClass nbtBuiltinClass)) {
                throw new NBTDeserializationError(object.type);
            }

            if (!nbtBuiltinClass.isPrimaryNBT()) {
                throw new NBTDeserializationError("Type '" + object.type + "' is not a primary NBT data type");
            }

            if (!(this.boundClass instanceof NBTBuiltinClass nbtBuiltinClass1)) {
                throw new NBTDeserializationError(this.boundClass.type);
            }

            return nbtBuiltinClass1.fromNBT(new DictionaryInstance(arguments.get(0).toDictionary()));
        }
    }

    private static class Init extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("init", arguments);

            return new NullInstance();
        }
    }

    private static class ToString extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("toString", arguments);

            return new StringInstance(this.boundClass.toString());
        }
    }
}