package com.revolvingmadness.sculk.language.builtins.functions;

import com.revolvingmadness.sculk.backend.Logger;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.ObjectClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.text.Text;

import java.util.List;

public class PrintFunction extends BuiltinFunction {
    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("print", arguments, List.of(ObjectClassType.TYPE));

        BuiltinClass value = arguments.get(0);

        BuiltinClass stringClass = value.call(interpreter, "toString", List.of());

        if (!stringClass.instanceOf(StringClassType.TYPE)) {
            throw ErrorHolder.functionRequiresReturnType("toString", stringClass.type, StringClassType.TYPE);
        }

        Logger.broadcast(Text.literal(stringClass.toString()));

        return new NullInstance();
    }
}
