package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.ListInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.ObjectClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

@SuppressWarnings("unused")
public class ListClassType extends NBTBuiltinClassType {
    public static final ListClassType TYPE = new ListClassType();

    private ListClassType() {
        super("List");

        try {
            this.addMethod("append", List.of(ObjectClassType.TYPE));
            this.addMethod("contains", List.of(ObjectClassType.TYPE));
            this.addGetterMethod("length", builtinClass -> new IntegerInstance(builtinClass.toList().size()));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public BuiltinClass append(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        boundClass.toList().add(arguments[0]);

        return new NullInstance();
    }

    public BuiltinClass contains(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        return new BooleanInstance(boundClass.toList().contains(arguments[0]));
    }

    @Override
    public BuiltinClass fromNBTList(ListInstance list) {
        return list;
    }
}
