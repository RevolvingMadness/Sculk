package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.ListInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.errors.ValueError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class StringClassType extends NBTBuiltinClassType {
    public static final StringClassType TYPE;

    private StringClassType() {
        super("String");
    }

    public BuiltinClass endsWith(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        return new BooleanInstance(boundClass.toString().endsWith(arguments[0].toString()));
    }

    @Override
    public BuiltinClass fromNBTString(StringInstance string) {
        return string;
    }

    public BuiltinClass fromUnicode(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        long unicode = arguments[0].toInteger();

        if (unicode < 0 || unicode > 9999) {
            throw new ValueError("Invalid unicode '" + unicode + "'");
        }

        return new StringInstance(String.valueOf((char) unicode));
    }

    public BuiltinClass split(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        String[] splitted = boundClass.toString().split(arguments[0].toString());

        List<BuiltinClass> result = new ArrayList<>();

        for (String part : splitted) {
            result.add(new StringInstance(part));
        }

        return new ListInstance(result);
    }

    public BuiltinClass startsWith(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        return new BooleanInstance(boundClass.toString().startsWith(arguments[0].toString()));
    }

    static {
        TYPE = new StringClassType();

        try {
            TYPE.addMethod("startsWith", List.of(StringClassType.TYPE));
            TYPE.addMethod("endsWith", List.of(StringClassType.TYPE));
            TYPE.addMethod("split", List.of(StringClassType.TYPE));
            TYPE.addNoArgMethod("length", builtinClass -> new IntegerInstance(builtinClass.toString().length()));
            TYPE.addNoArgMethod("lowercase", builtinClass -> new StringInstance(builtinClass.toString().toLowerCase()));
            TYPE.addNoArgMethod("uppercase", builtinClass -> new StringInstance(builtinClass.toString().toUpperCase()));
            TYPE.addMethod("fromUnicode", List.of(IntegerClassType.TYPE));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
