package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.FloatInstance;
import com.revolvingmadness.sculk.language.errors.NumberFormatError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

@SuppressWarnings("unused")
public class FloatClassType extends NBTBuiltinClassType {
    public static final FloatClassType TYPE = new FloatClassType();

    private FloatClassType() {
        super("Float", NumberClassType.TYPE);

        try {
            this.addStaticMethod("parseFloat", List.of(StringClassType.TYPE));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BuiltinClass fromNBTFloat(FloatInstance float_) {
        return float_;
    }

    public BuiltinClass parseFloat(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        String stringClass = arguments[0].toString();

        double float_;

        try {
            float_ = Double.parseDouble(stringClass);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatError(stringClass);
        }

        return new FloatInstance(float_);
    }
}
