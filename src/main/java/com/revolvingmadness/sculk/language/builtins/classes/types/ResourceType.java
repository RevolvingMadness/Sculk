package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.ResourceInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class ResourceType extends BuiltinType {
    public ResourceType() {
        super("Resource");
        this.typeVariableScope.declare(List.of(TokenType.CONST), "toString", new ToString());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "equalTo", new EqualTo());
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("init", 1, arguments.size());
        }

        BuiltinClass resourceClass = arguments.get(0);

        if (!resourceClass.instanceOf(new ResourceType())) {
            throw ErrorHolder.argumentRequiresType(1, "init", new ResourceType(), resourceClass.getType());
        }

        return new ResourceInstance(resourceClass.toResource());
    }

    private static class EqualTo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("equalTo", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new ResourceType())) {
                return new BooleanInstance(other.toResource().equals(this.boundClass.toResource()));
            }

            return new BooleanInstance(false);
        }
    }

    private static class ToString extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("toString", 0, arguments.size());
            }

            return new StringInstance(this.boundClass.toResource().toString());
        }
    }
}
