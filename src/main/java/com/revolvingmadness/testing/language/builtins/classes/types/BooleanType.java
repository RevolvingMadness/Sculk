package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.testing.language.interpreter.Interpreter;

import java.util.List;

public class BooleanType extends BuiltinType {
    public BooleanType() {
        super("Boolean");

        this.typeVariableScope.declare(true, "toString", new ToString());
        this.typeVariableScope.declare(true, "logicalNot", new LogicalNot());
    }

    private static class LogicalNot extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("logicalNot", 0, arguments.size());
            }

            return new BooleanInstance(!this.boundClass.toBoolean());
        }
    }

    private static class ToString extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("toString", 0, arguments.size());
            }

            return new StringInstance(this.boundClass.toBoolean().toString());
        }
    }
}
