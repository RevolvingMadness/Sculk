package com.revolvingmadness.sculk.language.builtins.functions;

import com.revolvingmadness.sculk.backend.Logger;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.ObjectType;
import com.revolvingmadness.sculk.language.builtins.classes.types.StringType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.text.Text;

import java.util.List;

public class PrintFunction extends BuiltinFunction {
    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validate("print", arguments, List.of(ObjectType.TYPE));

        BuiltinClass value = arguments.get(0);

        BuiltinClass stringClass = value.call(interpreter, "toString", List.of());

        if (!stringClass.instanceOf(StringType.TYPE)) {
            throw ErrorHolder.functionRequiresReturnType("toString", StringType.TYPE, stringClass.getType());
        }

        Logger.broadcast(Text.literal(stringClass.toString()));

        return new NullInstance();
    }
}
