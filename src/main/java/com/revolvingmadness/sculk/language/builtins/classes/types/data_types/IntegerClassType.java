package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.errors.NumberFormatError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

@SuppressWarnings("unused")
public class IntegerClassType extends NBTBuiltinClassType {
    public static final IntegerClassType TYPE = new IntegerClassType();

    private IntegerClassType() {
        super("Integer", FloatClassType.TYPE);

        try {
            this.addStaticMethod("parseInteger", List.of(StringClassType.TYPE));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BuiltinClass fromNBTInteger(IntegerInstance integer) {
        return integer;
    }

    public BuiltinClass parseInteger(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        String stringClass = arguments[0].toString();

        long integer;

        try {
            integer = Long.parseLong(stringClass);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatError(stringClass);
        }

        return new IntegerInstance(integer);
    }
}
